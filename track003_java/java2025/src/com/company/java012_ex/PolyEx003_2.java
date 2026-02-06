package com.company.java012_ex;

/*
object									3		4
  ↑
 TestA4	{int a, toString}				2		5
  ↑
 TestB4	{int b, toString}				1		6

*/
//Q1. 상속도를 그리시오. 
class TestA42  extends Object{  
int a=10;
@Override public String toString() { return "TestA4 [a=" + a + "]"; }
}
class TestB42  extends TestA42{  
int b=20;
@Override public String toString() { return "TestB4 [b=" + b + "]"; }
}

public class PolyEx003_2 {
	   public static void main(String[] args) {
	      TestA42  ta = new TestA42();
	      //Q2. TestA4  ta 사용할수 있는범위는?
	      // TestA4
	      //Q3. new TestA4() 는  heap area 에서 호출되는 생성자의 순서와 객체가 만들어지는 순서는?
	      // 생성자 : testa4() -> object / 객체 object -> testa4
	      TestB42  tb = new TestB42();  
	      //Q4. TestB4  tb 사용할수 있는범위는?
	      // TestB4 / TestA4
	      //Q5. new TestB4() 는  heap area 에서 호출되는 생성자의 순서와 객체가 만들어지는 순서는?
	      // 생성자 : testb4 -> testa4() -> object / 객체 object -> testa4 -> testb4
	      ta = new TestB42();
	      //Q6. ta가 사용할수 있는 보장하는 변수와 메서드는?
	      // TestA4 
	      //Q7.ta = new TestB4(); 에서 new TestB4() 에서 사용할수 있는 범위는? 
	      // testa4 / testb4
	      tb         = (TestB42) ta;   
	      //Q8. tb         = (TestB4) ta;   에서 tb가 사용할수 있는 범위는?
	      // TestB4 / testa4
	      //Q9. 컴피일러시  tb         = (TestB4) ta;  오류가 안나는 이유는?
	      // 다운캐스팅 상황인데 위에서 부모가 자식을 참조하는 파트가 있고 형변환을 통해 자료형을 맞춰줬기때문에 오류 x

	  
	      System.out.println(tb);  //Q10. 출력내용과 그이유는? 	// TestB4 [b=20]
	      System.out.println(tb.b);//Q11. 출력내용?		// 20
	      System.out.println(tb.a);//Q12. 출력내용?		// 10
	      
	   }
	}