package com.company.java013_ex;

public class Score {  //## public > protected> package(default) 아무것도 없는거 >  private
	// 멤버변수 - private
	private String name;    
	private int kor, eng, math;   
	private double avg;
	private String pass;
	
	//멤버함수 - 기본생성자, 필드있는생성자, toString, getters/setters
	public Score() { super(); }
	public Score(String name, int kor, int eng, int math, double avg, String pass) { super(); this.name = name; this.kor = kor; this.eng = eng; this.math = math; this.avg = avg; this.pass = pass; }
	public Score(String name, int kor, int eng, int math) { super(); this.name = name; this.kor = kor; this.eng = eng; this.math = math; }
	
	@Override public String toString() { return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", avg=" + avg + ", pass=" + pass + "]"; }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
