# FLUTTER

## 1단계 : 프로젝트 생성
 ```bash
 flutter create mobile
 cd mobile
 ```

## 2단계 : 프로젝트 구성
 - back : spring boot + security + jwt + redis
 - front(1) - homepage : node + react + next
 - front(2) - mobile : flutter

```
mobile/                # 프로젝트 루트
    lib/
    ├── core/                  # 앱 전역에서 공통으로 쓰이는 부분   
    │    ├── constants/        # 상수 정의 (API URL, 키 값, 색상 등 : 주소)
    │    ├── error/            # 에러 핸들링, 예외 클래스 정의 
    │    ├── network/          # 인터넷연결
    │    └── utils/            # 유틸 함수 모음 (날짜 포맷, 토큰 관리 등 )
    │
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
    │    │
    │    └── post/              # 게시글 관련 기능
    │         ├── data/               
    │         │    ├── models/           # DTO, API 응답/요청 모델
    │         │    │    └── post_model.dart
    │         │    ├── sources/          # 데이터 소스 (Remote API, Local DB)
    │         │    │    └── post_remote_source.dart
    │         │    └── repositories/     # Repository 구현체
    │         │         └── post_repository_impl.dart
    │         │
    │         ├── domain/                
    │         │    ├── entities/         # 핵심 엔티티 (Post 등)
    │         │    │    └── post.dart
    │         │    ├── repositories/     # Repository 인터페이스 (추상화)
    │         │    │    └── post_repository.dart
    │         │    └── usecases/         # 유스케이스 (비즈니스 규칙)
    │         │         ├── get_posts_usecase.dart
    │         │         └── like_post_usecase.dart
    │         │
    │         └── presentation/          
    │              ├── pages/            # 화면 단위
    │              │    └── liked_post_grid_page.dart
    │              ├── widgets/          # UI 컴포넌트
    │              │    └── post_card.dart
    │              └── state/            # 상태 관리 (Provider, Bloc, Riverpod 등)
    │                   └── liked_post_provider.dart
    │
    ├── app.dart               # 앱 진입점, MaterialApp, 라우팅, 테마 정의 
    └── main.dart              # 실행 entrypoint, runApp(App())

    test/                      # 테스트 코드 디렉토리
    └── token_manager_test.dart

    
```

## 3단계 : 필요한 패키지 설정
  ```bash
  flutter pub add http
  flutter pub add flutter_secure_storage
  flutter pub add flutter_riverpod 
  ```
  - `http` 또는 `dio`            → rest api 호출
  - `flutter_service_storage`   → jwt 토큰 안전저장
  - `flutter_riverpod`          → 상태관리 (타입안정성 및 유지보수성)
   `provider`(가장 많이 쓰임, 입문자/중소규모) `riverpod`(■provider보다 더 많이 선택됨) / `bloc`(대규모 서비스)

## 4단계
```
    ├── core/                  # 앱 전역에서 공통으로 쓰이는 부분   
    │    ├── constants/        # 상수 정의 (API URL, 키 값, 색상 등 : 주소)
    │    ├── error/            # 에러 핸들링, 예외 클래스 정의 
    │    ├── network/          # 인터넷연결
    │    └── utils/            # 유틸 함수 모음 (날짜 포맷, 토큰 관리 등 )
```
- core/constants/api.dart         → `BASE_URL` 정의(`http://localhost:8000/api`)
                                    서버엔드포인트(`/auth`, `/api/posts`)
- core/utils/token_manager.dart   → JWT ACCESS/REFRESH TOKEN 저장/읽기/삭제
- core/error/app_exception.dart   → 공통에러처리(401:로그인 안했어, 403:권한없어, 400:입력틀렸어) 
- core/network/dio_client.dart           → 인터넷 연결
                                    (DioClient - ACCESS token 자동으로 붙여서 인증된 사람, 열쇠가 낡았으면 refresh token으로 새 열쇠 받기)

[사용자 요청]
      │
      ▼
[api.dart: 주소록 확인]   
  서버에 전화하려면 "주소"가 필요해요.
  - 로그인? → login 주소
  - 게시글? → posts 주소
      │
      ▼
[token_manager.dart: 열쇠 상자]  
  - Access Token 꺼내기     → 잠깐 쓰는 열쇠
  - Refresh Token 준비하기  → 새 열쇠를 받을 수 있는 비밀 열쇠
      │
      ▼
[dio_client.dart: 전화기]
  - 서버에 전화 걸기 
  - 헤더에 "Bearer <Access Token>" 붙이기 
      │
      ├── 성공 → 서버 응답 받아서 화면에 보여줌
      │
      └── 실패 (401 Unauthorized)
            │
            ▼
     [token_manager: 새 열쇠 꺼내기]  
       - Refresh Token으로 새 Access Token 요청   
       - 새 토큰 저장
            │
            ▼
     [dio_client: 다시 전화 걸기]
            │
            ├── 성공 → 응답 받아서 화면에 보여줌
            └── 실패 → clearTokens()  
      
      │
      ▼
[app_exception.dart: 신호등 켜기]  
  - 401 → "로그인 안 했어!"
  - 403 → "권한 없어!"
  - 400 → "입력 틀렸어!"

## 5단계 테스트
```dart
// test/test_manager_test.dart

import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:mobile/core/utils/token_manager.dart';

class MockSecureStorage extends Mock implements FlutterSecureStorage {}

void main() {
  late MockSecureStorage mockStorage;
  late TokenManager manager;

  setUp(() {
    mockStorage = MockSecureStorage();
    manager = TokenManager(storage: mockStorage);
  });

  test('TokenManager saves tokens correctly', () async {
    when(() => mockStorage.write(
      key: any(named: "key"),
      value: any(named: "value"),
    )).thenAnswer((_) async => null);

    await manager.saveTokens("access123", "refresh456");

    verify(() => mockStorage.write(key: "accessToken", value: "access123")).called(1);
    verify(() => mockStorage.write(key: "refreshToken", value: "refresh456")).called(1);
  });

  test('TokenManager retrieves tokens correctly', () async {
    when(() => mockStorage.read(key: "accessToken")).thenAnswer((_) async => "access123");
    when(() => mockStorage.read(key: "refreshToken")).thenAnswer((_) async => "refresh456");

    final access = await manager.getAccessToken();
    final refresh = await manager.getRefreshToken();

    expect(access, "access123");
    expect(refresh, "refresh456");
  });

  test('TokenManager clears tokens correctly', () async {
    when(() => mockStorage.delete(key: any(named: "key"))).thenAnswer((_) async => null);

    await manager.clearTokens();

    verify(() => mockStorage.delete(key: "accessToken")).called(1);
    verify(() => mockStorage.delete(key: "refreshToken")).called(1);
  });
}

```

2. 실행방법
miblie/ 루트디렉토리에서 실행
```
flutter test
```

```
flutter test test/test_manager_test.dart
```

Q1. TEST1 : AWS에서 혹은 도메인이 바뀌었다면.... `api.dart`
Q2. TEST2 : FLUTTER에서 CORE 계층동작순서
    주소록 - 서버주소 찾기 (`api.dart`) 
    → 열쇠상자 - JWT(`token_manager.dart`)
    → 전화기 - 서버요청(`dio_client.dart`)