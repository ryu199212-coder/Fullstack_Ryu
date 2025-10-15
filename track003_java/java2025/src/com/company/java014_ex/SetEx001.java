package com.company.java014_ex;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class UserInfo{

private int no; 
private  String name; 
private  int age;


@Override public String toString() { return "UserInfo [no=" + no + ", name=" + name + ", age=" + age + "]"; }
public UserInfo(int no, String name, int age) { super(); this.no = no; this.name = name; this.age = age; }
public UserInfo() { super();  }

@Override public int hashCode() { return Objects.hash(age, name, no); }

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	UserInfo other = (UserInfo) obj;
	return age == other.age && Objects.equals(name, other.name) && no == other.no;
}
public int getNo() { return no; } public void setNo(int no) { this.no = no; }
public String getName() { return name; } public void setName(String name) { this.name = name; }
public int getAge() { return age; } public void setAge(int age) { this.age = age; }


}
public class SetEx001 {
	public static void main(String[] args) {
		Set<UserInfo> sets = new HashSet<>();
		sets.add(new UserInfo(1, "아이언맨" , 50));
		sets.add(new UserInfo(2, "헐크" , 40));
		sets.add(new UserInfo(3, "캡틴" , 120));
		sets.add(new UserInfo(3, "캡틴" , 120));
		
		for (UserInfo user : sets) {System.out.println(user);}
       
		Scanner scanner = new Scanner(System.in);
		System.out.println("사용자 이름 입력 > "); String name = scanner.next();
		boolean find = false;
        for (UserInfo user : sets) {
            if (user.getName().equals(name)) {
                System.out.println(user);
                find = true;
            break;}
        }
        
        int total = 0;
        for (UserInfo user : sets) {
            total += user.getAge();
        }
        double avg = (double) total / sets.size();
        System.out.printf("사용자 평균 나이: %.2f\n", avg);
        
		
	}
}
/*
연습문제1)  Collection  Framework
패키지명 : com.company.java014_ex
클래스명 : SetEx001
1. UserInfo2    Dto 클래스만들기  - 속성 : private int no; private  String name; private  int age;
2. users   HashSet만들기
3. 다음의 데이터 넣기
   new UserInfo2(1, "아이언맨" , 50) , 
   new UserInfo2(2, "헐크" , 40) , 
   new UserInfo2(3, "캡틴" , 120), 
   new UserInfo2(3, "캡틴" , 120)
4. 향상된 for / Interator 이용해서 데이터 출력 (3명만 출력되게- 같은자료 중복안되게)
5. 사용자들의 이름 입력받기 - 이름을 입력받으면 해당하는  유저의 자료출력
6. 사용자들의 나이 평균처리
*/