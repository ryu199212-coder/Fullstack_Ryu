package com.company.java013_ex;

public class ScoreProcess {
	public void process_avg(Score[] std) {
		for( Score s :   std ) {   s.setAvg(   ( s.getKor() + s.getEng() + s.getMath()  ) / 3.0     );  }
	}//  한명씩 꺼내와서   유저배열에서

	public void process_pass(Score[] std) { 
		for( Score s :   std ) {   //  한명씩 꺼내와서   유저배열에서
			s.setPass(  s.getAvg()<60  ? "불합격":  
							   s.getKor()<40 ||  s.getEng() <40 || s.getMath() <40 ? "재시험" : "합격"   );
		}
	}
}
