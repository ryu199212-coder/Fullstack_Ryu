package com.company.java_ex;

class A{
	int a;                  //인스턴스 변수 - heap - new O - this X
	static String company;  //클래스 변수 - method - new X - A.company - this X
	//void method() {int a; System.out.println(a);}
	//지역변수 - void methode() 안에서만 사용 + 오류나는 위치
	//해결
	void method() {int a = 0; System.out.println(a);}
}

public class Repeat002_class {

	public static void main(String[] args) {
		//1. 사용방법 : 설계도 - 장난감 조립 - 갖고놀기
		//2. 위의 사용방법 룰이 깨짐
		System.out.println(A.company);
		

	}

}
