# FLUTTER

## 1단계 : 프로젝트 생성
 ```bash
 flutter create mobile
 cd mobile
 ```

## 2단계 : 멤버관리 기능
    ├── features/              # 기능 단위로 모듈화 (DDD + Clean Architecture )
    │    ├── user/             # 사용자 인증/관리 관련 기능
    │    │    ├── data/        # 서버랑 주고받는 ("편지상자")    
    │    │    │    ├── models/         # 편지내용 -  DTO, API 응답/요청 모델
    │    │    │    ├── repositories/   # 편지 배달부 - Repository 구현체 (API 호출, DB 접근)
    │    │    │    └── sources/        # 편지출발 -서버, db / 데이터 소스 (Remote API, Local DB)
    │    │    ├── domain/      # 진짜 중요한 "보물상자"
    │    │    │    ├── entities/       # 보물카드, 핵심 엔티티 (User, Token 등)
    │    │    │    ├── repositories/   # 보물카드 찾는 지도 - 약속만 있고 실제 구현 없음  , Repository 인터페이스 (추상화)
    │    │    │    └── usecases/       # 보물카드 쓰는 방법 - login, logout 같은 규칙 - 유스케이스 (Login, Logout, RefreshToken)
    │    │    └── presentation/ # 화면에 보여주는 "무대" 
    │    │         ├── pages/          # 큰 무대    -  화면 단위 (LoginPage, HomePage) 
    │    │         ├── widgets/        # 작은 장심품 -  UI 컴포넌트 (LoginForm, UserCard)
    │    │         └── state/          # 배경       -  상태 관리 (Provider, Bloc, Riverpod 등)

1. 간략 흐름
1) data  →  편지 :  서버랑 이야기
2) domain  →  보물 : 중요한 정보와 규칙
3) presentation →  무대 : 눈에 보이는 화면

2. (1) data  →  편지 :  서버랑 이야기 
```
    │    │    ├── data/        # 서버랑 주고받는 ("편지상자")    
    │    │    │    ├── models/         # 편지내용 -  DTO, API 응답/요청 모델
    │    │    │    ├── repositories/   # 편지 배달부 - Repository 구현체 (API 호출, DB 접근)
    │    │    │    └── sources/        # 편지출발 -서버, db / 데이터 소스 (Remote API, Local DB)
```
    - flutter 에서는 api 호출 → dto매핑          → token 저장
    - `models/login_response.dart`             → Access Token, Refresh Token 모델  
    - `repositories/user_repository_impl.dart` → RemoteSource + TokenManager 연결   
    - `sources/user_remote_source.dart`        → 로그인/회원가입 API 호출  

3. (2) domain  →  보물 : 중요한 정보와 규칙
```
    │    │    ├── domain/      # 진짜 중요한 "보물상자"
    │    │    │    ├── entities/       # 보물카드, 핵심 엔티티 (User, Token 등)
    │    │    │    ├── repositories/   # 보물카드 찾는 지도 - 약속만 있고 실제 구현 없음  , Repository 인터페이스 (추상화)
    │    │    │    └── usecases/       # 보물카드 쓰는 방법 - login, logout 같은 규칙 - 유스케이스 (Login, Logout, RefreshToken)
```
    - `entities/user.dart` → 사용자 엔티티   
    - `repositories/user_repository.dart` → 추상화 인터페이스  
    - `usecases/login_usecase.dart` → 로그인 유스케이스  
    - `usecases/logout_usecase.dart` → 로그아웃 유스케이스

4. (3) presentation →  무대 : 눈에 보이는 화면
```
    │    │    └── presentation/ # 화면에 보여주는 "무대" 
    │    │         ├── pages/          # 큰 무대    -  화면 단위 (LoginPage, HomePage) 
    │    │         ├── widgets/        # 작은 장심품 -  UI 컴포넌트 (LoginForm, UserCard)
    │    │         └── state/          # 배경       -  상태 관리 (Provider, Bloc, Riverpod 등)
```
    - `pages/login_page.dart` →  큰 무대(로그인 화면, 글 목록 화면)
    - `state/user_provider.dart` →  무대 조명/배경(상태 관리: Provider, Bloc, Riverpod) / 로그인 상태 관리
    - `widgets/login_form.dart` → 작은 장식품(버튼, 카드) /UI 컴포넌트

