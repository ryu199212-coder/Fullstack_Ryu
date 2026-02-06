package com.company.java_ex;

class A1{
	int a;
	public A1() {super();}
	public A1(int A) {super();this.a=a;}
}

class B1{
	int b;
	public B1() {super();}
	public B1(int B) {super();this.b=b;}
}
class C1{
	int c;
	public int a;
	public int b;
	public void showC() {
		System.out.println("상속받은 A클래스의 a : " + a);
		System.out.println("상속받은 A클래스의 b : " + b);
		System.out.println("자신의 멤버 C클래스의 c : " + c);
	}
}

public class Repaet006_Extends {
	public static void main(String[] args) {
	C1 myc = new C1();
	myc.a=10;myc.b=20; myc.c=30; myc.showC();
	}
}
