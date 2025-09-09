package com.company.java005_ex;

import java.util.Scanner;

public class Repeat012_2 {
	public static void main(String[] args) {
			
			int i = 0;
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("숫자를 입력하시오 >"); i=scanner.nextInt();
			
			switch(i){
			case 1 : System.out.println("one"); break;
			case 2 : System.out.println("two"); break;
			case 3 : System.out.println("three"); break;
			default : System.out.println("1,2,3이 아니다"); break;
			
			
			
			}
}
	}