package com.company.java011_ex;

public class Score_ex {
	public class Score{
		   private String name;
		   private int kor, eng, math , total;
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
		   public double getAver() {
			   return aver;
		   }
		   public void setAver(double aver) {
			   this.aver = aver;
		   }
		   public String getP() {
			   return p;
		   }
		   @Override
		public String toString() {
			return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", total=" + total
					+ ", aver=" + aver + ", p=" + p + ", s=" + s + ", rank=" + rank + ", getKor()=" + getKor()
					+ ", getEng()=" + getEng() + ", getMath()=" + getMath() + ", getAver()=" + getAver() + ", getP()="
					+ getP() + ", getS()=" + getS() + ", getName()=" + getName() + ", getTotal()=" + getTotal()
					+ ", getRank()=" + getRank() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}
		   public void setP(String p) {
			   this.p = p;
		   }
		   public String getS() {
			   return s;
		   }
		   public void setS(String s) {
			   this.s = s;
		   }
		   private double aver;
		   private String p  , s  , rank;
		   public String getName() {
			return name;
		   }
		   public void setName(String name) {
			this.name = name;
		   }
		   public int getTotal() {
			return total;
		   }
		   public void setTotal(int total) {
			this.total = total;
		   }
		   public String getRank() {
			return rank;
		   }
		   public void setRank(String rank) {
			this.rank = rank;
		   }
		}

}
