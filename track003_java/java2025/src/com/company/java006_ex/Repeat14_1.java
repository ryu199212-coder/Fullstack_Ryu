package com.company.java006_ex;

import java.util.Scanner;

public class Repeat14_1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char ch;
		
		System.out.println("문자 입력 >");
		ch=scanner.next().charAt(0);
		
		
		if (ch=='x') {System.out.println("x-ray");}
		else if(ch=='y') {System.out.println("yogurt");}
		else if(ch=='z') {System.out.println("zebra");}
		else {System.out.println("x, y, z가 아니다");}
		
		
		
		
		
		
		
	
	}

}
/*사용자로부터 문자를 입력받아 다음과 같이 출력하세요.
x를 입력받으면 "x-ray" 출력
y를 입력받으면 "yogurt" 출력
z를 입력받으면 "zebra" 출력
그 외에는 "x, y, z가 아닙니다" 출력 */