package com.company.java008_ex;

import java.util.Arrays;

public class MethodEx008_2 {
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void main(String[] args) {
		char [] letters = {'A', 'B', 'C', '1', '@'};
		lower(letters);
	   //public static void lower(char [] letters) {}
	   
	   System.out.println(Arrays.toString(letters));
	   }
	/////////////////////////////////////////////////////////////////////////////////////
	public static void lower(char [] letters) {
		for(int i=0; i<letters.length; i++) {
		if(letters[i]>='A' && letters[i]<='Z') { letters[i] += 32;}
	}
	}
}


/*
*/