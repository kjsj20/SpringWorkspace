<%@page import="com.koreait.fashionshop.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<TopCategory> topList = (List)request.getAttribute("topList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header.jsp" %>
<style>
input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}
input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
input[type=button]:hover {
  background-color: #45a049;
}
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

/*드래그 관련*/
#dragArea{
	width:100%;
	height:300px;
	overflow:scroll;
	border: 1px solid #ccc;
}
.dragBorder{
	background : #fff;
}


.box{
	width:100px;
	float:left;
	padding:5px;
}

.box > img{
	width : 100%;
}

.close{
	color:red;
	cursor: pointer;
}
</style>
<script type="text/javascript">
var uploadFiles=[]; //미리보기 이미지 목록

$(function(){
	CKEDITOR.replace("detail");	
	
	//상위카테고리 선택하면..
	$($("select")[0]).change(function(){
		//비동기 방식으로 서버에 요청하되, 순수 ajax보다는 jquery ajax 를 이용하자!!
		getSubList(this);
	});
	
	/*드래그 관련 이벤트*/
	$("#dragArea").on("dragenter", function(e){ //드래그로 영역에 진입했을때...
		$(this).append("dragenter<br>");
		$(this).addClass("dragBorder");
	});
	$("#dragArea").on("drop", function(e){ //드래그영역 위에서 이미지를 떨구면...
		e.preventDefault(); 
		$(this).append("drop");
		
		//자바스크립트로 드래그된 이미지 정보를 구해와서, div영역에 미리보기 효과..
		var fileList = e.originalEvent.dataTransfer.files; //드래그한 파일들에 대한 배열 얻기!!
		console.log(fileList);
		
		//배열안에 드어있는 이미지들에 대한 미리보기처리..
		for(var i = 0; i < fileList.length; i++){
			uploadFiles.push(fileList[i]); //fileList안의 요소들을 일반배열에 옮겨심기
			//왜 심었나? 배열이 지원하는 여러 메서드들을 활용하기 위해...(ex : indexOf)
			preview(uploadFiles[i], i);			
		}
	});
	$("#dragArea").on("dragover", function(e){ //드래그영역 위에 있는 동안...
		e.preventDefault();
	});
	$("#dragArea").on("dragleave", function(e){ //드래그로 영역에서 빠져나가면...
		$(this).append("dragleave<br>");
		$(this).removeClass("dragBorder");
	});
	
	//이미지 삭제 이벤트 처리
	$("#dragArea").on("click",".close",function(e){
	
		//대상 요소 배열에서 삭제
		//삭제전에 uploadFiles 라는 배열에 들어있는 file의 index를 구하자
		var f = uploadFiles[e.target.id];
		var index = uploadFiles.indexOf(f);
		uploadFiles.splice(index,1);
		
		//대상 요소 삭제 (시각적으로 삭제)
		$(e.target).parent().remove();
	});
});


//업로드 이미지 미리보기
function preview(file, index){
	//js로 이미지 미리보기를 구현하려면, 파일리더를 이용하면 된다 FileReader
	var reader = new FileReader(); //아직은 읽을 대상 파일이 결정되지 않음..
	
	//파일을 읽어들이면, 이벤트 발생시킴
	reader.onload=function(e){
		var tag = "<div class=\"box\">"
		tag += "<div class=\"close\" id=\""+index+"\">X</div>"; 
		tag += "<img src=\""+e.currentTarget.result+"\"/>"
		tag += "</div>"
		
		$("#dragArea").append(tag);
	};
	reader.readAsDataURL(file); //지정한 파일을 읽는다.(매개변수로는 파일이 와야함)
}

//비동기 방식으로 하위 카테고리 요청하기!!
function getSubList(obj){
	//alert($(obj).val());
	
	$.ajax({
		url:"/admin/product/sublist", 
		type:"get",
		data:{
			topcategory_id:$(obj).val()			
		},
		success:function(result){
			//alert("서버로 부터 받은 결과는 "+result);
			
			//서버가 이미  json 으로 전송해주었으므로 별도의  parsing이 필요없다!!
			//기존 option태그를 먼저 지우자!!
			$($("select")[1]).empty();
			$($("select")[1]).append("<option>하위카테고리 선택</option>");
			
			for(var i=0;i<result.length;i++){
				var subCategory = result[i]; //subcategory 1건에 대한 json 객체얻기!!
				$($("select")[1]).append("<option value=\""+subCategory.subcategory_id+"\">"+subCategory.name+"</option>");
			}
		}
	});
}
//상품 등록
function regist(){
	/*비동기 전송시, 기존의 form을 이용할 수 있을까? yes*/
	var formData = new FormData($("form")[0]);// <form>태그와는 틀리다.. 전송할ㄸ ㅐ파라미터들을 담을 수 있지만 이 자체가
								  //form태그는 아니다
	
	//미리보기했던 이미지들은 파일컴포넌트화 되어있지 않기 때문에 , 전송데이터에서 빠져있다..
	//따라서 formData전송 전에, 동적으로 파일컴포넌트화시켜 formData에 추가하자!!
	//java에서의 improved for문과 동일한 역할(주로 컬렉션에서 객체를 꺼낼때 사용..)
	$.each(uploadFiles, function(i, file){
		formData.append("addImg", file, file.name); //input type = file name = addImg 와 동일한 효과
		console.log(file.name);
	}); 
	
	/*비동기 업로드*/
	$.ajax({
		url:"/admin/product/regist",
		data: formData,
		contentType:false,/*false일 경우 multipart/form-data*/
		processData:false, /*false일 경우 query-string으로 전송하지 않음*/
		type:"post",
		success:function(result){
			alert(result);
		}
	});
	/* 동기방식 업로드
	$("form").attr({
		action:"/admin/product/regist",
		method:"post",
		enctype:"multipart/form-data"
	});	
	$("form").submit();
	*/
}
</script>
</head>
<body>
<%@ include file="../inc/main_navi.jsp" %>

<h3>Contact Form</h3>
<div class="container">
  <form>
  	
  	<select>
  		<option>상위카테고리 선택</option>
  		<%for(TopCategory topCategory : topList){%>
  		<option value="<%=topCategory.getTopcategory_id()%>"><%=topCategory.getName() %></option>
  		<%} %>
  	</select>
  	
  	<select name="subcategory_id">
  		<option>하위카테고리 선택</option>
  	</select>
    <input type="text" name="product_name" placeholder="상품명">
    <input type="text" name="price" placeholder="가격">
    <input type="text" name="brand" placeholder="브랜드">
	<!-- 파일 최대 4개까지 지원 -->
	<p>대표이미지: <input type="file"  name="repImg"></p>
	
	<div id = "dragArea">	
<!-- 		<p>추가이미지: <input type="file" name="addImg"></p>
		<p>추가이미지: <input type="file" name="addImg" ></p>
		<p>추가이미지: <input type="file" name="addImg" ></p>
		<p>추가이미지: <input type="file" name="addImg" ></p> -->
	</div>
	<!-- 지원 사이즈 선택  -->
	<p>
		XS<input type="checkbox" 		name="fit" value="XS">
		S<input type="checkbox" 		name="fit" value="S">
		M<input type="checkbox" 		name="fit" value="M">
		L<input type="checkbox" 		name="fit" value="L">
		XL<input type="checkbox" 		name="fit" value="XL">
		XXL<input type="checkbox" 	name="fit" value="XXL">
	</p>
	
	<p>
		<input type="color" name="color" value="#ccfefe">
		<input type="color" name="color" value="#ffffff">
		<input type="color" name="color" value="#000000">
		<input type="color" name="color" value="#fdfdfd">
		<input type="color" name="color" value="0000ff">
		<input type="color" name="color" value="#ffdd00">
	</p>	
    
    <textarea id="detail" name="detail" placeholder="상세정보.." style="height:200px"></textarea>
    <input type="button" value="글등록" onClick="regist()">
    <input type="button" value="목록보기" onClick="location.href='/client/notice/list'">
  </form>
</div>

</body>
</html>