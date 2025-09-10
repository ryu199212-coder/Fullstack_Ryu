package com.company.java006_ex;

public class ForEx008_2 {
	public static void main(String[] args) {

		for (int i = 5; i >= 1; i--) {
			System.out.println(i + "\t");
		}

		int i = 5;
		while (i >= 1) {
			System.out.println(i + "\t");
			i--;
		}

		i = 5;
		do {
			System.out.println(i + "\t");
			i--;
		} while (i >= 1);

	}

}

/*
 * 연습문제8) for, while, do while 패키지명 : com.company.java005_ex 클래스명 : ForEx008 1.
 * for , while , do while문을 이용해서 다음과 같이 출력하시오 : 1 2 3 4 5 2. for , while , do
 * while문을 이용해서 다음과 같이 출력하시오 : 5 4 3 2 1 3. for , while , do while문을 이용해서 다음과 같이
 * 출력하시오 : JAVA1 JAVA2 JAVA3
 */