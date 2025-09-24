package com.company.java011;

public class Score{
	   private String name;
	   private int kor, eng, math , total;
	   private double aver;
	   private String p  , s  , rank;
	   public Score() {super();
	   this.rank ="";
	   }
	
	   public Score(String name, int kor, int eng, int math) {
		this();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	   public void total() {this.total=this.kor+this.eng+this.math;}
	   
	   public void aver() {this.aver=this.total/3.0;}
	   
	   public void p() {this.p = this.aver>=60 && this.kor>=40 && this.eng>=40 && this.math>=40? "합격" : "불합격";}
	   
	   public void s() {this.s = this.aver>=95? "장학생":"";}
	   
	   public void rank() {for (int i = 0; i < (int) (this.aver / 10); i++) {this.rank+="*";}
	   
	   }

			
	   
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

	   public int getTotal() {
		   return total;
	   }

	   public void setTotal(int total) {
		   this.total = total;
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

	   public void setP(String p) {
		   this.p = p;
	   }

	   public String getS() {
		   return s;
	   }

	   public void setS(String s) {
		   this.s = s;
	   }

	   public String getRank() {
		   return rank;
	   }

	   public void setRank(String rank) {
		   this.rank = rank;
	   }

	   @Override
	public String toString() {
		return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", total=" + total
				+ ", aver=" + aver + ", p=" + p + ", s=" + s + ", rank=" + rank + "]";
	}

	   public static void info() {
		   System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		   System.out.println("이름   국어   영어   수학   총점   평균   합격여부   장학생   랭킹");
		   System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	   }
	   
	   public void show() {
		   total();
		   aver();
		   p();
		   s();
		   rank();
		   
		   System.out.printf("%s   %d   %d   %d   %d   %.2f   %s   %s   %s\n",
				   name, kor, eng, math, total, aver, p, s, rank);
		// TODO Auto-generated method stub
	   
	 
	   }
	} // java011_ex에 설정해주세요!



