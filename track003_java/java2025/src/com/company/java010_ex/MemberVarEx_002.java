package com.company.java010_ex;
/*
class Student {
	//인스턴스 변수
    String name = "홍길동";        
    int kor = 90;                  
    int eng = 85;      
    
    //클래스 변수
    static int studentCount = 0;    

    static int total = kor + eng;   // 변수에 static 사용

    static int maxScore = 100;    
    
    //생성자
    public Student() {
        studentCount++;             
    }
    //인스턴스 메서드
    public int getTotalScore() {
        return this.kor + this.eng;        
    }
    //클래스 메서드
    public static void showStudentCount() {
        System.out.println("전체 학생 수: " + studentCount);  
    }
    public static void showName() {
         System.out.println(name);  // 변수에 static 사용 객체를 통해서가 아니라 클래스 이름을 통해 접근해야 한다
   }
    //인스턴스 메서드
    public void showInfo() {
        System.out.println("이름: " + name);            
        System.out.println("총점: " + getTotalScore());    
    }
}
public class MemberVarEx_002 {
	public static void main(String[] args) { // 지역변수
		Student s1 = new Student();
		Student s2 = new Student();

		s1.showInfo();
		Student.showStudentCount();
	}
}
/*
 * ------------------------[ runtime data area] 
 * [method: 정보, static, final                    
 * Student.class / MemberVarEx_002.class
 * static : student.studentCount, student.maxScore, student.showStudentCount(), student.showName()
 * ------------------------------------ 
 * [heap: 동적]                             | [stack : 잠깐빌리기]
 * 2번지
 * {name=null, kor=0, eng=0/ 
 * getTotalScore(), showInfo()             ← s2[2번지]
 * 
 * 1번지
 * {name=null, kor=0, eng=0/
 * getTotalScore(), showInfo()             ← s1[1번지]
 * 
 *                                         | main
 * ------------------------------------
 */