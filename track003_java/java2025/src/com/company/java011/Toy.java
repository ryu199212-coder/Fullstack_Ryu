package com.company.java011;

class Toy{
	//상태 : 멤버 변수
	private String name;  // 인스턴스변수 - heap area - new O - 생성자 O - this(각각)
	private int age;   // 인스턴스변수 - heap area - new O - 생성자 O - this(각각)
	static final String company="(주) 703toyland"; // 클래스.변수, - method - new X - (now)
	static int  num;	
	//행위 : 멤버 함수
	public void show() {
		System.out.println("Name : " + this.name);
		System.out.println("Age : " + this.age);
	}
	public String getName() { return name; }   public void setName(String name) { this.name = name; }
	public int getAge() { return age; }     public void setAge(int age) { this.age = age; }
	
	public Toy() {super();}
	public Toy(String name, int age) {super();this.name = name;this.age = age;}
	
	@Override
	public String toString() { return "Toy [name=" + name + ", age=" + age + "]"; }
}