package com.company.java005_ex;

import java.util.Scanner;

public class Repeat13_2 {
	public static void main(String[] args) {
		
		char input;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("입력 하시오"); input = scanner.next().charAt(0);
		
		switch(input) {
			case 'a' : System.out.println("apple"); break;
			case 'b' : System.out.println("banana"); break;
		  	case 'c' : System.out.println("coconut"); break;
		  	default : System.out.println("a, b, c가 아님"); break;
		  	
		
		
		} 
		
	}

}
