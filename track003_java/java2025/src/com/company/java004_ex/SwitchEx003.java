package com.company.java004_ex;

import java.util.Scanner;

public class SwitchEx003 {
	public static void main(String[] args) {
		
		int a, b;
		double result;
		char ch;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("정수를 하나 입력해주세요 >");
		a=scanner.nextInt();
		System.out.println("정수를 하나 입력해주세요 >");
		b=scanner.nextInt();
		System.out.println("연산자를 입력해주세요 >");
		ch=scanner.next().charAt(0);
		
		switch(ch) {
		case '+' :result=a+b; System.out.println(a+"+"+b+"="+result); break;
		case '-' :result=a-b; System.out.println(a+"-"+b+"="+result); break;
		case '*' :result=a*b; System.out.println(a+"*"+b+"="+result); break;
		case '/' :result=(double)a/b; 
		System.out.printf("%d / %d = %.2f", a, b, result); break;
	
		}
		
		
		
		
		
	}

}
/*연습문제3)  3
패키지명 : com.company.java004_ex
클래스명 :  SwtichEx003
출력내용 :  계산기

1. 정수를 하나 입력해주세요 > 10
2. 정수를 하나 입력해주세요 > 3
3. 연산자를 입력해주세요(+,-,*,/) > +
10+3=13*/