package com.company.java005_ex;

public class ForEx4_2 {
	public static void main(String[] args) {
		
		String result="";
		int num = 0;
		
		for(int i = 1; i <= 10; i++) {if(i % 3 == 0)
		{num++; result += (i==3? "" : ",") + i;}
		}
		
		System.out.println("3의 배수: " + num);
		System.out.println("1부터 10까지의 3의 배수 개수는: " + num);

		
		
		
	
	

	}
}
/*연습문제4)  
패키지명 : com.company.java005_ex
클래스명 :  ForEx004
출력내용 :   for 이용
1~10까지 3의 배수 갯수를 출력   

upgrade)  시간나면 도전!
3의배수 : 3,6,9    
갯수 : 3개*/