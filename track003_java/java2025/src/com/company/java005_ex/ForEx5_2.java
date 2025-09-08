package com.company.java005_ex;

public class ForEx5_2 {
	public static void main(String[] args) {
		
		int cnt = 0;
		
		for (char i = 'a'; i <= 'z'; i++) {
			switch (i) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				cnt++;
			}

			

		}
		System.out.println("소문자 a~z까지 모음의 갯수 >" + cnt);
	}

}

/*
 * 연습문제5) 패키지명 : com.company.java005_ex 클래스명 : ForEx005 출력내용 : for 이용 소문자 a~z까지
 * 모음의 갯수를 출력하시오.
 */