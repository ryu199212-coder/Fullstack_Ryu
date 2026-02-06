package com.company.java004_ex;

import java.util.Scanner;

public class IfEx001 {
	public static void main(String[] agrs) {
        Scanner scanner = new Scanner(System.in);
		//변수
		int i;
		int i2; 
		// String result = "불합격"
		
		//입력
		System.out.print("a점수 입력>"); i=scanner.nextInt();
		System.out.print("b점수 입력>"); i2=scanner.nextInt();
		
		//처리
		double result = (i+i2)/2;
		
		//출력
		if(result >= 60) {System.out.println("합격");}
		else{System.out.println("불합격");}
		//System.out.println(result);
		
		
		
	}

}


/*연습문제1)
패키지명 : com.company.java004_ex
클래스명 :  IfEx001
출력내용 : 평균을 입력받아 60점이상이면 합격,  불합격여부를 출력하는 프로그램을 IF로 작성하시오.*/