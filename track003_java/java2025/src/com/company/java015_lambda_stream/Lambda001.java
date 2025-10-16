package com.company.java015_lambda_stream;

//1. 클래스는 부품객체 - 설계도(틀, can do this~!)
//2. 상태 + 행위    - interface( public static final/ public abstract )

interface Inter1{void method();}
class Inter1Imp1 implements Inter1{
	@Override public void method() {System.out.println("Hello :D");} 
}

public class Lambda001 {

	public static void main(String[] args) {
		//#1. interface 구현객체(자식)
		Inter1 i1 = new Inter1Imp1(); i1.method();
		//#2. 익명이너클래스 (test목적, 한번쓰고 버림, 잘안쓰는 이벤트)
		//Inter1 i2 = new Inter1(); interface 는 추상메서드 이기 때문에 new 사용 불가
		Inter1 i2 = new Inter1() {
			@Override public void method() {System.out.println("일회용-Hello :D");}  
			}; i2.method();
		//#3. lambda 
		Inter1 i3 = () -> {System.out.println("줄이기-Hello :D");};
			   i3.method();
	}	

}
