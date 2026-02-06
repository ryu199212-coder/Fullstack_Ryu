package com.company.java003;

import java.util.Scanner;

public class Repeat009 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.print("숫자입력1>"); int num1 = scanner.nextInt();
		System.out.print("숫자입력2>"); int num2 = scanner.nextInt();
		
		double result = (double)num1/num2;
				
		System.out.printf("%d / %d = %.2f\n", num1, num2, result);
	}

}
