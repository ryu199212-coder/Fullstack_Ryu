package com.company.java002_ex;

import java.time.Year;
import java.util.Scanner;

public class InputEx003 {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currentYear = (2025);
        int birthYear = scanner.nextInt();
        int age = currentYear - birthYear;
        System.out.print("당신의 태어난 연도를 입력하시오> ");
        System.out.println("당신은 " + age + "세입니다.");
    }
}


