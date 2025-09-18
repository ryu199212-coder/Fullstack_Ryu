package com.company.java008_ex;

import java.util.Scanner;

public class MethodEx009 {
	   public static void who_are_you(String [][] users) {
	   // 변수
	   Scanner scanner = new Scanner(System.in);
	   String result="정보를 확인해주세요.";
	   String tempid="";
	   String temppass="";
	       
	   // 입력
	   System.out.println("아이디 입력 > "); tempid = scanner.next();
	  
	   
	   for(int u=0; u<users.length; u++) {
		   if(tempid.equals(users[u][0])) {result = tempid + "의 나라는" + users[u][2] + "입니다.";}
	   }
	   System.out.println(result);   
	   
	   
	   }
	public static void main(String[] args) {
		String [][] users = {
   				{ "aaa"  , "111"   , "한국" } ,     // 00 01 02
                { "bbb" , "222"   , "호주"  } ,     // 10 11 12
                { "ccc"  , "333"   , "중국" }       // 20 21 22
               };  
		who_are_you(users);    
		who_pass_change(users);			
			
		}
	   
	
	 public static void who_pass_change(String [][] users) {
		// 변수
		Scanner scanner = new Scanner(System.in);
		String temppass="";
		String result="정보를 확인해주세요.";
		String change="";
		String tempid="";
		// 입력
		System.out.println("비밀번호 입력 > "); temppass = scanner.next();
		System.out.println("아이디 입력 > "); tempid = scanner.next();
		// 처리 
		int find = -1;
		for (int u = 0; u < users.length; u++) {
            if (tempid.equals(users[u][0]) && temppass.equals(users[u][1])) {
                System.out.print("변경하실 비밀번호를 입력해주세요 > "); change = scanner.next();                
                users[u][1] = change;
                System.out.println(
                "정보를 확인해주세요 : [" + users[u][0] + ", " + users[u][1] + ", " + users[u][2] + "]");
                find = u;
                break;
            }
        }
		   
	   //if
		if(find==-1) {System.out.println("유저 정보를 확인해주세요");}
		
		
		
	}
	
	
	// 처리
	   
	
	
}


/*연습문제9)  method
패키지명 : com.company.java008_ex
클래스명 :  MethodEx009

public class MethodEx009{ 
   
//  public static void who_pass_change(String [][] users){ 아이디,비밀번호가 맞으면 비밀번호 바꾸기}


아이디를 입력해 주세요 > bbb
비밀번호를 입력해 주세요 > 123
유저를 확인해주세요!

아이디를 입력해 주세요 > bbb
비밀번호를 입력해 주세요 > 222
변경하실 비밀번호를 입력해주세요123
정보확인 : [bbb, 123, 호주]

*/
