package com.company.java007_ex;

public class Array2_Ex003 {
	public static void main(String[] args) {
		int[][] arr2 = new int[2][3]; // 101 102 103
										// 104 105 106

		// arr2[0][0] = 101; arr2[0][1] = 102; arr2[0][2] = 103;

		// int data=1;
		// arr2[0][0] = data++; arr2[0][1] = data++; arr2[0][2] = data++;
		// arr2[1][0] = data++; arr2[1][1] = data++; arr2[1][2] = data++;

		int data = 101;

		for (int ch = 0; ch < arr2.length; ch++) {
			for (int kan = 0; kan < arr2[ch].length; kan++) {
				arr2[ch][kan] = data++;
			}
		}

		for (int ch = 0; ch < arr2.length; ch++) {
			for (int kan = 0; kan < arr2[ch].length; kan++) {
				System.out.print(arr2[ch][kan] + "\t");
			}
			System.out.println();
		}

	}

}
/*
 * 연습문제3) array 패키지명 : com.company.java007_ex 클래스명 : Array2Ex003 배열을 이용하여 다음의
 * 프로그램을 작성하시오. 1. new 연산자 이용하여 다차원배열만들기 2. for + length 이용해서 대입 3. for + length
 * 이용해서 출력 101 102 103 104 105 106 };
 */