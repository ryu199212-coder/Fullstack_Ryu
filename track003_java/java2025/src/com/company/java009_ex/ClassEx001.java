package com.company.java009_ex;
class Student001{
	
//1. 클래스는 부품객체
//2. 클래스는 상태(멤버변수)와 행위(멤버함수)	 
	 String name;  int no, kor, eng, math;
	 int total; 
	 double avg; // 멤버 변수
	 void info() {
		 System.out.println("이름 : " + this.name);
		 System.out.println("총점 : " + this.total );
		 System.out.println("평균 : " + this.avg );} // 멤버 함수
	}
//////////////////////////////////////////////////////////////////
public class ClassEx001 {
	public static void main(String[] args) {
		Student001   s1 = new Student001();
		// 1) new(1번지-객체생성) 2) Student001() 초기값 3) s1 주소
	    s1.name="first";  s1.no=11; s1.kor=100; s1.eng=100; s1.math=99;
	    s1.total = s1.kor+s1.eng+s1.math;
	    s1.avg = s1.total/3.0;
	    s1.info();
	    
	    
	    
		
	}

}
/*
-------------------------------------------[ runtime data area ]
[method : 정보, static, final : 공용정보]
   Student001.class, ClassEx001.class   1)
-------------------------------------------
[heap : 동적]                                         | [stack : 잠깐빌리기]
                                                      s1.info(){}
1번지{name=first, no=11, kor=100, eng=100, math=99}  ← s1[1번지]
                                                     | main 2)
-------------------------------------------




*/
