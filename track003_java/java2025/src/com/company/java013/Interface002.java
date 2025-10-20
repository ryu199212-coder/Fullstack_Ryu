package com.company.java013;

//1. 클래스는 단일상속
/*	Papa2{brain}		Mama2{brain}
				↑		↑
     			  Son()

*/
class  Papa2{int brain;}
class  Mama2{int brain;}
//class  Son2 extends Papa2, Mama2{}   //Syntax error on token ",", . expected



//2. 인터페이스는 다중상속	
 
/*									{method()}			{method()}
	    						<<interface>>Inter21   <<interface>>Inter22  
* 											↑			 ↑   (실선 + 속이빈화살표)
		<<interface>>Inter20 	 	<<interface>>Inter23  {method()} 
			 ⋯⋯⋯△                          ⋯⋯⋯△		
		<<class>>Papa2									
(실선 + 속이빈화살표)↑   
	           Using1    extends Papa2  implements Inter23 , Inter20	
	     - {@method()} 	
	     - { @inter()} 							
*/



interface Inter20{ void inter();  }
interface Inter21{ void method(); }
interface Inter22{ void method(); }
interface Inter23 extends Inter21, Inter22 {}

//3. 많이 쓰는 형식    주요메인클래스상속,            추가설계1, 추가설계2  
class Using1 extends Papa2           implements Inter23 , Inter20{
	@Override public void method() {  }
	@Override public void  inter() {  }
}

public class Interface002 {
	public static void main(String[] args) {
		//Inter23 my = new Inter23();
		Using1 my = new Using1();
		
		
		// instanceof  클래스확인, 상속확인 ( 내부모? 족보확인)
		if(my instanceof Object) { System.out.println("1. Object"); }
		if(my instanceof Papa2)  { System.out.println("2. Papa"); }
		//if(my instanceof Mama2)  { System.out.println("3. Mama"); }
		// Incompatible conditional operand types Using1 and Mama2
		if(my instanceof Inter20){ System.out.println("4. Inter20"); }
	}
}









