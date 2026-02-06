package com.company.java013;

//1. 클래스는 부품객체
//2. 부품객체는 상태(멤버변수)와 행위(멤버함수)
//3. 상속 : 클래스의 재사용 , 단일상속
//4. 인터페이스 : can do this , 다중상속
/*	Papa{brain}		Mama{brain}
  			↑		↑
  		       Son()
 	
 */
class  Papa{int brain;}
class  Mama{int brain;}
//class  Son extends Papa, Mama{}   //Syntax error on token ",", . expected


interface Animal2{
	String Company="(주)메가스터디";  
	// public static final - [공용]클래스변수 , Animal2.Company , method area , new X, this각각X
	void eat(); // public abstract 메서드
}
class Saram implements Animal2{ 
	@Override public void eat(){ 
		/*Company="kakao";*/  System.out.println( Animal2.Company  + " 랍스타... 냠냠"); 
	}
}// The final field Animal2.Company cannot be assigned
class Pig implements Animal2{ 
	@Override public void eat(){ 
		/*Company="kakao";*/  System.out.println( Animal2.Company  + " 꾸꾸리... 냠냠"); 
	}
} 
public class Interface001 {
	public static void main(String[] args) {
		//Q1. 오류이유
		//Animal2 ani = new Animal2();  // Cannot instantiate the type Animal2
		Animal2 [] anis = {new Saram() , new Saram() , new Pig() , };
		for( Animal2 a: anis ) { a.eat();}
	}
}
/* (속이빈 점선)	
	Animal2   { Company="(주)메가스터디" / eat()  }
	↑  	↑  
Saram	 Pig 
{@eat()}   {@eat()}    

1. 부모 = 자식           > 하나의 자료타입(부모)으로 여러타입(자식,자식,,,자식들)을 관리
2. 업캐스팅, 타입캐스팅 X, > 부모가 메서드 사용시 자식의 메서드가 호출 @Override
*/