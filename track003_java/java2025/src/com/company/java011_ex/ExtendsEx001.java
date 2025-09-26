package com.company.java011_ex;
/*
Color 클래스 : 멤버 변수:  name (String, public)  / num (int, private)
  ↑
Green 클래스  : 멤버 변수:  name (String, public), num (int, private)
*/
class Color{
	public String name;
	private int num;
	public int getNum() { return num; } public void setNum(int num) { this.num = num; }

	@Override
	public String toString() { return "Color [name=" + name + ", num=" + num + "]"; }	
}
class Green extends Color{
	public void show() {
		System.out.println("GREEN");
		System.out.println("NAME : " + this.name);
		System.out.println("NUM : " + this.getNum());	
	}
}
public class ExtendsEx001 {
	public static void main(String[] args) {
		Green mygreen = new Green();
        mygreen.name = "LIGHT_GREEN";
        mygreen.setNum(5);
        mygreen.show();
	}
}
/*
GREEN
NAME : LIGHT_GREEN
NUM : 5
*/