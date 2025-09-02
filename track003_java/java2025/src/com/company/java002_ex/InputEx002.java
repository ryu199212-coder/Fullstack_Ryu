package com.company.java002_ex;

import java.util.Scanner;

public class InputEx002 {
	public static void main(String[] args) {
		//변수
		int num=0;
		Scanner scanner = new Scanner(System.in);
		
		//입력
		System.out.println("좋아하는 수(정수)   입력하시오 >");
		num = scanner.nextInt();
		
		//처리
		//출력
		System.out.println("좋아하는 숫자는 "+num+" 입니다.");
	}
}
/*
연습문제2)
패키지명 : com.company.java002_ex
클래스명 : InputEx002
출력내용 :  Scanner이용해서 좋아하는 수 입력받고 출력하시오.
    좋아하는 수(정수)   입력하시오 > _입력받기
    좋아하는 숫자는 ** 입니다.
*/
