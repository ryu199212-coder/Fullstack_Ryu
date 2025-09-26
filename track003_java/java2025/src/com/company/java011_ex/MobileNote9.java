package com.company.java011_ex;

public class MobileNote9 {
	private int battery;
	
	public int getBattery() { return battery; } public void setBattery(int battery) { this.battery = battery; }
	
	@Override
	public String toString() { return "MobileNote9 [battery=" + battery + "]"; }


	public void newShow() {
		System.out.println("NOTE9 객체 기능(Overriding)");
		System.out.println("예쁘게 사용하기!");
		System.out.println("battery = " + this.battery);		
	}

	public void setFace(String string) {
		
	}

	public void setIris(String string) {
		
		
	}	
}

