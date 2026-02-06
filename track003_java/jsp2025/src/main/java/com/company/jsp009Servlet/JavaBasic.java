package com.company.jsp009Servlet;

public class JavaBasic {
	private int a;
	private int b;
	

	// 생성자 / toString / getters + setters
	public JavaBasic() { super();  }
 

	public JavaBasic(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	@Override public String toString() { return "JavaBasic [a=" + a + ", b=" + b + "]"; }
	
	public int getA() { return a; } public void setA(int a) { this.a = a; }
	public int getB() { return b; } public void setB(int b) { this.b = b; } 
	
	public int add() { return a+b; }

}
