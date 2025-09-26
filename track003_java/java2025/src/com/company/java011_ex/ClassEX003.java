package com.company.java011_ex;

class Grand extends Object {
    public String Grand;
	public void one() { System.out.println("grand : one"); }
    public void two() { System.out.println("grand : two"); }
    public Grand() { super(); }
    
	@Override
	public String toString() { return "Grand [Grand=" + Grand + "]"; }
	public String getGrand() { return Grand; }
}

//(1) Father 클래스가 Grand 클래스를 상속받도록 수정
class Father extends Grand{
	private String Father;
	public void three() { System.out.println("Father : three"); }
	public Father() { super(); }

	@Override
	public String toString() { return "Father [Father=" + Father + "]"; }
	}

class Uncle extends Grand{
	public void four() {System.out.println("Uncle : four");}
	public void one() {System.out.println("Uncle : one");}
	public void two() {System.out.println("Uncle : two");}
	
}

class Aunt{
	String name="mini";
	@Override
	public String toString() { return "Aunt [name=" + name + "]"; } //Object
	
}
public class ClassEX003 {
	public static void main(String[] args) {
		Grand grand = new Grand(); grand.one(); grand.two();
	    Father father = new Father(); father.three();
	    Uncle uncle = new Uncle(); uncle.four(); uncle.two(); uncle.one();
	    
	    //Q1. uncle124 grand12
	    //Q2. uncle124
	    //Q3. @Override 상속시 부모와 같은 메서드를 자식클래스에맞게 수정한것
	   
	}
}
		



/*
grand : one
grand : two
Father : three
*/