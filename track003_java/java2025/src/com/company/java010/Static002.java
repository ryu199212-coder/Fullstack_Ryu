package com.company.java010;

//1. final 변경하지마
// 클래스는 부품객체
// 클래스는 상태(멤버변수)와 행위(멤버함수)
// 상속X  [상수: 변하지않는값]       override X
class FinalEx extends Object{
	final static String gaecheon = "10-3"; // 클래스 변수 - method - new X - this X - 생성자 X
	String name; // 인스턴스 변수 - heap - new O - 생성자 O - this O > 메모리 각각
	final void show() {System.out.println(FinalEx.gaecheon + "\t" + name);}
}

class FinalSon extends FinalEx{
	// @Override void show() {super.show();}  // override - 상속시 자식클래스에서 부모의 클래스를 재수정
	
}
///////////////////////////////////////////////////////////////
public class Static002 {
	public static void main(String[] args) {
		//FinalEx.gaecheon = "10-1";
		FinalEx saram = new FinalEx();
		saram.name = "sally";
		saram.show();
	}
}
///////////////////////////////////////////////////////////////