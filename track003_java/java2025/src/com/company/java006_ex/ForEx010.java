package com.company.java006_ex;

public class ForEx010 {
	public static void main(String[] args) {
		//
		for (char i = 'A'; i <= 'Z'; i++) {
			if (i % 5 == 0) {
				System.out.println();
			}
			System.out.print(i);
		}

		char i = 'A';
		while (i <= 'Z') {
			if (i % 5 == 0) {
				System.out.println();
			}
			System.out.print(i);
			;
			i++;
		}

		i = 'A';
		do {
			if (i % 5 == 0) {
				System.out.println();
			}
			System.out.print(i);
			;
			i++;
		} while (i <= 'Z');

	}

}
/*
 * 연습문제10) for/while/do while 패키지명 : com.company.java005_ex 클래스명 : RepeatEx010
 * for , while , do while 3가지 버젼으로 ABCDE FGHIJ KLMNO PQRST UVWXY Z
 */