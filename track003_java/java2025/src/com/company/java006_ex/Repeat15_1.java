package com.company.java006_ex;

import java.util.Scanner;

public class Repeat15_1 {
	public static void main(String[] args) {
		
		char ch;
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("문자 입력 > ");
		ch=scanner.next().charAt(0);
		
		if(ch=='m') {System.out.println("mango");} 
		else if(ch=='n') {System.out.println("nudle");} 
		else if(ch=='o') {System.out.println("orange");} 
		else {System.out.println("m, n, o가 아님");}
		
		
		
	}

}
