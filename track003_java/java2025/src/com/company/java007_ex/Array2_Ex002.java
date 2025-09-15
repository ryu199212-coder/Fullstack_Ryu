package com.company.java007_ex;

public class Array2_Ex002 {
	public static void main(String[] args) {
		int[][] arr2 = { { 101, 102, 103 }, { 201, 202, 203 } };

		System.out.println("ver-1");
		System.out.print(arr2[0][0] + "\t"); System.out.print(arr2[0][1] + "\t"); System.out.print(arr2[0][2] + "\t");
		System.out.println();
		System.out.print(arr2[1][0] + "\t"); System.out.print(arr2[1][1] + "\t"); System.out.print(arr2[1][2] + "\t");
		System.out.println();

		System.out.println("ver-2");
		
		for (int a = 0; a <= 2; a++) {
			System.out.print(arr2[0][a] + "\t");
		}System.out.println();
		for (int a = 0; a <= 2; a++) {
			System.out.print(arr2[1][a] + "\t");
		}System.out.println();


		System.out.println("ver-3");
		for (int b = 0; b <2; b++) {
			for (int a = 0; a <3; a++) {
				System.out.print(arr2[b][a] + "\t");
			}
			System.out.println();
		}
			

		System.out.println("ver-4");
		for (int b = 0; b < arr2.length; b++) {
			for (int a = 0; a < arr2[b].length; a++) {
				System.out.print(arr2[b][a] + "\t");
			}
			System.out.println();
		}
		

	}

}
/*
 * 연습문제2) array 패키지명 : com.company.java007_ex 클래스명 : Array2Ex002 배열을 이용하여 다음의
 * 프로그램을 작성하시오.
 * 
 * int[][] arr2={{101,102,103},{201,202,203}};
 * 
 * 이중for 이용해서 출력하기
 */