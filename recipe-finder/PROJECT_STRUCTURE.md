# 📁 프로젝트 구조

## 전체 폴더 구조

```
recipe-finder/
│
├── 📂 components/                     # React 컴포넌트
│   │
│   ├── 📂 ui/                         # UI 기본 컴포넌트
│   │   ├── button.tsx                 # 버튼
│   │   ├── input.tsx                  # 입력창
│   │   ├── dialog.tsx                 # 다이얼로그/모달
│   │   ├── card.tsx                   # 카드
│   │   ├── tabs.tsx                   # 탭
│   │   ├── select.tsx                 # 선택 박스
│   │   ├── avatar.tsx                 # 아바타/프로필 이미지
│   │   ├── badge.tsx                  # 배지
│   │   ├── separator.tsx              # 구분선
│   │   ├── label.tsx                  # 라벨
│   │   ├── textarea.tsx               # 텍스트 영역
│   │   ├── alert-dialog.tsx           # 경고 다이얼로그
│   │   ├── sonner.tsx                 # 토스트 알림
│   │   └── ...                        # 기타 UI 컴포넌트
│   │
│   ├── 📂 figma/                      # Figma 관련 컴포넌트
│   │   └── ImageWithFallback.tsx      # 폴백 이미지 컴포넌트
│   │
│   ├── RecipeCard.tsx                 # 레시피 카드 컴포넌트
│   ├── RecipeDetail.tsx               # 레시피 상세 페이지
│   ├── LoginDialog.tsx                # 로그인 다이얼로그
│   ├── UserMenu.tsx                   # 사용자 메뉴
│   ├── FavoritesDialog.tsx            # 즐겨찾기 다이얼로그
│   ├── RecipeFinder.tsx               # 레시피 찾기
│   ├── CreateRecipeDialog.tsx         # 레시피 작성 다이얼로그
│   ├── UserRecipesDialog.tsx          # 사용자 레시피 다이얼로그
│   ├── MyPageView.tsx                 # 마이페이지
│   ├── IngredientManagement.tsx       # 재료 관리
│   ├── IngredientInfoDialog.tsx       # 재료 정보 다이얼로그
│   ├── AdBanner.tsx                   # 광고 배너
│   ├── TopAuthors.tsx                 # TOP 작성자
│   └── SubscriptionDialog.tsx         # 구독 다이얼로그
│
├── 📂 services/                       # 서비스 레이어 (Mock API)
│   ├── authService.ts                 # 인증 서비스 (로그인/회원가입)
│   ├── recipeService.ts               # 레시피 서비스 (CRUD)
│   ├── userService.ts                 # 사용자 서비스 (프로필 관리)
│   ├── favoriteService.ts             # 즐겨찾기 서비스
│   ├── ingredientData.ts              # 재료 데이터
│   ├── ingredientDataNew.ts           # 새 재료 데이터
│   ├── apiClient.ts                   # API 클라이언트 (사용 안 함)
│   └── apiConfig.ts                   # API 설정 (사용 안 함)
│
├── 📂 styles/                         # 스타일시트
│   └── globals.css                    # 전역 CSS (Tailwind 포함)
│
├── 📂 supabase/                       # Supabase 관련 (사용 안 함)
│   └── ...
│
├── 📂 utils/                          # 유틸리티
│   └── ...
│
├── 📂 guidelines/                     # 가이드라인
│   └── Guidelines.md
│
├── App.tsx                            # ⭐ 메인 애플리케이션 컴포넌트
├── main.jsx                           # ⭐ 엔트리 포인트 (React DOM 렌더링)
├── index.html                         # ⭐ HTML 템플릿
├── vite.config.js                     # ⭐ Vite 설정 파일
├── package.json                       # ⭐ 의존성 목록
│
├── .gitignore                         # Git 무시 파일
├── README_KO.md                       # 📖 한국어 README
├── QUICK_START.md                     # 🚀 빠른 시작 가이드
├── INSTALL_GUIDE.md                   # 📦 설치 가이드
├── PROJECT_STRUCTURE.md               # 📁 이 파일
└── Attributions.md                    # 라이선스
```

---

## 🎯 핵심 파일 설명

### ⭐ 필수 파일 (절대 삭제 금지)

| 파일 | 용도 | 설명 |
|------|------|------|
| **App.tsx** | 메인 앱 | 전체 애플리케이션 로직과 상태 관리 |
| **main.jsx** | 엔트리 포인트 | React 앱을 DOM에 마운트 |
| **index.html** | HTML 템플릿 | 기본 HTML 구조 |
| **package.json** | 의존성 관리 | npm 패키지 목록 |
| **vite.config.js** | Vite 설정 | 개발 서버 및 빌드 설정 |

---

## 📂 폴더별 상세 설명

### 1. components/
React 컴포넌트들이 위치한 폴더

#### ui/ (UI 기본 컴포넌트)
- 재사용 가능한 기본 UI 컴포넌트
- 버튼, 입력창, 카드, 다이얼로그 등
- Tailwind CSS 기반 스타일링

#### 메인 컴포넌트
- **RecipeCard**: 레시피 목록에 표시되는 카드
- **RecipeDetail**: 레시피 상세 정보 페이지
- **LoginDialog**: 로그인/회원가입 모달
- **MyPageView**: 사용자 마이페이지
- **CreateRecipeDialog**: 레시피 작성 폼
- **TopAuthors**: TOP 20 작성자 리스트
- **SubscriptionDialog**: 프리미엄 구독 모달

---

### 2. services/
Mock API 서비스 레이어 (localStorage 기반)

#### authService.ts
```typescript
// 제공 기능:
- login()              // 로그인
- signup()             // 회원가입
- socialLogin()        // 소셜 로그인
- getCurrentUser()     // 현재 사용자
- logout()             // 로그아웃
```

#### recipeService.ts
```typescript
// 제공 기능:
- getAllRecipes()      // 전체 레시피 조회
- getRecipeById()      // 레시피 상세 조회
- createRecipe()       // 레시피 생성
- updateRecipe()       // 레시피 수정
- deleteRecipe()       // 레시피 삭제
- toggleLike()         // 좋아요 토글
- togglePremium()      // 프리미엄 지정 (관리자만)
```

#### userService.ts
```typescript
// 제공 기능:
- getProfile()         // 프로필 조회
- updateProfile()      // 프로필 수정
- uploadProfileImage() // 프로필 이미지 업로드
- deleteProfileImage() // 프로필 이미지 삭제
- changePassword()     // 비밀번호 변경
- getTopAuthors()      // TOP 20 작성자
- subscribe()          // 구독 신청
- cancelSubscription() // 구독 취소
```

#### favoriteService.ts
```typescript
// 제공 기능:
- getFavorites()       // 즐겨찾기 목록
- addFavorite()        // 즐겨찾기 추가
- removeFavorite()     // 즐겨찾기 제거
- toggleFavorite()     // 즐겨찾기 토글
```

---

### 3. styles/
CSS 스타일시트

#### globals.css
- Tailwind CSS import
- 전역 스타일 정의
- 커스텀 CSS 변수
- 폰트 설정

---

## 🔄 데이터 흐름

```
사용자 액션
    ↓
App.tsx (상태 관리)
    ↓
Component (UI 렌더링)
    ↓
Service (API 호출 Mock)
    ↓
localStorage (데이터 저장)
```

---

## 💾 localStorage 구조

```javascript
localStorage = {
  "user": {                    // 현재 사용자
    id: "1234567890",
    name: "홍길동",
    email: "test@test.com",
    phone: "010-1234-5678",
    subscription: "monthly"
  },
  
  "favorites": [1, 5, 12, 20], // 즐겨찾기 ID 목록
  
  "userRecipes": [             // 사용자 레시피
    {
      id: 100,
      title: "내가 만든 레시피",
      // ...
    }
  ],
  
  "allRecipes": [              // 전체 레시피 (초기 30개)
    // ...
  ]
}
```

---

## 🎨 컴포넌트 계층 구조

```
App
├── Header
│   ├── Logo
│   ├── SearchBar
│   └── UserMenu
│       ├── LoginDialog
│       └── ProfileMenu
│
├── Main
│   ├── CategoryTabs
│   ├── RecipeGrid
│   │   ├── RecipeCard (x30)
│   │   └── AdBanner
│   │
│   ├── RecipeDetail (Modal)
│   ├── CreateRecipeDialog
│   ├── FavoritesDialog
│   └── MyPageView
│
└── Footer
    ├── TopAuthors
    └── SubscriptionDialog
```

---

## 📊 파일 크기 분석

| 폴더/파일 | 파일 수 | 예상 크기 |
|----------|---------|-----------|
| components/ui/ | ~40개 | ~200KB |
| components/ | ~15개 | ~500KB |
| services/ | ~6개 | ~50KB |
| node_modules/ | ~1000+ | ~200MB |
| dist/ (빌드 후) | ~10개 | ~500KB |

---

## 🔧 수정 가이드

### 포트 변경
📁 `vite.config.js`
```javascript
server: {
  port: 3000,  // ← 여기 수정
}
```

### 초기 레시피 수정
📁 `App.tsx`
```typescript
const sampleRecipes: Recipe[] = [
  // ← 여기서 레시피 추가/수정
]
```

### 스타일 수정
📁 `styles/globals.css`
```css
/* 전역 스타일 수정 */
```

### 관리자 이메일 변경
📁 `App.tsx`
```typescript
const isAdmin = user?.email === "admin@recipe.com"; // ← 수정
```

---

## 📦 빌드 출력 구조

```bash
npm run build
```

```
dist/
├── index.html
├── assets/
│   ├── index-[hash].js      # 메인 JS
│   ├── index-[hash].css     # 메인 CSS
│   └── [images]             # 이미지들
└── ...
```

---

## 🚀 배포 가능한 파일

빌드 후 `dist/` 폴더를 다음 플랫폼에 배포 가능:

- ✅ Vercel
- ✅ Netlify
- ✅ GitHub Pages
- ✅ AWS S3 + CloudFront
- ✅ Firebase Hosting

---

## 🔍 파일 검색 팁

### VSCode에서 빠른 검색
```
Ctrl+P (Cmd+P on Mac)  → 파일명으로 검색
Ctrl+Shift+F           → 전체 텍스트 검색
```

### 특정 컴포넌트 찾기
```
RecipeCard     → components/RecipeCard.tsx
authService    → services/authService.ts
Button         → components/ui/button.tsx
```

---

## 📝 네이밍 규칙

- **컴포넌트**: PascalCase (예: `RecipeCard.tsx`)
- **서비스**: camelCase (예: `authService.ts`)
- **상수**: UPPER_CASE (예: `RECIPES_PER_PAGE`)
- **함수**: camelCase (예: `handleLogin`)

---

## 🎉 완료!

이제 프로젝트 구조를 완벽하게 이해했습니다!  
각 파일의 역할을 파악하고 필요한 부분을 수정해보세요! 🚀
