package com.company.java006_ex;

public class ArrayEx004 {
	public static void main(String[] args) {
		int cnt = 0;

		char[] arr = { 'B', 'a', 'n', 'a', 'n', 'a' };

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 'a') {
				cnt++;
			}
		}

		System.out.println("a의 갯수는" + cnt);

	}
}
/*
 * 연습문제4) array 패키지명 : com.company.java006_ex 클래스명 : ArrayEx004 1. 배열명 : ch 2. 값
 * 넣기 : 'B' , 'a' , 'n' , 'a', 'n' , 'a' 3. ch 배열에서 a의 갯수 세기
 */
