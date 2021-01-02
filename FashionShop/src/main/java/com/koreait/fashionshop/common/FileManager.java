/*
 * 파일과 관련된 유용한 기능을 모아놓는 클래스
 * */
package com.koreait.fashionshop.common;

import java.io.File;

public class FileManager {
	private String saveDir;
	
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	
	public String getSaveDir() {
		return saveDir;
	}
	
	//확장자만 추출하기 
	public static String getExtend(String path) {
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		//System.out.println(ext);		
		return ext;
	}
	
	//파일삭제  : 호출자는 삭제하고싶은 파일의 경로를 넘긴다
	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
	
	//미리 단위 테스트 해보기 위함..
	/*
	public static void main(String[] args) {
		//   d:\photo\summer\2010\지난.여름에.놀러.갔던.사진.jpg
		String filename="d:\\photo\\summer\\2010\\지난.여름에.놀러.갔던.사진.jpg";
		getExtend(filename);
	}
	*/
}




