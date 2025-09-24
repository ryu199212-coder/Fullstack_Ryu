package com.company.java011;

	public class Milk{  // java011_ex에 설정해주세요!
		   private int  mno;   
		   private String mname;  
		   public int getMprice() {
			return mprice;
		}
		   public void setMprice(int mprice) {
			   this.mprice = mprice;
		   }
		   private  int mprice;
		   @Override
		   public String toString() {
			return "Milk [mno=" + mno + ", mname=" + mname + ", mprice=" + mprice + "]";
		   }  
		}


