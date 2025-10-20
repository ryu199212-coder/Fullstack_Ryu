package com.company.java014;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// 1. 콜렉션 프레임워크 - [배열]의 단점을 개선 [객체]만 저장가능한 [동적배열]
// 2. List, Set, Map
// List(기차) - index 순서 O, 중복허용 O, add / get(순서) / size / remove(순서) / contains
// Set(주머니) - index 순서 X, 중복허용 X, add / 향상for, iterator / size / remove(객체) / contains
class Candy{
	String name;
	int price;
	public Candy(String name, int price) { super(); this.name = name; this.price = price; }
	public Candy() { super();  }
	@Override // 클래스 확인
	public String toString() { return "Candy [name=" + name + ", price=" + price + "]"; }
	@Override // 사용자가 넣어준 값 비교
	public int hashCode() {
		return Objects.hash(name, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candy other = (Candy) obj;
		return Objects.equals(name, other.name) && price == other.price;
	}
}
public class Set001 {
	public static void main(String[] args) {
		Set<Integer> set1 = new HashSet<>();
		set1.add(new Integer(1));  // Integer e = new Integer(1) (부품객체)
		set1.add(1);			   // Integer e = 1 (기본값)
		set1.add(1);			   // 부품객체 = 기본값 (Integer - wrapper 클래스)
		set1.add(2);			   // 기본값을 자동으로 - 객체화 - 부품객체 (wrapper 클래스)
		set1.add(3);			   // int → Integer, float → Float (auto 박싱)
		System.out.println(set1);  // [1, 2, 3]
		

		Set<Candy> set2 = new HashSet<>();
		set2.add(new Candy("츄파츕스", 300));
		set2.add(new Candy("츄파츕스", 300));
		set2.add(new Candy("츄파츕스", 300));
		set2.add(new Candy("청포도알사탕", 4500));
		set2.add(new Candy("멘톨", 5500));
		
		System.out.println(set2);
		System.out.println(set2.size());
	}
}