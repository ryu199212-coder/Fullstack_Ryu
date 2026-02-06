## 1. 프로젝트 초기화

### 1. 프로젝트 만들기
```
npm init
```
## 📁 프로젝트 구조

```
front/
├── .next/                  # ✅ Next.js 빌드 결과물 (자동 생성, 배포 시 사용)
├── components/             # ✅ 재사용 가능한 UI 컴포넌트 폴더
│   └── Layout.js           # 페이지 공통 레이아웃 컴포넌트
├── node_modules/           # ✅ 설치된 npm 패키지들
├── pages/                  # ✅ Next.js 라우팅 기반 페이지 폴더
│   ├── _app.js             # 전체 앱의 공통 설정 (Redux Provider, 글로벌 스타일 등)
│   ├── index.js            # 메인 페이지
│   ├── login.js            # 로그인 페이지
│   ├── signup.js           # 회원가입 페이지
│   └── users.js            # 사용자 목록 또는 정보 페이지 
├── reducers/               # ✅ Redux 리듀서 폴더
│   ├── index.js            # 루트 리듀서 (combineReducers)
│   ├── user.js             # 사용자 관련 리듀서
│   └── user.test.js        # 리듀서 테스트 코드
├── sagas/                  # ✅ Redux-Saga 폴더
│   ├── index.js            # 루트 사가
│   ├── user.js             # 사용자 관련 사가
│   └── user.test.js        # 사가 테스트 코드
├── store/                  # ✅ Redux 스토어 설정 폴더
│   ├── configureStore.js   # Redux 스토어 설정
│   └── configureStore.test.js # 스토어 테스트 코드
├── styles/                 # ✅ CSS 스타일 폴더
│   └── globals.css         # 글로벌 스타일
├── .babelrc                # Babel 설정 파일
├── .eslintrc               # ESLint 설정 파일
├── package-lock.json       # npm 의존성 잠금 파일
├── package.json            # 프로젝트 메타 정보 및 의존성
└── setupTests.js           # 테스트 환경 설정 파일

```
[실습]
```
mkr front
cd front
npm init
```

### 2. 프로그램 설치
```
npm install
```

[실습]
```
package.json 프로젝트 폴더에 넣기
npm install
```

### 3. 서버 진입점
```
front/
├── pages/                  # ✅ Next.js 라우팅 기반 페이지 폴더
│   ├── index.js            # 메인 페이지
```

### 4. 구조안내, 개발

1. 프로젝트 생성 `package.json`,   `node_modules`, `pages`
2. 앱 진입점    `pages/index.js`, `pages/_app.js`
3. 스타일 적용  `globals.css`, 가  `_app.js 에서 import됩
4. 실행확인    `npm run dev`
```
front/
├── .next/                  # ✅ Next.js 빌드 결과물 (자동 생성, 배포 시 사용)
├── components/             # ✅ 재사용 가능한 UI 컴포넌트 폴더
│   └── Layout.js           # 페이지 공통 레이아웃 컴포넌트
├── node_modules/           # ✅ 설치된 npm 패키지들
├── pages/                  # ✅ Next.js 라우팅 기반 페이지 폴더
│   ├── _app.js             # 전체 앱의 공통 설정 (Redux Provider, 글로벌 스타일 등)
│   ├── index.js            # 메인 페이지
│   ├── login.js            # 로그인 페이지
│   ├── signup.js           # 회원가입 페이지
│   └── users.js            # 사용자 목록 또는 정보 페이지 
├── reducers/               # ✅ Redux 리듀서 폴더
│   ├── index.js            # 루트 리듀서 (combineReducers)
│   ├── user.js             # 사용자 관련 리듀서
│   └── user.test.js        # 리듀서 테스트 코드
├── sagas/                  # ✅ Redux-Saga 폴더
│   ├── index.js            # 루트 사가
│   ├── user.js             # 사용자 관련 사가
│   └── user.test.js        # 사가 테스트 코드
├── store/                  # ✅ Redux 스토어 설정 폴더
│   ├── configureStore.js   # Redux 스토어 설정
│   └── configureStore.test.js # 스토어 테스트 코드
├── styles/                 # ✅ CSS 스타일 폴더
│   └── globals.css         # 글로벌 스타일
├── .babelrc                # Babel 설정 파일
├── .eslintrc               # ESLint 설정 파일
├── package-lock.json       # npm 의존성 잠금 파일
├── package.json            # 프로젝트 메타 정보 및 의존성
└── setupTests.js           # 테스트 환경 설정 파일
```

1. 각 구조 만들기(폴더생성)
2. 각 설정 파일
```
front/
├── .babelrc                # Babel 설정 파일 : JSX → 일반 JS로 변환 브라우저가 이해할 수 있게
├── .eslintrc               # ESLint 설정 파일 : 일관된 코드스타일 유지(들여쓰기,세미콜론,따옴표) / 팀 규칙
└── setupTests.js           # 테스트 환경 설정 파일 : 테스트환경 설정파일 환경초기
```

4. page
```
front/
├── components/             # ✅ 재사용 가능한 UI 컴포넌트 폴더
│   └── Layout.js           # 페이지 공통 레이아웃 컴포넌트
├── pages/                  # ✅ Next.js 라우팅 기반 페이지 폴더
│   ├── _app.js             # 전체 앱의 공통 설정 (Redux Provider, 글로벌 스타일 등)
│   ├── index.js            # 메인 페이지
```

### 5. 개발(reducer - saga - view)
1. reducer(주방 레시피대로 상태바꾸기 - 치킨의 상태) 조리시작, 조리중, 조리완료
2. saga(배달기사 - 서버에 다녀오기)
3. store(치킨집 - 모든상태를 모아두는 중앙창고 / 주방, 배달)

1) `View` 손님이 주문 `/user/login.js` → (store에 액션 전달:치킨집)
                                     → 액션을 saga/reducer로 전달
2) 배달기사가 서버에 다녀오고 (saga)
3) 주방레시피대로 상태꾸기 (reducer)
4) 치킨집(store) 업데이트
5) `View` 화면반영 → 상태갑지하고 화면에 그림그리기

1. [reducers] - user.js     ※ post.js, hashtag.js
2. [reducers] - index.js
3. [reducers] - test.js