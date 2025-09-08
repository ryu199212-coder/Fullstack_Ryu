# ☕ Java Developer Portfolio

안녕하세요!  
Java 기반 백엔드 개발자로 취업을 준비하며 직접 설계하고 구현한 프로젝트들을 정리한 포트폴리오입니다.  
객체지향 설계, 데이터베이스 연동, RESTful API 구현 등 실무에서 바로 활용 가능한 기술을 중심으로 구성했습니다.

---

## 🎯 개발 목적

- Java 기반 백엔드 개발 역량 강화
- 실무에서 요구되는 구조적 설계 및 코드 품질 확보
- API 설계, DB 연동, 예외 처리 등 실전 감각 습득

---

## 🛠️ 기술 스택

| 기술        | 설명 |
|-------------|------|
| Java 17     | 객체지향 프로그래밍 및 비즈니스 로직 구현 |
| Spring Boot | REST API, DI, MVC 패턴 기반 백엔드 프레임워크 |
| JPA / Hibernate | ORM 기반 DB 연동 및 트랜잭션 처리 |
| MySQL       | 관계형 데이터베이스 설계 및 쿼리 작성 |
| Gradle      | 빌드 및 의존성 관리 |
| Git & GitHub| 버전 관리 및 협업 |
| Postman     | API 테스트 및 문서화 |

---

## 📁 프로젝트 구성

### 1. 📝 Simple Blog API
- **설명**: 게시글 CRUD 기능을 제공하는 RESTful API
- **기능**:
  - 게시글 작성, 조회, 수정, 삭제
  - 사용자 인증 (JWT 기반)
  - 예외 처리 및 유효성 검사
- **실무 포인트**:
  - Controller-Service-Repository 구조
  - DTO/Entity 분리
  - Swagger를 통한 API 문서화

---

### 2. 📦 Product Order System
- **설명**: 상품 주문 및 결제 기능을 포함한 간단한 전자상거래 백엔드
- **기능**:
  - 상품 등록/조회
  - 주문 생성 및 상태 관리
  - 결제 시뮬레이션 로직
- **실무 포인트**:
  - 트랜잭션 처리 및 예외 복구
  - Enum 기반 상태 관리
  - 테스트 코드 작성 (JUnit, Mockito)

---

### 3. 👥 User Management System
- **설명**: 사용자 등록, 로그인, 권한 관리 기능 제공
- **기능**:
  - 회원가입 및 로그인
  - 비밀번호 암호화 (BCrypt)
  - 관리자/사용자 권한 분리
- **실무 포인트**:
  - Spring Security 적용
  - Role 기반 접근 제어
  - 커스텀 예외 및 글로벌 핸들러 구현

---

## ✅ 실무 적용 역량

- **객체지향 설계**: SOLID 원칙 기반 구조 설계
- **API 설계**: RESTful 규칙 준수 및 Swagger 문서화
- **DB 설계**: 정규화된 테이블 구조 및 관계 설정
- **예외 처리**: 커스텀 예외 클래스 및 글로벌 핸들링
- **테스트 코드**: 단위 테스트 및 통합 테스트 작성
- **코드 품질**: 가독성, 네이밍, 주석, 리팩토링 적용

---
## 트러블 슈팅

### 문제발생(1)
```bash
package com.company.java004_ex;

import java.util.Scanner;

public class IfEx004 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char ch='\u0000';
		
		System.out.print("문자를 입력>"); ch=scanner.nextInt();
		
		if(ch>='A' && ch<='Z') {System.out.println("대문자");}
		else if(ch>='a'  &&  ch<='z'){System.out.println("소문자");}
	  //else if(ch>=97  &&  ch<=122){System.out.println("소문자");}
		}

}
/*연습문제4)
패키지명 : com.company.java004_ex
클래스명 :  IfEx004
출력내용 : 문자한개를 입력받아 
   대문자인지,  소문자인지 판별하는 프로그램을 작성하시오.
   ※  대문자  ch>='A' && ch<='Z' / 소문자  ch>='a'  &&  ch<='z'  */
   ```
   ### 문제해결(1)
   ```bash
   주요 문제는 scanner.nextInt()로 문자를 입력받으려는.nextInt()는 정수 입력을 받는 메서드인데,
   여기에 문자를 입력하면 예외가 발생하거나 의도한 대로 작동하지 않는다.
   
   ★scanner.nextInt() → scanner.next().charAt(); 로 변경 하면 해결
```
---
### 문제발생(2)
```bash
package com.company.java004_ex;

import java.util.Scanner;

public class Repeat011 {
	public static void main(String[] args) {
		
		int num1, num2;
		result;
		char ch = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("정수 입력 >"); num1=scanner.nextInt();
		System.out.println("정수 입력 >"); num2=scanner.nextInt();
		System.out.println("연산자 입력 >"); ch=(char) scanner.next().charAt(0);
		
		result = (""+num1+ch+num2+"=");
		
		if(ch=='+') {result += (num1+num2);}
		else if(ch=='-') {result += (num1-num2);}
		else if(ch=='*') {result += (num1*num2);}
		else if(ch=='/') {result += String. format("%.2f, (double)num1/num2");}
		
		System.out.println(result);
		
	
		
		
	}
}
```
### 문제해결(2)
```bash
자료형이 빠져 있어서 컴파일 오류가 난다. result는 문자열을 담고 있으므로
변수 파트에 'String'을 추가해서 'String result'로 변경 해야한다
```
---


---

## 참고문헌
- [생활코딩](https://opentutorials.org/course/1223)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Baeldung](https://www.baeldung.com/)