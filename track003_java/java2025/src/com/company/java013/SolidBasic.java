package com.company.java013;

/*
S	단일 책임 원칙 (SRP)	클래스는 하나의 책임만 가져야 함. 즉, 변경 이유가 하나여야 함.
O	개방-폐쇄 원칙 (OCP)	확장에는 열려 있고, 수정에는 닫혀 있어야 함. 기존 코드를 건드리지 않고 기능 추가 가능해야 함.
L	리스코프 치환 원칙 (LSP)	자식 클래스는 부모 클래스의 기능을 대체할 수 있어야 함. 즉, 부모 타입으로 자식 객체를 써도 문제 없어야 함.
I	인터페이스 분리 원칙 (ISP)	클라이언트는 자신이 사용하지 않는 인터페이스에 의존하면 안 됨. 인터페이스는 작게 나누는 게 좋음.
D	의존 역전 원칙 (DIP)	고수준 모듈이 저수준 모듈에 의존하면 안 됨. 둘 다 추상화에 의존해야 함. 구현보다 인터페이스에 의존하라는 뜻.

1. S : 한 클래스는 하나의 일만(쿠키반죽은 반죽만, 굽기는 굽기만)
2. O : 새로운 클래스 쉽게 추가(새로운 쿠키 추가 수정X)
3. L : 모든 클래스는 같은 방식으로 만든다(어떤 쿠키던지 오븐에 넣으면 잘 만들어져야한다)
4. I : 인터페이스는 작고 명확하게(반죽 담당자는 “굽기 버튼”이 필요 없다)
5. D : 고수준 모듈이 저수준 모듈에 의존하면 안 됨(“쿠키 레시피”라는 추상화된 지침만 있으면 어떤 쿠키든 구울 수 있다)

*/
//1. S : 단일책임의 원칙
class CookieMaker{
	public void bakeCookie(String type) {System.out.println(type + "쿠키를 구워요!");}
}
//2. O : 새로운 클래스 쉽게 추가(새로운 쿠키 추가 수정X)
interface Cookie{void make();}
class ChocoCookie implements Cookie{@Override public void make() {System.out.println("초코쿠키");}}
class DeepChocoCookie implements Cookie{@Override public void make() {System.out.println("딥초코쿠키");}}
class BananaCookie implements Cookie{@Override public void make() {System.out.println("바나나쿠키");}}

//3. L : 리스코프 치환(어떤 쿠키던지 오븐에 넣으면 잘 만들어져야한다)
class CookieFactory{
	public void makeCookie(Cookie cookie) { // Cookie cookie = 각종 쿠키종류
		cookie.make(); // 오븐
	}
}
//4. I : interface - 꼭 필요한 기능만
interface SimpleCookie{void make();}
class SimpleCookieMake implements SimpleCookie{
	@Override public void make() {System.out.println("쿠키 만드는법은 간단하게");}
}

//5. 의존역전원칙 - CookieFactory는 구체적인 쿠키가 아니라 추상적인 Cooki에 의존
class CookieShop{
	private Cookie cookie;
	public CookieShop() { super();  }
	public CookieShop(Cookie cookie) { super(); this.cookie = cookie; }
	public void sell() {System.out.println("cookie 가게에서..."); cookie.make();}
	//쿠키 종류는 외부에서 넣어줌
}
public class SolidBasic {
	public static void main(String[] args) {
		//1. S : 단일책임의 원칙
		System.out.println("1. S : 단일책임의 원칙 - 쿠키굽기");
		CookieMaker maker = new CookieMaker();
		maker.bakeCookie("초코");
		maker.bakeCookie("오트밀");
		maker.bakeCookie("라즈베리");
		
		//2+3. O+L
		System.out.println("2+3. OL : 개방폐쇄(추가) + 리스코프(어떤쿠키든 굽기가능) 치환");
		CookieFactory factory = new CookieFactory();
		factory.makeCookie(new ChocoCookie());
		factory.makeCookie(new DeepChocoCookie());
		factory.makeCookie(new BananaCookie());
		
		//4. I 
		System.out.println("4. I : 인터페이스 분리");
		SimpleCookie making = new SimpleCookieMake(); making.make();
		
		//5. D : 의존역전 : 어떤쿠키든 가게에서 팔 수 있음
		CookieShop shop = new CookieShop(new DeepChocoCookie());
		shop.sell();
	}
}

