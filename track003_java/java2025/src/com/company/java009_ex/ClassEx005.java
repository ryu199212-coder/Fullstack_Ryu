package com.company.java009_ex;

import java.util.Scanner;

class Card{
      int cardNum; 
      boolean isMembership;      
      
      @Override
   public String toString() {
      return "Card [cardNum=" + cardNum + ", isMembership=" + isMembership + "]";}
      
   public Card() {super();}
   

}
   public class ClassEx005{
      public static void main(String[] args) {
      Card  c1= new Card(); 
      System.out.println(c1);  
      }
   }
/*습문제5)  class
패키지명 : com.company.java009_ex
클래스명 :  ClassEx005
-- 생성자 작성하시오.
class Card{
   //상태-멤버변수  : 채널/볼륨 int cardNum; boolean  isMembership;   
   //행위-멤버함수  : 채널, 볼륨 입력: input() / 출력 : show()
}
public class ClassEx005{
   public static void main(String[] args) {
   Card  c1= new Card(); 
   System.out.println(c1);  
   }
}

출력내용 :
Card[cardNum=0, isMembership=false]
*/