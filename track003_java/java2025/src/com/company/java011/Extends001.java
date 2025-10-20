package com.company.java011;

//1. 상속? 클래스의 재사용
/* 실선-속이 빈 화살표(일반화)
 * Object       3)          {Object()}       4)
 *   ↑
 *   A int a    2)          {a   A()   a=10} 5)
 *   ↑
 *   B int b    1) new : 1번지{b → B()   b=20} 6)
 *   ----------------------------------------------
 *   B b1 = new B(); b1.show();
 *   ----------------------------------------------
 *   1) extends 상속
 *   2) A는 Object이다, B도 Object이다
 *   3) 생성자 호출 : B() → A() → Object   123
 *   4) 객체생성 : Object → A → B         456
 */
class A extends Object{
	int a=10;
	public A() { super(); }
}

class B extends A{
	int b=20;
	public B() { super(); }
	public void show() {System.out.println(super.a + "\t" + this.b);}
}

public class Extends001 {
	public static void main(String[] args) {
		B b1 = new B(); b1.show();
	}
}
