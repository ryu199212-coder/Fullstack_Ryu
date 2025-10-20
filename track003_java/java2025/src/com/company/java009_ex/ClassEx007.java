package com.company.java009_ex;

import java.util.Scanner;

class Calc {
   int num1, num2;
   char op;
   double result;



   public Calc(int num1, int num2, char op) {
      super();
      this.num1 = num1;
      this.num2 = num2;
      this.op = op;
   }

   public Calc() {
      super();
      // TODO Auto-generated constructor stub
   }

   void input() {
      Scanner scanner = new Scanner(System.in);
      System.out.print("\n숫자1 > ");
      this.num1 = scanner.nextInt();
      System.out.print("숫자2 > ");
      this.num2 = scanner.nextInt();
      System.out.print("연산자 > ");
      this.op = scanner.next().charAt(0);
   }

   void opcalc() {
      if (this.op == '+') {
         this.result = this.num1 + this.num2;
      } else if (this.op == '-') {
         this.result = this.num1 - this.num2;
      } else if (this.op == '*') {
         this.result = this.num1 * this.num2;
      } else if (this.op == '/') {
         this.result = this.num1 / this.num2;
      } else {
         System.out.println("");
      }
   }

   void show() {
      opcalc();
      System.out.printf("%d %s %d = %.2f", this.num1, this.op, this.num2, this.result);
   }
}

public class ClassEx007 {
   public static void main(String[] args) {
      Calc c1 = new Calc(10, 3, '+');
      c1.show();
      Calc c2 = new Calc();
      c2.input();
      c2.show();

   }
}
/*연습문제7)  class
패키지명 : com.company.java009_ex
클래스명 :  ClassEx007
-- 생성자 작성하시오.
class Calc{
   //상태-멤버변수  :  int num1, num2;  char op;  double result;
   //행위-멤버함수  :  void input()   입력받기
   //               void opcalc() +더하기계산, -라면 -계산  , *라면 *계산 , /라면 /계산 
   //                      void show()    연산출력   
}
public class ClassEx007{
   public static void main(String[] args) {
   Calc  c1= new Calc(10,3,'+');  
   c1.show();
   
   Calc  c2= new Calc();  
   c2.input();   
   c2.show(); 
    
   }
}

출력내용)
10+3=13

숫자1> 10
숫자2> 3
연산자> /
10/3=3.33*/