package com.company.java012_ex;

//Q1. 상속도 그리기
/*
object									3		4
  ↑
 Papa	{int money, sing()}				2		5
  ↑
 Son	{int money, sing()}				1		6

*/
//Q2. 각클래스에서 사용할수있는 멤버변수/멤버메서드
class Papa extends Object{  
int money = 10000;     	// 멤버변수
public Papa() { super(); }
public void sing() {  System.out.println("GOD-거짓말");  }	//멤버메서드
}// end class

class Son extends Papa{ 
int money = 1500;	//멤버변수
public Son() { super(); }
@Override public void sing() {  System.out.println("빅뱅-거짓말"); }	//멤버메서드
} // end class

public class PolyEx004 {
public static void main(String[] args) { 
   Papa mypapa = new Son();    
   // Q3. Papa mypapa 의미?
   // 업캐스팅된 상황으로 PAPA형식의 변수이지만 실제 참조객체는 SON()의 값이다.  투 타입 모두 사용 가능
   // Q4. 인스턴스화한 실제 메모리 빌려온그림
   // mypapa는 Papa 타입이라 변수는 Papa.money(10000) 를 참조하고, 메서드는 오버라이딩된 Son.sing() 이 호출된다.


   
   System.out.println(mypapa.money); // Q5.  출력   // 10000
   mypapa.sing();  //Q6. 출력 	// 빅뱅-거짓말
    //Q7. mypapa.money 를 이용해서  1500 출력되게 해주세요.
   System.out.println(((Son) mypapa).money);
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
public class PolyEx001 {
   public static void main(String[] args) { 
      Papa mypapa = new Son2();    
      // Q3. Papa mypapa 의미?
      // Q4. 인스턴스화한 실제 메모리 빌려온그림
      System.out.println(mypapa.money); // Q5.  출력   
      mypapa.sing();  //Q6. 출력 
       //Q7. mypapa.money 를 이용해서  1500 출력되게 해주세요.  
   }
}
*/