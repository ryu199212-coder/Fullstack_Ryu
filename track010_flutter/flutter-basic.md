# ■ FLUTTER

## 1단계 : FLUTTER 설치
1. Git으로 설치 :
 git clone https://github.com/flutter/flutter.git -b stable C:\flutter
2. 환경 변수 설정 :
 시스템 환경 변수 편집 → Path → C:\flutter\bin 추가
3. 실행 테스트 :
 flutter doctor

## 2단계 : VS 설치 / 확장프로그램
**[실습]**
- [x] VS Code 설치 ([공식 사이트](https://code.visualstudio.com/))  
- [x] 확장 프로그램 설치 (Ctrl+Shift+X):
  - Flutter  
  - Dart  

## 3단계 : 앱 실행
**[실습]**
- [x] Android Studio 설치 ([공식 사이트](https://developer.android.com/studio))  
- [x] SDK Manager 설정  
  - SDK Platforms → 최신 API 레벨 설치 (예: **Android 14**)  
  - SDK Tools → Command-line Tools, Emulator, Platform-Tools, Build-Tools 체크  
- [x] New Project  → hello1 (java)  
- [x] AVD Manager → **Pixel 6** 가상 디바이스 생성  
- [x] 디바이스 실행 - 무겁다....
- [x] 설치완료확인:  

## 4단계 : 첫 프로젝트 생성
**[실습]** 
- [x] 실행:
  ```bash
  flutter create hello2
  cd hello2
  ```
  ```bash
  flutter devices
  flutter run -d emulator-5554
  flutter run -d chrome
  ```

**[실습]** 
- `lib/main.dart` 수정
```dart
    return Scaffold(
      appBar: AppBar(title: Text('첫번째 Flutter 앱')),
      body: Center(child: Text('Hello Fluter')),
    );   
```
```
앱 실행중 터미널 에서
- `r` → reload
- `R` → Restart
```
## 5단계 : 필수 패키지 설치
**[실습]**
- [x] `pubspec.yaml` 수정:
  ```yaml
  dependencies:
    flutter:
      sdk: flutter
    http: ^1.1.0
    provider: ^6.1.1
    shared_preferences: ^2.2.2
    go_router: ^12.1.3
  ```
- [x] 패키지 설치:
  ```bash
  flutter pub get
  flutter pub add http
  flutter pub add provider
  ```
pubspec.yaml (환경 설정 파일)
1. http: ^1.1.0                서버통신
2. provider: ^6.1.1            상태관리패키지
3. shared_preferences: ^2.2.2  로컬저장소 (다크모드 설정, 로그인 토큰)
4. go_router: ^12.1.3          url 기반 라우팅

## 6단계 : dart 기본문법