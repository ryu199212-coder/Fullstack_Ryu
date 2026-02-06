package com.company.java012_ex;

//Q1. 상속도 그리기
/*
    Object
      ↑
     Papa {money = 10000 / sing}
      ↑
     Son {money = 15000 / [@sing-빅뱅}
*/

//Q2. 각클래스에서 사용할수있는 멤버변수/멤버메서드
/*class Papa extends Object{  
	int money = 10000;     
	public Papa() { super(); }
	public void sing() {  System.out.println("GOD-거짓말");  }
}// end class
class Son extends Papa{ 
	int money = 1500;
	public Son() { super(); }
	@Override public void sing() {  System.out.println("빅뱅-거짓말"); }
} // end class

public class PolyEx004 {
	public static void main(String[] args) {
		Papa mypapa = new Son();    //부모 = 자식 (업캐스팅 / 타입캐스팅X)
		// Q3. Papa mypapa 의미? A : Papa자료형 쓸수있음{money = 10000 / sing}
		// Q4. 인스턴스화한 실제 메모리 빌려온그림
		// mypapa = {money = 15000 / [@sing-빅뱅} - {money = 10000] / sing - GOD}
		System.out.println(((Son)mypapa).money); // Q5.  출력   
		mypapa.sing();  //Q6. 출력 
		 //Q7. mypapa.money 를 이용해서  1500 출력되게 해주세요.  
	} 
}
/*
연습문제4)  다형성
패키지명 : com.company.java012_ex
클래스명 : PolyEx004
다음과 같이 코드를 작성하시오.
// Q1. 상속도 그리기
// Q2. 각클래스에서 사용할수있는 멤버변수/멤버메서드
class Papa extends Object{  
	int money = 10000;     
	public Papa() { super(); }
	public void sing() {  System.out.println("GOD-거짓말");  }
}// end class
class Son extends Papa{ 
	int money = 1500;
	public Son() { super(); }
	@Override public void sing() {  System.out.println("빅뱅-거짓말"); }
} // end class
public class PolyEx004 {
	public static void main(String[] args) { 
		Papa mypapa = new Son();    
		// Q3. Papa mypapa 의미?
		// Q4. 인스턴스화한 실제 메모리 빌려온그림
		System.out.println(mypapa.money); // Q5.  출력   
		mypapa.sing();  //Q6. 출력 
		 //Q7. mypapa.money 를 이용해서  1500 출력되게 해주세요.  
	}
}
*/