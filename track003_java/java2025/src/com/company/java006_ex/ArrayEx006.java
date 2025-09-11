package com.company.java006_ex;



public class ArrayEx006 {
	public static void main(String[] args) {
		

		char[] arr = {'A', 'B', 'C', 'D', 'E'};
		
		int[] arr2 = new int[5];	
	

		for (int i = 0; i < arr.length; i++) {
			System.out.print((i == 0 ? "" : ",") + arr[i]);
		}
		
		

	}
}
/*
 * 연습문제7)  array
패키지명 : com.company.java007_ex
클래스명 :  ArrayEx007
    new 연산자 이용해서 배열만들기
    1. 배열명 : arr     
    2. 값 넣기 : A   B   C   D   E    for+length 이용해보기
    3. for + length 로 출력
 */