package com.company.java_ex;

public class Coffee012 {
    String name;
    int num;
    int price;

public Coffee012(String name, int num, int price) {
    this.name = name;
    this.num = num;
    this.price = price;
}

public Coffee012() {
    this.name = "아메리카노";
    this.num = 1;
    this.price = 2000;
}

public void show() {	
    System.out.println("=====커피");
    System.out.println("커피명: " + this.name);
    System.out.println("커피잔수: " + this.num);
    System.out.println("커피가격: " + this.price + "원");
}


public static void main(String[] args) {
    Coffee012 a1 = new Coffee012("카페라떼", 2, 4000);
    a1.show();

    Coffee012 a2 = new Coffee012();
    a2.show();
   
    }
}
