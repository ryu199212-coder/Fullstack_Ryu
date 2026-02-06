package com.company.java005;

import java.util.Scanner;

public class Repeat002_IFor2 {
	public static void main(String[] args) {
		//1, 무한반복 for(;;){}
		Scanner scanner = new Scanner(System.in);
		for(;;) {
		System.out.println("숫자 1을 입력하세요");
		int a = scanner.nextInt();
		if(a == 1) {break;}
			
		}
		
		//for - break (해당 조건에 나가라)
		for(int i = 1; i<=10; i++) {
			if(i==3) {break;}
			System.out.print(i+"\t");
		}
		System.out.println();
		//for - continue (해당 조건을 건너뛰어라)
		for(int i = 1; i<=10; i++) {
			if(i==3) {continue;}
			System.out.print(i+"\t");
		
	}
	}
}
