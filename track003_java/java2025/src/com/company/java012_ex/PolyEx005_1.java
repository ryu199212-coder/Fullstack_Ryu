package com.company.java012_ex;
/*
 * 
 *     Object
 *       ↑
 *     Parent7 {x=100, method}
 *       ↑
 *     Child7 {x=200, @method}
*/
class Parent7  extends Object{
   int x = 100;
   public Parent7() { super(); }
   void method() { System.out.println("Parent Method"); }
} 
class Child7 extends Parent7 {
   int x = 200;
   public Child7() { super(); }
   @Override  void method() { System.out.println("Child Method"); }
   void papaMethod() {super.method();}
}
public class PolyEx005_1 {
   public static void main(String[] args) {
      Parent7 p = new Child7();     
      // Q3.  Parent7 p   보장하는 범위 {x=100, method} 
      // Q4. 인스턴스화 했을때 사용가능한 범위 : new Child7()  {x=200, @method}-{x=100, method} 
      
                            Child7 c = new Child7();     
      System.out.println("p.x = " + p.x);  // Q5. 출력되는 내용   100
      p.method();  // Q6. 출력되는 내용     Child Method
      System.out.println("c.x = " + c.x);   // Q7. 출력되는 내용  200
      c.method();   // Q8. 출력되는 내용    Child Method
      
      // Q9. main 에서 부모메서드 호출하기? - Parent Method
      ((Parent7)p).method(); // 오버라이딩 된 메서드를 직접 호출하는건 불가능
      c.papaMethod();
      ((Child7)p).papaMethod(); // 타입캐스팅 - 부모가 자식메서드 호출가능(자식생성자 호출시)
   }
}


