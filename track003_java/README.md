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

## 참고문헌
- [생활코팅](https://opentutorials.org/course/1223)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Baeldung](https://www.baeldung.com/)