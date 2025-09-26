package com.company.java011;

//1. 상속? 클래스의 재사용
/* 실선-속이 빈 화살표(일반화)
	 * Object (실선-속이 빈 화살표 extends)  ■3)Object{}                  ■4)
	 *   ↑                                                           
	 * Animal     ■2) Animal(){name, age / eat(), sleep(), poo()}    ■5)     
	 * ↑    ↑     ■1) Cat     {animal_card / qukquk()}               ■6)
  Person   Cat    ■0) 1번지()  {}
---------------------------------------------------------
  Cat cat = new Cat();
---------------------------------------------------------
1) Cat은 Animal이다
2) 생성자 호풀 : Cat() Animal() Object
 * 객체 생성 : Object Animal Cat
 */
class Animal{
	String name;
	int age;
	void eat(){System.out.println("먹고");}
	void sleep(){System.out.println("자고");}
	void poo(){System.out.println("배변");}
}
class Cat extends Animal{
	String animal_card;
	void qukquk() {System.out.println(this.name + "꾹꾹이");}
}
class person {}
public class Extends002 {
	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.name="kitty"; cat.age=52;
		cat.animal_card="ani-1234";
		cat.eat(); cat.sleep(); cat.poo(); cat.qukquk();
	}
}
