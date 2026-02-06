package com.company.java008;

import java.util.Arrays;

public class Method004 {
	//오버로딩? 같은 목적으로 비슷한 동작을 수행하는 메서드들의 [이름을 일관]되게 작업
	//       [파라미터의 타입]과 [갯수]로 메서드 구분
	
	public static void show(){System.out.println("마법상자");}
	
	public static void show(int a){System.out.println("무조건 *10>"+ a + ":" + a*10);}
	
	public static void show(int a, int b, int c) {
		System.out.println("무조건 *100>"+ a + ":" + a*100);
	    System.out.println("무조건 *100>"+ b + ":" + b*100);
	    System.out.println("무조건 *100>"+ c + ":" + c*100);}
	
	
	//////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// arr보관[주소:1000번지] = [1000번지]{1,2,3}
		int [] arr    = {1,2,3}; // 3 : 0~2
		// public static 리턴값 메서드명(파라미터){해야할일}
		// public static void show(){}
		show();  // public static void show(){}
		show(1); // [값] public static void show(int a){10배}
		show(arr[1]); // [값] show(2)
		show(1,2,3); // [값] {100배}
		show(arr[0],arr[1],arr[2]); // [값] show(1,2,3)
		show(arr); // public static void show(int [] arr){}
		System.out.println("main : "+ Arrays.toString(arr));
	}
	//////////////////////////////////////////////////////////////////////

	private static void show(int[] arr) { // 배열에 접근해서. 주소를 따라가서 1000배
		for(int i = 0; i < arr.length; i++) {
			arr[i]*=1000;
		}
		System.out.println("무조건 *1000 >"+ 
		System.identityHashCode(arr) + Arrays.toString(arr));
		// TODO Auto-generated method stub
		
	}
}
