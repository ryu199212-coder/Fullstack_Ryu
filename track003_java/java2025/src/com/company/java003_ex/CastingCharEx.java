package com.company.java003_ex;

import java.util.Scanner;

public class CastingCharEx {
	public static void main(String[] args) {
		//변수
		char upper = ' ';
		char lower = ' ';
		Scanner scanner=new Scanner(System.in);
		//입력
		System.out.print("대문자 입력 >");
		upper = scanner.next().charAt(0); // next() 문자열 입력 charAt(0) 첫번째 문자
		//처리 'A' 65, 'B' 66, / 'a' 97, 'b' 98
		lower = (char) (upper + 32);
		//출력
		System.out.println("입력하신 대문자 : " + upper + ", 소문자 변환 : " + lower);

		
		
	}

}
//Q)대문자입력받아서 소문자로 변경프로그램을 작성하시오.