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

# 4. Mini Project

---

## ✈️ 여행 일정 및 예산 관리 시스템

### 📌 프로젝트 개요
Java 콘솔 환경에서 작동하는 **여행 일정 및 예산 관리 도구**입니다. 사용자는 여행자 정보를 등록하고, 여행지를 선택한 뒤 추천 일정을 확인하거나 예산을 계산 및 관리할 수 있습니다. 배열 기반의 데이터 처리와 조건 분기 로직을 통해 현실적인 사용자 경험을 제공합니다.

---

### 🎯 주요 기능

- **여행자 등록 및 삭제**  
  사용자 인증 기반으로 여행자 정보를 생성 및 제거

- **여행지 선택**  
  서울, 부산, 대전, 대구, 광주 중 선택 가능

- **일정 추천**  
  각 도시별 3일간의 관광 코스 제공

- **예산 계산**  
  여행지별 평균 일일 비용을 기반으로 3일 예상 경비 자동 계산

- **예산 관리**  
  예산 추가 및 예약 취소 시 예산 차감 기능

- **정보 조회**  
  등록된 여행자 정보 및 선택된 여행지 확인

---

### 🧠 개발 포인트

- **배열 기반 데이터 관리**  
  도시별 일정과 비용을 2차원 배열로 구성하여 효율적인 접근 구현

- **조건 분기 처리**  
  사용자 입력에 따라 다양한 흐름 제어 및 예외 처리

- **사용자 경험 고려**  
  여행지 선택 시 추천 일정과 예산 적정성 안내로 현실감 있는 UX 제공

---

### 💡 향후 개선 방향

- GUI 기반으로 확장하여 시각적 인터페이스 제공  
- 다중 사용자 지원 및 파일 저장 기능 추가  
- 여행지 정보 외부 API 연동으로 실시간 콘텐츠 제공

---

### 📺 시연 영상

[YouTube 영상 보기](https://youtu.be/WAa1vEBteT0)

---

### 🛠️ 사용 기술

- Java (콘솔 기반)
- 배열 및 조건문, 반복문
- 사용자 입력 처리 및 예외 대응

---

### ✅ 어필 포인트

- 기능 중심의 구조 설계로 확장성과 유지보수 용이
- 사용자 중심의 흐름 설계로 실무 감각 반영
- 향후 GUI 및 API 연동을 고려한 구조 설계

---

## 💼 개발자 포트폴리오 관리도구

### 📌 프로젝트 개요
Java 콘솔 환경에서 작동하는 **개발자 포트폴리오 관리 시스템**입니다. 사용자는 개발자 정보를 등록하고, 기술 스택 기반으로 검색하거나 프로젝트 수를 관리할 수 있습니다. 실무에서 자주 접하는 CRUD 로직과 배열 기반 데이터 처리, 조건 분기 및 반복문 활용을 통해 기초적인 시스템 설계 능력을 보여줍니다.

---

### 🎯 주요 기능

- 👤 개발자 등록: 이름, 기술 스택, 프로젝트 수, GitHub 링크 입력  
- 📄 포트폴리오 조회: 등록된 개발자의 상세 정보 출력  
- ➕ 프로젝트 추가 / ➖ 삭제  
- 🔍 기술 스택 검색: 특정 기술을 가진 개발자 필터링  
- 📋 이력서 요약 출력: 전체 개발자 정보를 카드 형식으로 출력  

---

### 🛠️ 사용 기술

- Java 기본 문법 (배열, 조건문, 반복문, 입력 처리)
- 콘솔 UI 구성 및 사용자 경험 개선
- 간단한 데이터 구조 설계 및 상태 관리

---

### 🎯 실무 연관성

- 데이터 관리 로직을 직접 구현하며 CRUD의 핵심 개념을 체득
- 사용자 입력 처리 및 예외 대응을 통해 실무에서 필요한 안정성 고려
- 기술 스택 기반 필터링은 실제 인사 시스템에서 활용되는 기능과 유사
- GitHub 링크 관리 기능은 실무에서 포트폴리오 연동에 필수적인 요소

---

### 📺 시연 영상

[YouTube 영상 보기](https://youtu.be/L3NoFI95FSs)

---

### ✅ 어필 포인트

- 단순한 기능 구현을 넘어서 사용자 중심의 인터페이스 구성에 신경 썼습니다  
- 실무자가 빠르게 정보를 파악할 수 있도록 이력서 요약 출력 기능을 설계했습니다  
- 확장 가능성을 고려해 구조를 단순화했으며, 추후 파일 저장, GUI 연동 등으로 발전시킬 수 있습니다

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
 **문제점**
```bash
자료형이 빠져 있어서 컴파일 오류가 난다.
```
### 문제 해결(2)
result는 문자열을 담고 있으므로
변수 파트에 'String'을 추가해서 'String result'로 변경 해야한다
```
---
<img src="./제목 없음.png"
alt="프로필"

어벤저스 순위
```

---
### 문제 발생(3)
```bash
if (check1) {
    System.out.printf(
        (result == (int) result) ? "%d %c %d = %d\n" : "%d %c %d = %.2f\n",
        num1, ch, num2,
        (result == (int) result) ? ((int) result) : result
    );
}
```
**문제점**

* `System.out.printf()`에서 포맷 문자열을 \*\*삼항 연산자(조건 연산자)\*\*로 분기하고 있음
* `"%d"`는 정수, `"%.2f"`는 실수를 기대하는데, 이에 대응하는 인자 `(int) result`와 `result`는 타입이 다름
* 하지만 삼항 연산자의 반환 타입은 **하나의 공통 타입**으로 수렴되어야 하는데,
  `int`와 `double`은 **공통 조상이 없어 자동 변환되지 않음**
* 이로 인해 컴파일러가 `printf()` 호출 시 포맷과 인자의 타입 매칭을 정확히 판단할 수 없어 **컴파일 오류 발생**

```bash
Type mismatch: cannot convert from double to int
```

### 문제 해결(3)
```bash

* 삼항 연산자에서 **기본형(int, double)** 대신 \*\*래퍼 클래스(Integer, Double)\*\*로 변환하고 `(Object)` 캐스팅
* `printf()`는 가변 인자를 `Object...`로 처리하므로, 명확한 타입 변환을 통해 **컴파일 오류를 방지**

```java
if (check1) {
    System.out.printf(
        (result == (int) result) ? "%d %c %d = %d\n" : "%d %c %d = %.2f\n",
        num1, ch, num2,
        (result == (int) result)
            ? (Object)(Integer)((int) result)
            : (Object)(Double) result
    );
}
```

**문제점**

* `int`와 `double`의 공통 조상인 `Object`로 묶어 타입 불일치를 제거
* 자바 컴파일러가 **형식 문자열과 인자의 매칭을 확실히 이해**할 수 있게 도와줌
* 결과적으로 **컴파일 오류를 해결**하면서 원하는 출력 포맷도 유지
```
---

### 문제 발생(4)
```bash
System.out.print("숫자열을 입력하시오 : ");
ch = scanner.nextLine();

for(int i=0;i<str;i++) {
      val = ch.charAt(i);
      hap += (int)val;
      }
      System.out.println(hap);
```

 **문제점**

* 문자열로 입력받은 숫자의 각 자리 문자를 `(int)`로 변환해 더하는 과정에서, 문자 `'1'`의 ASCII 코드값인 49가 더해져 결과가 예상과 다름
* `'1'`과 정수 1은 엄연히 다르며, `(int) val`은 \*\*문자 코드값(ASCII)\*\*를 반환함
* 결과적으로 각 자리 숫자의 합이 아니라 각 자리 문자의 ASCII 합이 계산되어 **오답 출력** 발생

```bash
예: "12345" 입력 시, 1+2+3+4+5=15 기대하나, 실제는 49+50+51+52+53=255 출력
```

<br/>

### 문제 해결(4)

* 문자 `'0'`의 ASCII 값이 48이므로, 각 자리 문자를 정수 숫자로 변환하려면 `'0'`을 빼주면 됨
* 즉, `(int) val - 48` 또는 `val - '0'` 형태로 변환하면 문자 숫자를 올바른 정수로 인식 가능

```java
val = ch.charAt(i);
hap += val - '0'; 
```

* 이렇게 하면 문자 `'1'`이 정수 1로, `'2'`가 정수 2로 변환되어 올바른 합산 결과를 얻음

<br/>
---

### 문제 발생(5) - 문자열 비교 오류
```bash
if(id == id && pass == pass){ }
```

 **문제점**

* 로그인 로직에서 사용자 입력값과 저장된 ID, 비밀번호를 비교할 때, **항상 true가 반환되는 현상** 발생
* 두 값이 분명 다름에도 불구하고 조건문이 통과하여, **비밀번호가 틀려도 로그인이 되어버리는 문제** 발생

<br/>

 **원인 분석**

* `==` 연산자는 기본형(primitive type)에서는 값을 비교하지만, **객체형(String 등)에서는 참조(주소)를 비교**함
* 즉, `id == id`는 **동일한 참조를 비교하므로 항상 true**
* 사용자 입력값인 `tempid`, `temppass`와 저장된 값 `id`, `pass`는 **내용이 같더라도 서로 다른 객체**일 수 있음 → `==` 비교 시 false 반환 또는 로직이 의도와 다르게 작동

<br/>

### 문제 해결(5)

* 문자열 값 자체를 비교하려면 `.equals()` 메서드를 사용해야 함
* 아래와 같이 수정하여 **내용 기반 비교**로 로직을 변경함

```bash
if(tempid.equals(id) && temppass.equals(pass)){ 
    // 로그인 성공 처리
}
```

* `.equals()`는 두 문자열의 **실제 값(value)** 을 비교하기 때문에, 내용이 동일할 경우만 true를 반환함
---

## 참고문헌
- [생활코딩](https://opentutorials.org/course/1223)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Baeldung](https://www.baeldung.com/)