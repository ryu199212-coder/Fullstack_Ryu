package com.company.java013;
/*
 *    Abstract (is A) 일반클래스 + 설계
 *    고양이는 동물이다 
 *       개도 동물이다
 * 
 *    Animal
 *    ↑    ↑
 *   Cat  Dog
*/
abstract class Animal{ //일반클래스 + 설계
	String name;
	abstract void eat();    // '추상 메서드'가 있으면 반드시 '추상 클래스'로 만들어줘야함
	abstract void sleep();  // 구현부가 없음{} = 추상메서드
	abstract void poo();    // 공통의 속성, 구체적인 내용이 없음
	// public Animal() { super();  }
}
class Cat extends Animal{
	@Override void eat() {System.out.println(super.name + "고양이 냠냠");}
	@Override void sleep() {System.out.println(super.name + "고양이 수면");}
	@Override void poo() {System.out.println(super.name + "고양이 시원");}
}
class Dog extends Animal{
	@Override void eat() {System.out.println(super.name + "강아지 냠냠");}
	@Override void sleep() {System.out.println(super.name + "강아지 수면");}
	@Override void poo() {System.out.println(super.name + "강아지 시원");}	
}
	
public class Abstract001 {
	public static void main(String[] args) {
		// 1. abstract class : 일반클래스 + 설계
		// Animal ani = new Animal(); new 메모리 빌리고, 객체생성 / Animal()초기화, {}구현부 없음
		
		Animal ani = null;
		ani = new Cat();
		ani.name = "sally";  ani.eat();
		
		ani = new Dog();
		ani.name = "alpha";  ani.eat();
		
		// 2.사용 목적
		Animal [] arr = {new Cat(), new Cat() , new Dog(), new Dog(),};
		int cnt=0;
		for(Animal a : arr) {a.name = "ani" + ++cnt; a.eat();}
	}
}

/*
 *        Object
 *          ↑
 *        Animal {name / eat(), sleep(), poo()}
 *        ↑    ↑
 *       Cat  Dog{@eat(), @sleep(), @poo}
*/
