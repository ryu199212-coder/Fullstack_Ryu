package com.company.java011_ex;

public class MobileNote8 {
	private String face;
	
	public String getFace() { return face; } public void setFace(String face) { this.face = face;}

	@Override
	public String toString() { return "MobileNote8 [face=" + face + "]";}
	
	public void newShow() { 
		System.out.println("NOTE8 객체 기능(Overriding)");
		System.out.println("face = " + this.face); }
}


