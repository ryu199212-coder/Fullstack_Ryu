package com.company.java008_ex;

public class Repeat021 {
	public static void main(String[] args) {
		
		
		int[][] arr = new int[2][3];

		
		int data = 101;
		for(int a = 0; a<arr.length; a++) {
			for(int b = 0; b<arr[a].length; b++) {
				arr[a][b]=data++;
				
			}
			data = 201;
		}
		for(int a = 0; a<arr.length; a++) {
			for(int b = 0; b<arr[a].length; b++) {
				System.out.print(arr[a][b] + "\t");
				}
			System.out.println();
			}
		}
	}


