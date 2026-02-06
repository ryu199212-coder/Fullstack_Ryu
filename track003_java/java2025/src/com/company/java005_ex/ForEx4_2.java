package com.company.java005_ex;

public class ForEx4_2 {
	public static void main(String[] args) {
		
		String result="";
		int num = 0;
		
		for(int i = 1; i <= 10; i++) {if(i % 3 == 0)
		{num++; result += (i==3? "" : ",") + i;}
		}
		
		System.out.println("3의 배수: " + num);
		System.out.println("1부터 10까지의 3의 배수 개수는: " + result);

		
		
		
	
	

	}
}
/*연습문제9)  for/while/do while
패키지명 : com.company.java005_ex
클래스명 :  RepeatEx009
for , while , do while 3가지 버젼으로 
1~10까지 3의 배수의 합 : 18*/