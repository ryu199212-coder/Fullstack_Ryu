package com.company.java008_ex;

public class Repeat025 {
    public static void main(String[] args) {
    	
    	char[][] ch = new char[2][3];
    	
    	
    	char data = 'A';
    			
    	for(int i = 0; i < ch.length; i++) {
    		for(int j = 0; j < ch[i].length; j++) {
    			ch[i][j] = data++;  			   				
    		}
    		data = 'a';
    	}
    	
    	for(int i = 0; i < ch.length; i++) {
    		for(int j = 0; j < ch[i].length; j++) {
    			System.out.print(ch[i][j]+ "\t");
    		}
    		System.out.println();
    	}
    	

    	
  
		}
	
	}
	
	


	


