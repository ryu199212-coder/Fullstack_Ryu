package com.company.java006;


public class Arr002 {
	public static void main(String[] args) {
		//1. new
		// 주소 담을꺼야  1000번지 - 같은자료형 연달아서 저장
		// arr(1000번지)        [0][1][2]
		int [] arr =           {1,2,3};
		
		int [] arr2 = new int[3]; //new 공간 빌리기 int형태의 자료형 n개
			
		//arr2[0] = 10; arr2[1] = 20; arr2[2] = 30; // for로 줄이기
		int data = 10;
		//arr2[0] = data;/*10*/ data+=10;/*20*/
		//arr2[1] = data;/*20*/ data+=10;/*30*/
		//arr2[2] = data;/*30*/ data+=10;/*40*/
		
		for(int i = 0; i<arr.length; i++){arr2[i] = data; data+=10;}
		
		
		for(int i=0; i<arr2.length; i++){System.out.print(arr2[i] + "");}
		
		
	}

}
