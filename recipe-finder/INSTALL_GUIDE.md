# 📦 설치 가이드 - 완벽 설명서

## 🎯 목표
localhost:3000에서 레시피 추천 사이트를 완벽하게 실행하기

---

## 📋 사전 준비

### 1. Node.js 설치 확인
터미널(명령 프롬프트)을 열고 다음 명령어 실행:

```bash
node -v
```

✅ **v16.0.0 이상**이 표시되면 OK!  
❌ 표시되지 않으면 → https://nodejs.org/ 에서 LTS 버전 다운로드

```bash
npm -v
```

✅ **7.0.0 이상**이 표시되면 OK!

---

## 🗂️ 프로젝트 파일 확인

다음 폴더와 파일이 모두 있는지 확인:

```
recipe-finder/
│
├── components/           ✅ (필수)
│   ├── ui/              ✅ (필수)
│   ├── figma/           ✅ (필수)
│   └── ...
│
├── services/            ✅ (필수)
│   ├── authService.ts
│   ├── recipeService.ts
│   ├── userService.ts
│   └── ...
│
├── styles/              ✅ (필수)
│   └── globals.css
│
├── App.tsx              ✅ (필수)
├── main.jsx             ✅ (필수)
├── index.html           ✅ (필수)
├── package.json         ✅ (필수)
└── vite.config.js       ✅ (필수)
```

---

## 🚀 설치 단계

### 1단계: 프로젝트 폴더로 이동

**Windows:**
```cmd
cd C:\Users\사용자이름\recipe-finder
```

**Mac/Linux:**
```bash
cd ~/recipe-finder
```

### 2단계: 패키지 설치

```bash
npm install
```

⏳ 설치 시간: 약 1~3분  
📦 설치되는 패키지: React, Vite, Tailwind CSS 등

**설치 중 나타나는 메시지:**
```
added 234 packages in 45s
```
✅ 이런 메시지가 나오면 성공!

### 3단계: 개발 서버 실행

```bash
npm run dev
```

**성공 메시지:**
```
  VITE v5.1.4  ready in 523 ms

  ➜  Local:   http://localhost:3000/
  ➜  Network: use --host to expose
  ➜  press h + enter to show help
```

### 4단계: 브라우저에서 확인

자동으로 브라우저가 열리거나,  
수동으로 http://localhost:3000 접속

---

## ✅ 설치 성공 확인

브라우저에서 다음이 보이면 성공:

1. ✅ 상단에 "레시피 파인더" 로고
2. ✅ 검색창
3. ✅ 카테고리 탭 (전체, 한식, 양식 등)
4. ✅ 레시피 카드 30개
5. ✅ "로그인" 버튼 (우측 상단)

---

## 🔧 문제 해결

### ❌ 문제 1: `command not found: npm`
**원인**: Node.js가 설치되지 않음  
**해결**: https://nodejs.org/ 에서 다운로드 및 설치

---

### ❌ 문제 2: `Cannot find module 'react'`
**원인**: 패키지가 설치되지 않음  
**해결**:
```bash
rm -rf node_modules package-lock.json
npm install
```

---

### ❌ 문제 3: `Port 3000 is already in use`
**원인**: 포트 3000이 이미 사용 중

**해결 방법 1** - 다른 포트 사용:
```bash
# vite.config.js 파일 열기
# 다음 부분 수정:
server: {
  port: 3001,  # 3000 → 3001로 변경
  open: true,
}
```

**해결 방법 2** - 기존 프로세스 종료:

**Windows:**
```cmd
netstat -ano | findstr :3000
taskkill /PID [프로세스ID] /F
```

**Mac/Linux:**
```bash
lsof -ti:3000 | xargs kill -9
```

---

### ❌ 문제 4: 화면이 하얗게 나옴
**원인**: JavaScript 오류

**해결**:
1. F12 키 눌러 개발자 도구 열기
2. Console 탭에서 오류 확인
3. 브라우저 새로고침 (Ctrl+Shift+R)

---

### ❌ 문제 5: 스타일이 깨짐
**원인**: Tailwind CSS 미적용

**해결**:
```bash
npm run build
npm run dev
```

---

### ❌ 문제 6: EACCES 권한 오류 (Mac/Linux)
**해결**:
```bash
sudo chown -R $(whoami) ~/.npm
sudo chown -R $(whoami) /usr/local/lib/node_modules
```

---

## 🔄 재설치 방법

완전히 다시 시작하려면:

```bash
# 1. 기존 파일 삭제
rm -rf node_modules
rm -rf package-lock.json
rm -rf dist

# 2. 캐시 삭제
npm cache clean --force

# 3. 재설치
npm install

# 4. 실행
npm run dev
```

---

## 🌐 브라우저 호환성

✅ **권장 브라우저:**
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

❌ **지원하지 않는 브라우저:**
- Internet Explorer (모든 버전)

---

## 📱 모바일에서 테스트

같은 Wi-Fi 네트워크에서:

1. 터미널에서 IP 주소 확인:
   ```bash
   # Mac/Linux
   ifconfig | grep inet
   
   # Windows
   ipconfig
   ```

2. 브라우저에서 접속:
   ```
   http://192.168.x.x:3000
   ```

---

## 📊 시스템 요구사항

| 항목 | 최소 | 권장 |
|------|------|------|
| **Node.js** | 16.0 | 18.0+ |
| **npm** | 7.0 | 9.0+ |
| **RAM** | 2GB | 4GB+ |
| **디스크** | 500MB | 1GB+ |
| **OS** | Windows 10+, macOS 10.15+, Ubuntu 18.04+ | 최신 버전 |

---

## 🎓 다음 단계

설치가 완료되었다면:

1. **README_KO.md** - 전체 기능 설명
2. **QUICK_START.md** - 빠른 시작 가이드
3. 브라우저에서 레시피 탐색 시작!

---

## 📞 추가 도움

여전히 문제가 있나요?

1. **브라우저 콘솔** (F12) 확인
2. **터미널 오류 메시지** 캡처
3. `npm -v` 및 `node -v` 결과 확인

---

## 🎉 설치 완료!

이제 **http://localhost:3000**에서  
완벽하게 작동하는 레시피 사이트를 즐기세요! 🍳👨‍🍳👩‍🍳
