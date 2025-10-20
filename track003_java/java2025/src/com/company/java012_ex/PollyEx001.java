package com.company.java012_ex;

//Q1. 상속도를 그리시오.
class TestA2 extends Object{
	int a=10;
@Override public String toString() { return "TestA2 [a=" + a + "]"; }
	}
class TestB2 extends TestA2{
	int b=20;
@Override public String toString() { return "TestB2 [b=" + b + "]"; }
}
////////////////////////////////////////////////////////////
public class PollyEx001 {
	public static void main(String[] args) {
		TestA2 ta = new TestB2();
		//Q2. 15번째 줄에서 TestA2 ta는 클래스의 무엇을 사용할 수 있을까요? 코드의 의미
		//Q3. 15번째 줄에서 TestB2 는 클래스의 무엇을 사용할 수 있을까요?
		
		System.out.println(); // Q4. 출력내용과 이유? TestA2 vs TestB2
		System.out.println(ta.a); // Q5. 사용가능?
		//System.out.println(ta.b); // Q6. 사용가능?
	}
}
////////////////////////////////////////////////////////////