package com.company.java005_ex;

public class ForEx005 {
	public static void main(String[] args) {
		
		int cnt = 0;
		
		//a가 모음(a,e,i,o,u) 체크
		//b가 모음(a,e,i,o,u) 체크
		//c가 모음(a,e,i,o,u) 체크
		
		//if(a가 모음(a,e,i,o,u)면 체크){카운트}
		//if(b가 모음(a,e,i,o,u)면 체크){카운트}
		//if(c가 모음(a,e,i,o,u)면 체크){카운트}
		
		//if('a'=='a' || 'a'=='e' || 'a'=='i' || 'a'=='o' || 'a'=='u"){cnt++}
//		if('a'=='a' || 'a'=='e' || 'a'=='i' || 'a'=='o' || 'a'=='u'){cnt++}
//		if('b'=='a' || 'b'=='e' || 'b'=='i' || 'b'=='o' || 'b'=='u'){cnt++}
//		if('c'=='a' || 'c'=='e' || 'c'=='i' || 'c'=='o' || 'c'=='u'){cnt++}
		
		for(char i ='a' ; i <='z'; i++){if(i =='a' || i =='e' || i =='i' || i =='o' || i =='u'){cnt++;}}
		
		System.out.println("a~z까지 모음의 개수는"+cnt);
		
	}

}


/*연습문제5)  
패키지명 : com.company.java005_ex
클래스명 :  ForEx005
출력내용 :   for 이용
소문자 a~z까지 모음의 갯수를 출력하시오. */