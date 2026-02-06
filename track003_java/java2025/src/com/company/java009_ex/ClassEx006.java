package com.company.java009_ex;

import java.util.Scanner;

class Score{
	   String stdid; int kor,eng,math,total,avg;   //상태-멤버변수  
	   int total() {return this.kor+this.eng+this.math;} //행위-멤버함수   
	   double avg() {return (this.kor+this.eng+this.math)/3.0;}  //               
	   void info() {Scanner scanner = new Scanner(System.in);
	   
		   System.out.println("학번      kor     eng    math    total   avg");
		   System.out.println(this.stdid+"\t"+this.kor+"\t"+this.eng+"\t"+this.math+"\t"+this.total()+"\t"+this.avg());
	   } // 학생정보출력  ※힌트2)  info(){    total();  avg();     }  다른메서드에서 메서드 사용가능  
	   public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	   public Score(String stdid, int kor, int eng, int math) {
		super();
		this.stdid = stdid;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = total;
		this.avg = avg;
	}
	   @Override
	   public String toString() {
		return "Score [stdid=" + stdid + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", total=" + total
				+ ", avg=" + avg + "]";
	   }
}
public class ClassEx006 {
		   public static void main(String[] args) {
			   Score  s1= new Score("std1234" , 100, 100 , 99 ); 
			   s1.stdid="std1234";   s1.kor=100; s1.eng=100; s1.math=99; 
			   s1.info();
			   }
			}
/*연습문제6)  class
패키지명 : com.company.java009_ex
클래스명 :  ClassEx006
-- 생성자 작성하시오.
class Score{
   //상태-멤버변수  :  String stdid; int kor,eng,math,total,avg;   
   //행위-멤버함수  :  void total() 총점구해주기
   //               void avg()  평균구하기
   //                      void info()   학생정보출력  ※힌트2)  info(){    total();  avg();     }  다른메서드에서 메서드 사용가능  
   //※ 힌트1) 생성자 :   Score() / Score(stdid, kor, eng, math)
}
public class ClassEx006{
   public static void main(String[] args) {
   Score  s1= new Score("std1234" , 100, 100 , 99 ); 
   s1.info();
   }
}

출력내용 :
학번   kor   eng   math   total   avg
std1234   100   100   99   299   99.67*/