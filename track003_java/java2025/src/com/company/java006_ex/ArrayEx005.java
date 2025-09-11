package com.company.java006_ex;

public class ArrayEx005 {
	public static void main(String[] args) {
		int upper = 0, lower = 0;

		char[] arr = { 'B', 'a', 'n', 'a', 'n', 'a' };

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] >= 'A' && arr[i] <= 'Z') {
				upper++;
			} else if (arr[i] >= 'a' && arr[i] <= 'z') {
				lower++;
			}
		}
		System.out.println("대문자의 갯수는 >" + upper);
		System.out.println("소문자의 갯수는 >" + lower);

	}

}
/*
 * 연습문제5) array 패키지명 : com.company.java006_ex 클래스명 : ArrayEx005 1. 배열명 : ch 2. 값
 * 넣기 : 'B' , 'a' , 'n' , 'a', 'n' , 'a' 3. ch 배열에서 대문자의 갯수카운트, 소문자의 갯수 카운트
 */