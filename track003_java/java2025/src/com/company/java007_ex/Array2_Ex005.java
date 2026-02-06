package com.company.java007_ex;

public class Array2_Ex005 {
	public static void main(String[] args) {
		int[][] arr2 = { { 1, 1, 1,},
				         { 2, 2, 2,},
				         { 3, 3, 3,},
				         { 4, 4, 4,},};

		int total = 0;
		double avg = 0.0;
		
		
		for(int ch=0; ch<arr2.length; ch++) {
			for(int kan=0; kan<arr2[ch].length; kan++) {
				total+=arr2[ch][kan];
			}
		}
		
		avg=(double)total/(arr2.length*arr2[0].length);
		
		//for(int ch=0; ch<arr2.length; ch++) {
			//for(int kan=0; kan<arr2.length; kan++) {
				System.out.println("총점 : " + total);
		        System.out.println("평균 : " + avg);
		//	}
		//}
		
	
		

	}

}
/*연습문제5)  array
패키지명 : com.company.java007_ex
클래스명 :  Array2Ex005
배열을 이용하여 다음의 프로그램을 작성하시오.   
1. 다음의 주어진조건을 이용하여 총점과 평균을 구하시오.

 int[][] arr = {
   { 1, 1, 1,},
   { 2, 2, 2,},
   { 3, 3, 3,},
   { 4, 4, 4,},
 };
 int total=0;  double avg=0.0;

출력내용:
총점 : 30
평균 : 2.5
 */