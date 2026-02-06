package com.company.java006;

public class Arr001 {
	public static void main(String[] args) {
		//1. 변수 활용시
		int a=1, b=2, c=3;
		
		//2. 배열?
		// [같은타입]의 데이터를 [연속된 공간에 저장]하는 자료구조
		// 각 데이터의 저장위치 [인덱스]를 통해서 접근
		// 1) int [] arr 주소 보관 준비완료  2) null 공간은 있지만 값이 없음 
		int [] arr = null;
		System.out.println(arr);
		
		// [stack]arr2 주소 보관 = [heap] 1, 2, 3 연속된 공간에 저장
		int         [] arr2   = {1, 2, 3};
		//   arr2(1000번지}    → 1000번지{1, 2, 3}
		//                            [0][1][2]
		System.out.println(arr2);
		System.out.println("1꺼내쓰기 >" + arr2[0]);
		System.out.println("2꺼내쓰기 >" + arr2[1]);
		System.out.println("3꺼내쓰기 >" + arr2[2]);
		
		
		//3-1. 배열 예시 : arr3 1, 2, 3, 4, 5
		int [] arr3 = {1, 2, 3, 4, 5};//0~4
		System.out.println(arr3[2]);
		
		//3-2. 배열 예시 : arr4 100, 200, 300
		int [] arr4 = {100, 200, 300};//0~2
		System.out.println(arr4[0]);
		
		//3-3. 배열 예시 : arr5 1.1, 1.2, 1.3, 
		double [] arr5 = {1.1, 1.2, 1.3};
		System.out.println(arr5[2]);
		
		//3-4. 배열 예시 : arr6 'a', 'b', 'c'
		char [] arr6 = {'a', 'b', 'c'};
		System.out.println(arr6[0]);
		System.out.println(arr6[1]);
		System.out.println(arr6[2]);
		
		//4. 배열.length
		System.out.println("배열의 갯수 >" + arr6.length);
		//{반복} {변수} for(시작;종료;변화)
		for(int i=0; i<=2; i++) {System.out.println(arr6[i]);} 
		
		System.out.println();
		for(int i=0; i<3; i++) {System.out.println(arr6[i]);} 
		
		System.out.println();
		for(int i=0; i<arr6.length; i++) {System.out.println(arr6[i]);} //0, arr6.length 
		
		
		
		
		
		
		
	}//end main

}//end class
