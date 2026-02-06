package com.company.java007_ex;

public class Array2_Ex008 {
	public static void main(String[] args) {
		int[][] datas = {  {10, 10, 10 ,10}, 
	                       {20, 20, 20 ,20}, 
	                       {30, 30, 30 ,30},  
                      	};
		int data = 1;
		
		int[][] result = new int[datas.length+1][datas[0].length+1];
		
		for(int ch = 0; ch<datas.length; ch++) {
			for(int kan = 0; kan<result.length; kan++) {
				result[ch][kan]=datas[ch][kan];												
			}
		}
		//for(int kan=0; kan<result[0].length-1; kan++) {result[0][4] += result[0][kan];}
		//for(int kan=0; kan<result[1].length-1; kan++) {result[0][4] += result[1][kan];}
		//for(int kan=0; kan<result[2].length-1; kan++) {result[0][4] += result[2][kan];}
		
		//for(int ch=0; ch<result.length-1; ch++) {
			//for(int kan=0; kan<result[ch].length-1; kan++) 
			//{ result[3][kan] += result[ch][0];}
		//}
		
		for(int ch=0; ch<result.length-1; ch++) {
			for(int kan=0; kan<result[ch].length-1; kan++) 
			{ result[ch][4] += result[ch][kan];
			 result[3][kan] += result[ch][0];
			 result[3][4] += result[ch][kan];
			}
		}
		
		
		for(int ch = 0; ch<result.length; ch++ ) {
			for(int kan = 0; kan<result[ch].length; kan++) {
				System.out.print(result[ch][kan] + "\t");
			}
			System.out.println();
		}
		
	}

}
/*연습문제8)  array
패키지명 : com.company.java007_ex
클래스명 :  Array2Ex008
배열을 이용하여 다음의 프로그램을 작성하시오.   
1. 다음의 주어진조건을 이용하여 총점과 평균을 구하시오.

int[][] datas = {  {  10, 10, 10 ,10}, 
            {  20, 20, 20 ,20}, 
            {  30, 30, 30 ,30},  
};  // 3층 4칸 
int[][] result = new int[datas.length+1][datas[0].length+1];

#1. result 에 datas데이터 복사하기
#2. 가로방향누적데이터
#3. 세로방향데이터누적
#4. 총합

출력내용:
10   10   10   10   40   
20   20   20   20   80   
30   30   30   30   120   
60   60   60   60   240   

 */