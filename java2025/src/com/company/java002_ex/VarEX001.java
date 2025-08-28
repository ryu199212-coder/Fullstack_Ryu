package com.company.java002_ex;

public class VarEX001 {
     public static void main(String[] args) {
    	 //1. 변수?  자료형   변수명
    	 int a = 0;   // %d 1 2 3        a[    ]
    	 System.out.println("1:" + a);
    	 
    	 a = 1000000; // A=B (=대입해주세요)  a[1000000]
    	 System.out.println("2:" +a); //1000000
    	 
    	 
    	 a = a-90000; //a[1000000-90000]
    	 System.out.println("3:" +a);
    	 
    	 //2. 변수의 범위
    	 {int b =20; System.out.println(b);}
    	 //b = 1000; b cannot be resolved to a variable
    	 
    	 
    	 //3. 변수명  $_소문자
    	 //오픈박스(변수)
    	 int $1=1;  int _2 = 2; int a3bc=3;
    	 //밀봉박스(상수)
    	 final int HOME=4;
    	 //int static; //Syntax error on token "static", delete this token
    	 //int package;
    	 //int void;
    	 int main;
    	   //1-1.  apple라는 변수만들기
    	   //1-2.  1-1.에서 만든 변수에 1000이라는 데이터 대입하시오.  ( 자료형 int )
    	   //1-3.  1-1.에서 만든 변수이용해서 
    	   //        사과가격은 1000원입니다 출력
    	   //1-4.  1-1.에서 만든 변수에 2000이라는 데이터 대입하시오.  
    	   //1-5.  1-1.에서 만든 변수이용해서 
    	   //        사과가격은 2000원입니다 출력
    	 
    	
    	 int apple = 1000;
    	 System.out.println("1:" +apple);
    	 apple = 2000;
    	 System.out.println("2:" +apple);

    	 
    	 연습문제2)  
    	 패키지명 : com.company.java002_ex
    	 클래스명 : VarEx002
    	 출력내용 : 
    	    1-1.  정수형데이터를 담을수 있는 변수 a 만들고   
    	    1-2.  a에 10대입하시오
    	    1-3.  정수형데이터를 담을수 있는 변수 b 만들고   
    	    1-4.  b에 3대입하시오
    	    1-5.  System.out.println 을 4번사용해서 
    	      10 + 3 = 13
    	      10  - 3 = 7
    	      10  * 3 = 30
    	      10  / 3 = 3
     
     }
}