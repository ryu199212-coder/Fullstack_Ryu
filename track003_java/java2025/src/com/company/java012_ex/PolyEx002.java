package com.company.java012_ex;

/*
	object
	  ↑
	 TestA3	{int a, toString}
	  ↑
	 TestB3	{int b, toString}

*/

//Q1. 상속도를 그리시오. 
class TestA3  extends Object{  
 int a=10;
 @Override public String toString() { return "TestA3 [a=" + a + "]"; }
}
class TestB3  extends TestA3{  
 int b=10;
 @Override public String toString() { return "TestB3 [b=" + b + "]"; }
} 
/////////////////////////////////////////////////
public class PolyEx002 {
 public static void main(String[] args) {
    TestB3  tb =  (TestB3) new TestA3();
    //Q2. 15번째줄에서   TestB3  tb는 클래스의 무엇을 사용할수 있을까요? 코드의 의미
    // TestB3 / TestA3			// A : {int b, toString} - {int a,----------}
    //Q3. 15번째줄에서   T(TestB3) new TestA3() 클래스의 무엇을 사용할 수 있을까요?
    // TestB3 / TestA3			// A : {int b, toString}
    //Q4. 코드상에서는 문제가 없는데 코드를 실행하면 오류가 나는 이유는?
    // 다운캐스팅 시에는 부모에 자식을 담은 정보가 필요한데 그것이 없기때문에 (오버라이딩 toString 때문에)
 }
}

