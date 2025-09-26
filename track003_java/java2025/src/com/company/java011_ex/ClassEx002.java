package com.company.java011_ex;
/* 클래스의 재사용
Object      {         }
  ↑
MobileNote   { show() }
  ↑
MobileNote7   { iris,face / newShow() } 
  ↑
MobileNote8   { ◎face     / ◎newShow() }  
  ↑
MobileNote9   { battery   / ◎newShow() } 

----------------------------------------------
MobileNote8 my8 = new MobileNote8();
----------------------------------------------

----------------------------------------------
MobileNote7 my7 = new MobileNote7();
----------------------------------------------
1.     MobileNote7  는    MobileNote 이다.
2.     생성자호출순서 :  MobileNote7()  → MobileNote()  → Object()
3.     객체생성순서  :  Object → MobileNote → MobileNote7
    1번지 : { iris,face / newShow() } - { show() }
    
*/

	

public class ClassEx002 {
	public static void main(String[] args) {
        /*MobileNote7 my7 = new MobileNote7();
        my7.setIris("brown");
        my7.setFace("pretty");
        my7.newShow();

        MobileNote8 my8 = new MobileNote8();
        my8.setFace("pretty");
        my8.newShow();

        MobileNote9 my9 = new MobileNote9();
        my9.setBattery(24);
        my9.newShow();*/
		MobileNote9 my92 = new MobileNote9();
		my92.setIris("black");
		my92.setFace("cuty");
		my92.setBattery(24);
		my92.newShow();
    }
}
/*NOTE7 객체 기능(Overriding)
iris = brown
face = pretty

NOTE8 객체 기능(Overriding) 추가
face = pretty

NOTE9 객체 기능(Overriding) 추가
battery 예쁘게 사용하기!
battery = 24

4. 클래스 구조 설명
클래스명      멤버변수        멤버메서드
MobileNote   없음                        void show()
MobileNote7   String iris, String face  void newShow()
MobileNote8   String face                 void newShow()
MobileNote9   int battery                 void newShow()
모든 멤버변수는 private으로 선언*/

