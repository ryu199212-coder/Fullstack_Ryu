package com.company.java008_ex;

import java.util.Arrays;

public class MethodEx008_4 {

	/////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		String[][] apt = {
				{"아이유", "손흥민", "BTS RM"},
				{"이정재", "리사", "유재석"},
				{"박지성", "강호동", "마동석"},			
		};
	
		System.out.println("Main > Apt - " + Arrays.deepToString(apt));	   
		ringBell(apt);
		System.out.println("Main > Apt - Bell" + Arrays.deepToString(apt));
	}
	/////////////////////////////////////////////////////////////////////////////////////
	public static void ringBell(String[][] apt) {apt[1][1] = "★";}
	
}


/*
*/