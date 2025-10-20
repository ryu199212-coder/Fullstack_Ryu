package com.company.java011_ex;

import java.util.Arrays;

import com.company.java011_ex.Apple;

public class ClassArrEx1 {
	public static void main(String[] args) {
	        Apple[] apples= new Apple[3]; 
	        
	        apples[0] = new Apple("RED", "iron", 2, 1000);
	        apples[1] = new Apple("GREEN", "hulk", 1, 1500);
	        apples[2] = new Apple("GOLD", "captain", 3, 2000);
	        
	        System.out.println("ver-1 for");
	        for(int i = 0; i<apples.length; i++) {System.out.println(apples[i]);}
	        
	        System.out.println("\nver-2 향상된 for");
	        // 한개씩 : 배열, 객체명
	        for(Apple a : apples) {System.out.println(a);}
	        
	        Apple[] apples2 = new Apple[] {
	        		new Apple("RED", "iron", 2, 1000),
	        		new Apple("GREEN", "hulk", 1, 1500),
	        		new Apple("GOLD", "captain", 3, 2000)
	        };
	        System.out.println(Arrays.deepToString(apples2));
	       

	}
}
/*ㅁ출력된 화면
Apple [name=RED, order=iron, num=2, price=1000]
Apple [name=GREEN, order=hulk, num=1, price=1500]
Apple [name=GOLD, order=captain, num=3, price=2000]*/