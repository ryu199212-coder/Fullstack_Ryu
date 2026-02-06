package com.company.java008_ex;

public class MethodEx002 {
	public static void dog() {System.out.println("멍멍");}
	public static void cat() {System.out.println("야옹");}
	public static void line() {System.out.println("=========");}
	
	
	public static void main(String[] args) {
		
		
		dog();
		line();
		cat();
		line();
	}

}


/*■15. method (1)
1.   method?
-  코드 재사용
-  실행하고자 하는 명령어들을 {} 안에 한꺼번에 모아놓고
-  메서드이름이 호출되면 실행되는 기능
-  function, method, 멤버함수

2. method 사용법
Step1)  함수정의
Step2)  함수호출


연습문제1)  method
패키지명 : com.company.java008_ex
클래스명 :  MethodEx001
다음과 같이 dog(), line(), cat() 메서드를 정의하시오.

public static void main(String[] args) {
  dog();  // 멍멍 출력
  line();   //========= 출력 
  cat();    // 야옹 출력
  line();   //========= 출력
}*/