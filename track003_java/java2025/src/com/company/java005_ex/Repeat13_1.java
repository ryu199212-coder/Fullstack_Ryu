package com.company.java005_ex;

import java.util.Scanner;

public class Repeat13_1 {
	public static void main(String[] args) {
		
		char input; 
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("입력 하시오"); input = scanner.next().charAt(0);
		
		if(input == 'a') {System.out.println("apple");}
		else if(input =='b') {System.out.println("banana");}
		else if(input =='c') {System.out.println("coconut");}
		else {System.out.println("a,b,c가 아님");
		
		
		}
		
	}
	

}
