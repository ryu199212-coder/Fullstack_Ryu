package com.company.java005_ex;

import java.util.Scanner;

public class Bank_ver1_s1 {
	public static void main(String[] args) {

		// 변수
		Scanner scanner = new Scanner(System.in);
		int num;
		String id = null, pass = null;
		double balance;
		

		// 입력+처리+출력
		// 1-1
		for (;;) {
			System.out.println("====== BANK ======\n*" 
		+ "1.추가\n*2.조회\n*3.입금\n*4.출금\n*5.추가\n*9.종료\n*입력>>>");
			num = scanner.nextInt();
			     if (num == 9) {System.out.println("ATM 종료"); break;}
			else if (num == 1) {System.out.println("추가");}     
			else if (num == 2) {System.out.println("조회");}    
		    else if (num == 3) {System.out.println("입금");}
		    else if (num == 4) {System.out.println("출금");}
			else if (num == 5) {System.out.println("삭제");}
			}//for end
			     
			    
			
		}
			     
			     
			
			
			
			
	
	}

			
			
			

/*
 * Step1. 무한 반복으로 만드는 메뉴 만들기 for(;;){ //1-2 빠져나올 조건 '9' //1-3 입력받은 번호가 if or
 * switch 1을 입력하면 추가기능 입니다. 출력구문 까지만 2을 입력하면 조회기능 입니다. 출력구문 까지만 3을 입력하면 입금기능
 * 입니다. 출력구문 까지만 4을 입력하면 출금기능 입니다. 출력구문 까지만 5을 입력하면 삭제기능 입니다. 출력구문 까지만 9을 입력하면
 * 종료합니다.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */