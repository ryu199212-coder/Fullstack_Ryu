package com.company.java010_ex;

class Sawon3{ 
	
	//인스턴스 변수
    static int pay=10000; //heap area - new O - 생성자// 클래스 변수 / 접근해서 쓰려면 static
    //클래스 변수 - method area - new X - 생성자 X
    static int su=10;  
    static int basicpay=pay;    
    static int basicpay2; 
    
    
    
    //클래스 메서드
    public static void showSu() {   System.out.println(su);  } 
    public void showPay() {   System.out.println(Sawon3.pay/*this.pay*/);  }    // Static 에서는 this 사용불가 객체없이 호출
    	              // "Sawon3.pay"는 클래스(static)변수니까, 객체를 통해서가 아니라 클래스 이름을 통해 접근해야 한다
    //인스턴스 메서드
    public  void  showAll001() {   
       System.out.println(su);  // 메모리에 static 올라가있어서 this 사용 가능
       System.out.println(Sawon3.pay/*this.pay*/);  // this 사용 가능
    } 
    //클래스 메서드
    public void  showAll002() {   
        showAll001();    
       System.out.println(Sawon3.pay/*this.pay*/); 
    } 
} 

public class MemberVarEx_001 {	
	  public static void main(String[] args) {
			Sawon3   sola = new Sawon3();  
			sola.showAll001();
		}
}
/*
------------------------[ runtime data area]
[method: 정보, static, final : 공용정보]
>Sawon3.class / MemberVarEx_001.class
>static : Sawon3.su, Sawon3.basicpay2, Sawon3.showSu(), Sawon3.showA11002()
------------------------------------
[heap: 동적]               | [stack : 잠깐빌리기]
                            showAll001();
1번지{pay=0. showA11001()} ← sola[1번지] 32번째 줄
                          | main
------------------------------------
*/