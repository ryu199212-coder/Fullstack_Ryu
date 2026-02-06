package com.company.java015_lambda_stream;

import com.company.java015_lambda_stream.RefClass.InterUsing;

class RefClass{void method(String str){System.out.println(str);}
interface InterUsing{void inter(RefClass c, String str);}
}

public class Lambda003 {
	public static void main(String[] args) {
		//#1. 익명클래스
		InterUsing a1 = new InterUsing() {
			@Override public void inter(RefClass c, String str) {c.method(str);}	
		};
		a1.inter(new RefClass(), "Hello :)");
		
		//#2. 람다()->{}
		InterUsing a2 = (c,str) -> {c.method(str);};
		a2.inter(new RefClass(), "Hello :):)"); //RefClassd의 method 사용
		
		//#3. :: 표현식(참조)
		InterUsing a3 = RefClass::method;
		a3.inter(new RefClass(), "Hello :):):)"); // 자동연결 1) RefClass 2) method 3)
		
		//#4. interface InterBasic{int method(int a, int b);}
		InterBasic basic = (int a, int b) -> {return Math.max(a,b);};
		System.out.println(basic.method(10, 3));
		
		InterBasic basic2 = (a, b) -> Math.max(a,b);
		System.out.println(basic.method(100, 3));
		
		InterBasic basic3 = Math::max;
		System.out.println(basic.method(1000, 3));
		
		InterBasic basic4 = (a, b) -> Math.min(a, b);
		System.out.println(basic4.method(10, 3));
		
		InterBasic basic5 = Math::min;
		System.out.println(basic4.method(10, 3));
		
		//#4. interface
		InterString basic6 = (a,b)->a.compareTo(b);
		System.out.println(basic6.compare("apple", "banana"));
		//문자열이 같으면 0, (음수)a<b a가 b보다 앞에옴, (양수)a>b a가 b보다 뒤에옴
		
		InterString basic7 = String::compareTo;
		System.out.println(basic7.compare("coconut", "banana"));
		//문자열이 같으면 0, (음수)a<b a가 b보다 앞에옴, (양수)a>b a가 b보다 뒤에옴
		
		InterParse basic8 = (s) -> Integer.parseInt(s); //Integer 클래스에서 parseInt 사용
		System.out.println(basic8.parse("10")+3);
		
		InterParse basic9 = Integer::parseInt; //Integer 클래스에서 parseInt 사용
		System.out.println(basic9.parse("10")+3);
		
		//InterAbs basic10 = a-> {return Math.abs(a);}; // 절대값 10 Math abs 사용
		//InterAbs basic10 = a-> Math.abs(a);
		InterAbs basic10 = Math::abs;
		System.out.println(basic10.apply(-10));
		
		//InterPrint basic11 = (s) -> {System.out.println("Hello Lambda");};
		//InterPrint basic11 = s -> System.out.println("Hello Lambda");
		InterPrint basic11 = System.out::println;
		basic11.print("Helo Lambda");
		
		//ex1)람다
		//힌트)System.out.println(ex1.getLength());
		//System.out.println(ex1.getLength("hello")); 출력시 5
		//Ex1 ex1 = s -> s.length();
		Ex1 ex1 = String::length;
		System.out.println(ex1.getLength("hello"));
		
		
		//ex2)람다2
		//힌트)System.out.println(ex2.getLength());
		//ex2.getLength("lambda :D")); 출력시 lambda :D
		//Ex2 ex2 = (s) -> System.out.println(s);
		Ex2 ex2 = System.out::println;
		ex2.print("lambda :D");
	}
}

interface InterBasic {int method(int a, int b);       } //(a, b) -> return
interface InterString{int compare(String a, String b);} //순서1) (a,b) -> return
interface InterParse {int parse(String s);            } //(s) -> return
interface InterAbs   {int apply(int a);               } //(a) -> return
interface InterPrint {void print(String s);           } //(s) -> x
interface Ex1        {int getLength(String s);        }
interface Ex2        {void print(String s);           }