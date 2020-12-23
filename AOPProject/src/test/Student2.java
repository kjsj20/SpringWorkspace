package test;

public class Student2 {
	private Bell bell;
	public void setBell(Bell bell) {
		this.bell = bell;
	}
	public void study1() { //영어시간
		bell.startBell();
		System.out.println("1교시는 영어시간이에요");
		bell.endBell();
	}
	public void study2() { //국어시간
		bell.startBell();
		System.out.println("2교시는 국어시간이에요");
		bell.endBell();
	}
	public void study3() { //물리시간
		bell.startBell();
		System.out.println("3교시는 물리시간이에요");
		bell.endBell();
	}
	public void study4() { //수학시간
		bell.startBell();
		System.out.println("4교시는 수학시간이에요");
		bell.endBell();
	}
}
