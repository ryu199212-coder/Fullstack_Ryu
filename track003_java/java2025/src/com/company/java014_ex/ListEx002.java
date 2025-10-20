package com.company.java014_ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListEx002 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
	     list.add("one");
	     list.add("two");
	     list.add("three");
	     
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("숫자 입력 > ");
	    int i = scanner.nextInt();
	    
	    System.out.println(list.get(i-1));
	}
}
/*연습문제2)  Collection  Framework
패키지명 : com.company.java014_ex
클래스명 : ListEx002
1.  numbers ArrayList 만들기
2.  one, two, three 데이터 추가
3.  사용자에게 1,2,3 입력받기
4.  1을 입력받으면 one 출력
    2를입력받으면 two 출력
    3을입력받으면 three 출력*/