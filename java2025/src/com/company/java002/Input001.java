package com.company.java002;

import java.util.Scanner;

public class Input001 {
     public static void main(String[] args){
    	 //자료형의 분류 : 기본형(값) / 창조형(주소)
    	 //기본형 : 논리형(boolean), 정수형(byte-short-int-long), 실수형(float-double)
    	 
    	 //OOP? 클래스(부품객체) 조립해 완성해가는 프로그램
    	 //변수
    	 int     like    = 0; //like [0] 기본형
    	 Scanner scanner = new Scanner(System.in);  //참조 : ctrl + shift + o
    	 //1. new 공간빌리기         heap 1000 번지 []
    	 //2. Scanner(System.in)  Scanner() 사용 가능하게 초기화 - System.in 키보드로 입력
    	 //3. 사용법 : Scanner.   변수명.
    	 //입력
    	 System.out.println("정수(1,2,3)를 입력하세요");
    	 like = scanner.nextInt(); // 입력받기 커서가 깜빡임
    	 //처리 X
    	 //출력
    	 System.out.println("입력한 정수는 " + like + "입니다.");
    	 
    	 연습문제1)    ※ Input002  참고
    	 패키지명 : com.company.java002_ex
    	 클래스명 : InputEx001
    	 출력내용 : 
    	    1-1.  나이를 입력받을 자료형 선택후  변수명  age로 하시오.  예) 10,20
    	    1-2.  Scanner이용해서 나이 입력받고 출력하시오.
    	   
    	     당신의 나이를 입력하시오 > _입력받기
    	     내 나이는 ** 입니다.

    	 연습문제2)
    	 패키지명 : com.company.java002_ex
    	 클래스명 : InputEx002
    	 출력내용 :  Scanner이용해서 나이 입력받고 출력하시오.
    	     좋아하는 수(정수)   입력하시오 > _입력받기
    	     좋아하는 숫자는 ** 입니다.
 
    	 
}
}
