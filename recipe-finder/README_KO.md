# 레시피 파인더 - 요리 레시피 추천 사이트

완벽하게 작동하는 요리 레시피 추천 웹 애플리케이션입니다. 복사-붙여넣기만으로 localhost:3000에서 바로 실행할 수 있습니다!

## 📌 주요 기능

✅ **30개의 다양한 레시피** - 한식, 양식, 중식, 일식, 디저트, 건강식 등
✅ **카테고리별 필터링** - 8가지 카테고리
✅ **검색 기능** - 레시피 제목, 설명, 재료로 검색
✅ **소셜 로그인** - 구글, 네이버, 카카오 로그인 지원
✅ **사용자 인증** - 회원가입, 로그인, 프로필 관리
✅ **레시피 작성/수정/삭제** - 사용자가 직접 레시피 관리
✅ **즐겨찾기 기능** - 마음에 드는 레시피 저장
✅ **마이페이지** - 내 레시피, 즐겨찾기 관리
✅ **프리미엄 구독** - 월간/연간 구독 플랜
✅ **TOP 20 작성자** - 종합 점수 기반 순위
✅ **광고 배너** - 8개 레시피마다 광고 표시
✅ **페이징 처리** - 8개 → 광고 → 8개 → 더보기
✅ **재료 상세 정보** - 재료별 상세 정보 다이얼로그
✅ **완전한 Mock 데이터** - 서버 없이 localStorage로 작동

---

## 🚀 빠른 시작 (localhost:3000 실행)

### 1단계: 파일 복사
모든 파일과 폴더를 원하는 위치에 복사-붙여넣기 합니다.

```
recipe-finder/
├── components/          (모든 컴포넌트 파일)
├── services/            (API 서비스 파일)
├── styles/              (CSS 파일)
├── App.tsx              (메인 앱)
├── main.jsx             (엔트리 포인트)
├── index.html           (HTML 템플릿)
├── vite.config.js       (Vite 설정)
├── package.json         (의존성 목록)
└── README_KO.md         (이 파일)
```

### 2단계: 패키지 설치

터미널을 열고 프로젝트 폴더로 이동한 후:

```bash
npm install
```

또는

```bash
yarn install
```

또는

```bash
pnpm install
```

### 3단계: 개발 서버 실행

```bash
npm run dev
```

또는

```bash
yarn dev
```

또는

```bash
pnpm dev
```

### 4단계: 브라우저에서 확인

자동으로 브라우저가 열리며 **http://localhost:3000**에 접속됩니다!

---

## 📦 설치가 안 되는 경우

### Node.js가 없는 경우
1. [Node.js 공식 사이트](https://nodejs.org/)에서 LTS 버전 다운로드
2. 설치 후 터미널에서 확인:
   ```bash
   node -v
   npm -v
   ```

### 의존성 설치 오류
```bash
# npm 캐시 삭제 후 재설치
npm cache clean --force
rm -rf node_modules package-lock.json
npm install
```

### 포트 3000이 이미 사용 중인 경우
```bash
# vite.config.js에서 포트 변경
# server.port: 3000 → 3001 로 수정
```

---

## 🎯 사용 방법

### 1. 기본 사용
- **레시피 검색**: 상단 검색창에서 레시피 이름, 재료 등으로 검색
- **카테고리 필터**: 한식, 양식, 중식, 일식 등 카테고리 선택
- **레시피 상세**: 레시피 카드 클릭 시 상세 정보 표시

### 2. 로그인
- **일반 로그인**: 아무 이메일/비밀번호로 로그인 가능 (Mock)
- **소셜 로그인**: 구글, 네이버, 카카오 버튼 클릭 (Mock)
- **회원가입**: 이름, 이메일, 비밀번호, 전화번호 입력

### 3. 관리자 기능
- **관리자 로그인**: `admin@recipe.com` 으로 로그인
- 프리미엄 레시피 지정 가능
- 모든 레시피 관리 권한

### 4. 레시피 작성
- 로그인 후 "레시피 작성" 버튼 클릭
- 제목, 카테고리, 설명, 재료, 조리법 입력
- 이미지 URL 또는 파일 업로드

### 5. 즐겨찾기
- 레시피 카드의 하트 아이콘 클릭
- 마이페이지에서 즐겨찾기 목록 확인

### 6. 프리미엄 구독
- 상단 "프리미엄" 배지 클릭
- 월간(9,900원) 또는 연간(99,000원) 선택
- Mock 결제로 즉시 구독 활성화

---

## 🔧 기술 스택

- **React 18** - UI 라이브러리
- **Vite** - 빠른 개발 서버
- **Tailwind CSS v4** - 스타일링
- **Lucide React** - 아이콘
- **Sonner** - 토스트 알림
- **LocalStorage** - 데이터 저장 (Mock API)

---

## 📁 폴더 구조

```
recipe-finder/
│
├── components/
│   ├── ui/                    # UI 컴포넌트 (버튼, 입력, 다이얼로그 등)
│   ├── RecipeCard.tsx         # 레시피 카드
│   ├── RecipeDetail.tsx       # 레시피 상세
│   ├── LoginDialog.tsx        # 로그인 다이얼로그
│   ├── MyPageView.tsx         # 마이페이지
│   ├── CreateRecipeDialog.tsx # 레시피 작성
│   ├── AdBanner.tsx           # 광고 배너
│   ├── TopAuthors.tsx         # TOP 20 작성자
│   └── ...
│
├── services/
│   ├── authService.ts         # 인증 서비스 (Mock)
│   ├── recipeService.ts       # 레시피 서비스 (Mock)
│   ├── userService.ts         # 사용자 서비스 (Mock)
│   ├── favoriteService.ts     # 즐겨찾기 서비스 (Mock)
│   └── ingredientData.ts      # 재료 데이터
│
├── styles/
│   └── globals.css            # 전역 스타일 (Tailwind)
│
├── App.tsx                    # 메인 애플리케이션
├── main.jsx                   # 엔트리 포인트
├── index.html                 # HTML 템플릿
├── vite.config.js             # Vite 설정
└── package.json               # 의존성 목록
```

---

## 🎨 데이터 구조

### Recipe (레시피)
```typescript
{
  id: number;
  title: string;
  category: string;
  image: string;
  cookTime: string;
  difficulty: string;
  servings: number;
  description: string;
  ingredients: string[];
  instructions: string[];
  views: number;
  likes: number;
  author: string;
  isPremium?: boolean;
  userId?: string;
  createdAt?: string;
}
```

### User (사용자)
```typescript
{
  id: string;
  name: string;
  email: string;
  phone: string;
  profileImage?: string;
  subscription?: 'none' | 'monthly' | 'yearly';
}
```

---

## 📊 Mock 데이터 설명

모든 데이터는 **localStorage**에 저장되어 서버 없이 작동합니다:

- `user` - 현재 로그인한 사용자 정보
- `favorites` - 즐겨찾기 레시피 ID 목록
- `userRecipes` - 사용자가 작성한 레시피 목록
- `allRecipes` - 전체 레시피 목록 (초기 30개 + 사용자 레시피)

---

## 🔐 관리자 기능

관리자 이메일: `admin@recipe.com` (아무 비밀번호)

**관리자만 가능:**
- ✅ 프리미엄 레시피 지정
- ✅ 모든 레시피 수정/삭제
- ✅ 사용자 관리

---

## 🌟 프리미엄 구독

### 월간 플랜 (9,900원/월)
- 프리미엄 레시피 무제한 열람
- 광고 없는 경험
- 레시피 다운로드 기능

### 연간 플랜 (99,000원/년)
- 월간 플랜 모든 혜택
- 17% 할인 (월 8,250원)
- 독점 레시피 접근

---

## 📈 TOP 20 작성자 점수 계산

```
종합 점수 = (조회수 × 0.5) + (좋아요 × 1.5) + (게시글수 × 2)
```

예시:
- 조회수: 5,245
- 좋아요: 285
- 게시글: 15
- **점수 = (5245 × 0.5) + (285 × 1.5) + (15 × 2) = 742.5**

---

## 🐛 문제 해결

### 레시피가 표시되지 않음
- localStorage 초기화: 개발자 도구 → Application → Local Storage → 전체 삭제
- 페이지 새로고침

### 로그인이 안 됨
- localStorage의 `authToken`과 `user` 확인
- 브라우저 쿠키 설정 확인

### 이미지가 로드되지 않음
- Unsplash 이미지 URL 확인
- 네트워크 연결 확인

### 스타일이 적용되지 않음
```bash
# Tailwind CSS 재빌드
npm run build
```

---

## 📞 지원

문제가 발생하면:
1. 브라우저 콘솔 (F12) 확인
2. localStorage 초기화 시도
3. 페이지 새로고침

---

## 📝 라이선스

이 프로젝트는 학습 및 개인 프로젝트 용도로 자유롭게 사용 가능합니다.

---

## 🎉 즐거운 요리하세요!

**localhost:3000**에서 완벽하게 작동하는 레시피 사이트를 경험하세요! 🍳👨‍🍳👩‍🍳
