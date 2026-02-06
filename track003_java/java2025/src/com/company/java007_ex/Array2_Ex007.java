package com.company.java007_ex;

public class Array2_Ex007 {
	public static void main(String[] args) {
		int[][] arr = new int[4][4];
		
		int data = 1;
		
		for(int ch = 0; ch<arr.length; ch++) {
			for(int kan = 0; kan<arr[ch].length; kan++) {
				arr[ch][kan] = data++;
			}
			data=1;
		}
		
		for(int ch = 0; ch<arr.length; ch++) {
			for(int kan = 0; kan<arr[ch].length; kan++) {
			System.out.print(arr[ch][kan] + "\t");
				
			}
			System.out.println();
		}
	
		
		
	
		

	}

}
/*연습문제7)  array
패키지명 : com.company.java007_ex
클래스명 :  Array2Ex007
> 2차원배열   4*4
1.  new 로 만들고 
2.  데이터 넣기  for+length
3.  데이터 출력  for+length
1   2   3   4
1   2   3   4
1   2   3   4
1   2   3   4
 */