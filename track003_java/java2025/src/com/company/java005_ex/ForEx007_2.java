package com.company.java005_ex;

import java.util.Scanner;

public class ForEx007_2 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int kor=-1, mat=-1, eng=-1, total=0;
		double avg;
		char ch=0;
		String pass = null;
		String std;
		String level = null; 
		String sch = "";

		
		System.out.print("학번 입력 >"); std=scanner.next();
		
		for(;;) {
		if(!(kor>=0 && kor<=100)) {
			System.out.print("국어점수 입력 >"); kor=scanner.nextInt();
			continue;
			}
		
		if(!(eng>=0 && eng<=100)) { 
			System.out.print("영어점수 입력 >"); eng=scanner.nextInt();
			continue;
		    }
		
		if(!(mat>=0 && mat<=100)) {
			System.out.print("수학점수 입력 >"); mat=scanner.nextInt();
			continue;
		    }
		break;
		   }
		
	
		
		
	    total = kor+eng+mat;
	    avg = total/3.0;
	    pass = avg>=60 || kor>=40 || eng>=40 || mat>=40? "불합격" : "합격";
	    sch = avg<95? "장학생":"";
	    level = avg>=90? "수" : avg>=80? "우" : avg>=70? "미" : avg>=60? "양":"가";
	    
	    
	    
	    
	    
	    
	    System.out.println("========================================================");
		System.out.println("학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생");
		System.out.println("========================================================");
		System.out.printf("%s   %d   %d   %d   %d   %.2f   %s   %s   %s\n",
	                       std, kor, eng, mat, total, avg, pass, level, sch);
		
		
		
		
	}
	

}
/*클래스명 :  ForEx007
출력내용 :  성적처리 프로그램입니다.

1. 총점 구하기
2. 평균 구하기
3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
4. 평균이 95점이상이면 장학생
5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가 

학번 입력 > std111
국어점수 입력 > 100    ※ 0~100사이만입력받기
수학점수 입력 > 100    ※ 0~100사이만입력받기
영어점수 입력 > 99      ※ 0~100사이만입력받기

============================================================== 
학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
============================================================== 
std111   100   100   99   299   99.67   합격   수   장학생*/