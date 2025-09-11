package com.company.java006_ex;

public class ArrayEx003 {
	public static void main(String[] args) {

		String[] arr = { "아이언맨", "헐크", "캡틴" };

		for (int i = 0; i < arr.length; i++) {
			System.out.print((i == 0 ? "" : ",") + arr[i]);
		}

	}
}
/*
 * 연습문제3) array 패키지명 : com.company.java006_ex 클래스명 : ArrayEx003 1. 배열명 : arr 2.
 * 값 넣기 : "아이언맨" , "헐크" , "캡틴" 3. for + length 로 출력
 */