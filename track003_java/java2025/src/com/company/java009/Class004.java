package com.company.java009;

// 1. 클래스는 부품객체
// 2. 클래스는 상태(멤버변수)와 행위(멤버함수)

/*
		 Object                        2)     Object  {}          3)
		  ↑
		Product{name=null, price=0}    1)    Product(){super()}   4)     Product p1 = new product();
 */
class Product{
	String name;
	int price;
	
	public Product() {super();}
	public Product(String name, int price) {super();this.name = name;this.price = price;}
	public String toString() {return "Product [name=" + name + ", price=" + price + "]";}
	
}
////////////////////////////////////////////////
public class Class004 {
	public static void main(String[] args) {
		Product p1 = new Product(); // 1) 공간빌리고 객체생성  2) 생성자 호출  3) p1 주소
		System.out.println(p1);

	}

}
////////////////////////////////////////////////
/*
--------------------------------------------- [runtime data area]
[method : 정보, static, final : 공용정보]         Product.class, Class004.class                 
---------------------------------------------                         
[heap : 동적]                    |[stack : 잠깐 빌리기] Product p1 = new Product();
					Object();
1번지{name=null, price=0}        ← p1[1번지]
                                | main
---------------------------------------------
*/







