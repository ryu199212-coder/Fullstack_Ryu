package com.company.java004_ex;

import java.util.Scanner;

public class IfEx008_1 {
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
	
	pass = (avg>=60 && kor>=40 && mat>=40 && eng>=40) ? "합격":"불합격";
	
	level = (avg>=90) ? "수": 
	        (avg>=80) ? "우":
	        (avg>=70) ? "미" :
	        (avg>=60) ? "양" : "가";
	
	
	sch = (avg>=95) ? "장학생": "";
	
	

	System.out.println("========================================================");
	System.out.println("학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생");
	System.out.println("========================================================");
	System.out.printf("%s   %d   %d   %d   %d   %.2f   %s   %s   %s\n",
                       std, kor, eng, mat, total, avg, pass, level, sch);

}
}
