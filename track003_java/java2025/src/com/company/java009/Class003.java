package com.company.java009;

//1. 클래스는 부품객체
//2. 클래스는 상태(멤버변수)와 행위(멤버함수)

class Car31{}// 생성자 Car31() - 컴파일러가 기본생성자를 자동생성
class Car32{
	String color;
	// alt + shift + s 밑에서 세번째(2,3,4)
	public Car32() { super();  } //기본생성자 만들기
	
	public Car32(String color) { super(); this.color = color; }
	
	@Override public String toString() {return "Car32 [color=" + color + "]";}		
}
class Car33 extends Car32{
	
}
//////////////////////////////////////////////////
public class Class003 {
	public static void main(String[] args) {
		Car31 car1 = new Car31(); // 1. new(메모리빌리고, 객체생성) 2. Car31() 초기화 3. car1 번지
		System.out.println(car1); //Car31@24b1d79b
		
		Car32 car2 = new Car32();
		System.out.println(car2 + "\t" + car2.color); //Car32@103f852  Car32 [color=null]	null
		
		Car32 car3 = new Car32("red");
		
		System.out.println(car3 + "\t" + car3.color); // Car32 [color=red]	red	
	}
}
//////////////////////////////////////////////////

/* Q1. 클래스란? [설계도]         예) [Car31.class, Car32.class]
 * Q2. 객체?   [결과물]          예) [car1, car2, car3]
 * Q3. 인스턴스? [각각의 장남갈들]   예) [car1(x), car2(null), car3(red)]
 */