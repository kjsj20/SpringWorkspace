<%@page import="com.model2.domain.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	int result = (Integer)request.getAttribute("result");
	StringBuilder sb = new StringBuilder();
	sb.append("<script>");
	if(result == 0){
		sb.append("alert('삭제실패');");
		sb.append("history.back();");
	} else {
		sb.append("alert('삭제성공');");
		sb.append("location.href='/board/list'");
	}
	sb.append("</script>");
	
	out.print(sb.toString());
%>