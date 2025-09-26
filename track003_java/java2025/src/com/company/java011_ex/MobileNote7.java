package com.company.java011_ex;

public class MobileNote7 {
	private String iris;
	private String face;
	
	public String getIris() { return iris; } public void setIris(String iris) { this.iris = iris;}
	public String getFace() { return face; } public void setFace(String face) { this.face = face;}
	
	@Override
	public String toString() {
		return "Note [iris=" + iris + ", face=" + face + "]";}
	
	public void newShow() {
		System.out.println("NOTE7 객체 기능(Overriding)");
		System.out.println("iris = " + this.iris);
		System.out.println("face = " + this.face);
		
	}	
}
