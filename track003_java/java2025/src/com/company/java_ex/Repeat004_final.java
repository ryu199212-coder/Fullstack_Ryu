package com.company.java_ex;

//옵션1. division, jumin 바뀌면 안됨
//옵션2. 각 유저마다 데이터 저장

//1. 클래스는 부품객체
//2. 상태와 행위
//3. 상태 - 인스턴스변수★, 클래스변수(static), 지역변수
//4. 바뀌면 안됨 -  static, final- 하지마★ (멤버변수-상수 , 멤버메서드-오버라이딩하지마, 클래스-상속받지마)
/*	초기화순서 		초기값		명시적초기화			초기화블록			생성자
		division	\u0000      x 유지 \u0000		x 유지 \u0000	처리	User004('B' , "200101-1234567") O 
		jumin		null        x 유지 null          x 유지 null		처리	User004()  \u0000, null
 */
//////////////////////////// 1단계설계도
class User004{  
   final char    division;     
   final String  jumin;        
   
   public User004(char division, String jumin) { super(); this.division = division; this.jumin = jumin; }
   public User004() { super(); 
	   this.division = 'A';
	   this.jumin = "123456-1234567";
   }  // 필드있는생성자를 만들면 자동생성이 깨짐 + final 이 붙어서 초기화를 해줘야함.
   @Override public String toString() { return "User004 [division=" + division + ", jumin=" + jumin + "]"; }
}
//////////////////////////// 
public class Repeat004_final {
	  public static void main(String[] args) {
			User004 c1 = new User004('B' , "200101-1234567");  //2단계  장난감만들기
			System.out.println(c1);
			User004 c2 = new User004();   // 2단계 장난감만들기
			System.out.println(c2);
		  }
}
 