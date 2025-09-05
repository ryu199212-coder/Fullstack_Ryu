package com.company.java004_ex;

import java.util.Scanner;

public class IfEx007 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int num1;
		int num2;
		char ch='\u0000';
		
		
		System.out.println("정수를 하나 입력해주세요>"); num1=scanner.nextInt();
		System.out.println("정수를 하나 입력해주세요>"); num2=scanner.nextInt();
		System.out.println("연산자를 입력해주세요>"); ch=scanner.next().charAt(0);
		
		if(ch=='+') {System.out.printf("%d+%d=%d\n" , num1, num2, num1+num2);}
		else if(ch=='-') {System.out.printf("%d+%d=%d\n" , num1, num2, num1-num2);}
		else if(ch=='*') {System.out.printf("%d+%d=%d\n" , num1, num2, num1*num2);}
		else if(ch=='/') {System.out.printf("%d+%d=%d\n" , num1, num2, num1/num2);}
		
	}

}


/*연습문제7)   
패키지명 : com.company.java004_ex
클래스명 :  IfEx007
출력내용 :  계산기

1. 정수를 하나 입력해주세요 > 10
2. 정수를 하나 입력해주세요 > 3
3. 연산자를 입력해주세요(+,-,*,/) > +
10+3=13

1. 정수를 하나 입력해주세요 > 10
2. 정수를 하나 입력해주세요 > 3
3. 연산자를 입력해주세요(+,-,*,/) > -
10-3=7 */