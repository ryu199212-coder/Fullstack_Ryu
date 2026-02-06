package com.company.java003_ex;

import java.util.Scanner;

public class CastingEx001 {
	public static void main(String[] args ) {
	
		//변수
		Scanner scanner=new Scanner(System.in);
		int a;
		int b;
		double c;
		//입력
		System.out.print("a 숫자"); a=scanner.nextInt();
		System.out.print("b 숫자"); b=scanner.nextInt();
		//처리
		c=(double)a/b;		
		//출력
		System.out.printf("10/3=%.2f\n",c);
		System.out.printf("%d/%d=%.2f", a, b, c);
		
		
	}

}


