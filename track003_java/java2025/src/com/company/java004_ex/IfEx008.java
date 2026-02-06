package com.company.java004_ex;

import java.util.Scanner;

public class IfEx008 {
	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	
	int kor, mat, eng;
	int total;
	double avg;
	String pass = null;
	String std;
	String level = null; 
	String sch = "";

	
	System.out.print("학번을 입력하시오"); std=scanner.next();
	System.out.print("국어점수"); kor=scanner.nextInt();
	System.out.print("수학점수"); mat=scanner.nextInt();
	System.out.print("영어점수"); eng=scanner.nextInt();
	
	total = kor+mat+eng;
	avg = total/3.0;
	
	if(avg>=60 && kor>=40 && mat>=40 && eng>=40) {pass="합격";}
	else{System.out.println("불합격");}
	
	if(avg>=90) {level="수";}
	else if(avg>=80){level="우";}
	else if(avg>=70){level="미";}
	else if(avg>=60){level="양";}
	else{System.out.println("가");}
	
	if(avg>=95) {sch="장학생";}
	
	

	System.out.println("========================================================");
	System.out.println("학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생");
	System.out.println("========================================================");
	System.out.printf("%s   %d   %d   %d   %d   %.2f   %s   %s   %s\n",
                       std, kor, eng, mat, total, avg, pass, level, sch);
	
	
}

}

/*연습문제8)  
패키지명 : com.company.java004_ex
클래스명 :  IfEx008
출력내용 :  성적처리 프로그램입니다.

1. 총점 구하기
2. 평균 구하기
3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
4. 평균이 95점이상이면 장학생
5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가 

학번 입력 > std111
국어점수 입력 > 100
수학점수 입력 > 100
영어점수 입력 > 99
=================================================================================== 
학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
=================================================================================== 
std111   100   100   99   299   99.67   합격   수   장학생
*/