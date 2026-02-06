package com.company.java007_ex;

public class Array2_Ex004 {
	public static void main(String[] args) {
		char[][] arr2 = new char[2][3]; // 101 102 103
										// 104 105 106


		char data = 'A';

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
/*연습문제4)  array
패키지명 : com.company.java007_ex
클래스명 :  Array2Ex004
배열을 이용하여 다음의 프로그램을 작성하시오.   
1. new 연산자 이용하여 다차원배열만들기
2. for + length 이용해서 대입   
3. for + length 이용해서 출력 
   A   B   C
   B   C   D
 */