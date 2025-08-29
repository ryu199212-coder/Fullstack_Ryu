package com.company.java002_ex;

import java.util.Scanner;

public class InputEX001 {
	public static void main(String[] args) {
		int age = 34;
		Scanner scanner = new Scanner(System.in);
		System.out.print("당신의 나이를 입력하시오>");
		age = scanner.nextInt();
		System.out.println("내 나이는" + age + "입니다.");
		
	}
}