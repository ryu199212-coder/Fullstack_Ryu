package com.company.java008_ex;

import java.util.Scanner;

public class Repeat020 {
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		char ch;
		
		for(;;) {
			System.out.println("입력1");ch=scanner.next().charAt(0);
			
			if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {break;					
			}
		}
		
		while(!(ch == '+' || ch == '-' || ch == '*' || ch == '/')) {
			System.out.println("입력2");ch=scanner.next().charAt(0);
			}
		
		do {System.out.println("입력3");ch=scanner.next().charAt(0);} 
		while(!(ch == '+' || ch == '-' || ch == '*' || ch == '/'));

		


		
		
		
		
		
  }
		
}