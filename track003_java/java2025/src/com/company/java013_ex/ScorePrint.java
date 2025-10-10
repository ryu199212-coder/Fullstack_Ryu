package com.company.java013_ex;

public class ScorePrint {
	public void show(Score[] std) {
		 System.out.println("=============================================================\r\n"
 		+ "이름\t국어\t영어\t수학\t평균\t합격여부\r\n"
 		+ "======================================================================");
		 for(int i=0; i<std.length; i++) {
			 System.out.println(std[i].getName()  + "\t" + std[i].getKor()  + "\t" +std[i].getEng()  + "\t" 
					 	+  std[i].getMath()  + "\t" +  String.format("%.2f", std[i].getAvg())   
					 	+ "\t" +  std[i].getPass());
		 }
	}
}
