package com.company.java004_ex;

import java.util.Scanner;

public class Repeat011 {
	public static void main(String[] args) {
		
		int num1, num2;
		String result;
		char ch = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("정수 입력 >"); num1=scanner.nextInt();
		System.out.println("정수 입력 >"); num2=scanner.nextInt();
		System.out.println("연산자 입력 >"); ch=(char) scanner.next().charAt(0);
		
		result = (""+num1+ch+num2+"=");
		
		if(ch=='+') {result += (num1+num2);}
		else if(ch=='-') {result += (num1-num2);}
		else if(ch=='*') {result += (num1*num2);}
		else if(ch=='/') {result += String. format("%.2f, (double)num1/num2");}
		
		System.out.println(result);
		
	
		
		
	}
}
