package com.company.java_ex;

class Sawon005{   
	 //(1) 인스턴스변수 - heap, new O, 실체화, this 각각, 생성자  [2. new해서 객체만들어서 사용]
	   int pay      =10000;      
	 //(2) 클래스변수 - method , new X , [1. 바로사용가능]
	   static int su=10;  // 명시적초기화
	   //static int basicpay=pay;   // basicpay 메모리상에 올라가 있음. pay는 new해서 사용해야함. 시점이 안맞음
	   // ★ static에서는 instance 사용불가 -  시점이 안맞음
	   
	   static int basicpay2;
	   static {  basicpay2=20000; }  //초기화블록
	 
	 //(3)  클래스메서드 - method  , new X , [1. 바로사용가능]
	   public static void showSu() {   System.out.println(su);  }  
	   
	 //(4)  인스턴스메서드 - heap, new O, 실체화, this 각각, 생성자  [2. new해서 객체만들어서 사용]
	   public  void  showAll001() {   
	       System.out.println(su);  // 클래스변수 사용가능
	       System.out.println(this.pay); // 인스턴스 변수 사용가능
	   } 
	 //(5)  클래스메서드 - method  , new X , [1. 바로사용가능]	   
	   public static  void  showAll002() {
	       //showAll001();      // ★ static에서는 instance 사용불가 -  시점이 안맞음  
	       //System.out.println(this.pay);  // ★ static에서는 instance 사용불가 -  시점이 안맞음
	   } 
} 

public class Repeat003_member {
	public static void main(String[] args) {

		   Sawon005  sola = new Sawon005();  
		   sola.showAll001();
			//1. 사용방법 : 설계도 - 장난감조립(new 생성자불러서) - 갖고놀기
			//2. 위의 사용방법 룰이 깨짐.
		   Sawon005.showAll002();  // 메모리상에 올라가있음.
	}
}
