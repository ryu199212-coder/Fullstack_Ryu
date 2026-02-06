# 🎮 명령어 모음

## 📦 설치

```bash
# npm 사용
npm install

# yarn 사용
yarn install

# pnpm 사용
pnpm install
```

---

## 🚀 개발 서버 실행

```bash
# npm 사용 (포트 3000)
npm run dev

# yarn 사용
yarn dev

# pnpm 사용
pnpm dev
```

**결과**: http://localhost:3000 자동 실행

---

## 🏗️ 프로덕션 빌드

```bash
# 빌드
npm run build

# 결과 미리보기
npm run preview
```

**빌드 폴더**: `dist/`

---

## 🧹 청소 명령어

```bash
# node_modules 삭제
rm -rf node_modules

# 캐시 삭제
npm cache clean --force

# 빌드 폴더 삭제
rm -rf dist

# 전체 초기화 (재설치 필요)
rm -rf node_modules package-lock.json dist
npm install
```

---

## 🔍 디버깅 명령어

```bash
# 의존성 트리 확인
npm list

# 특정 패키지 버전 확인
npm list react

# 구버전 패키지 확인
npm outdated

# 패키지 업데이트
npm update
```

---

## 📊 프로젝트 정보

```bash
# Node.js 버전
node -v

# npm 버전
npm -v

# 프로젝트 정보
npm info

# 빌드 크기 분석
npm run build
ls -lh dist/
```

---

## 🔧 문제 해결 명령어

### 포트 충돌 해결

**Windows:**
```cmd
# 포트 3000 사용 중인 프로세스 찾기
netstat -ano | findstr :3000

# 프로세스 종료
taskkill /PID [프로세스ID] /F
```

**Mac/Linux:**
```bash
# 포트 3000 사용 중인 프로세스 찾기
lsof -ti:3000

# 프로세스 종료
lsof -ti:3000 | xargs kill -9
```

### 권한 오류 해결 (Mac/Linux)

```bash
# npm 폴더 권한 변경
sudo chown -R $(whoami) ~/.npm
sudo chown -R $(whoami) /usr/local/lib/node_modules

# 또는 nvm 사용 권장
```

### 의존성 오류 해결

```bash
# 1. 캐시 삭제
npm cache clean --force

# 2. node_modules 삭제
rm -rf node_modules package-lock.json

# 3. 재설치
npm install

# 4. 실행
npm run dev
```

---

## 🌐 네트워크 접근 (모바일 테스트)

```bash
# 모든 네트워크 인터페이스에서 접근 가능
npm run dev -- --host

# 또는 vite.config.js 수정:
# server: { host: true, port: 3000 }
```

**접속**: http://192.168.x.x:3000

---

## 📦 패키지 관리

```bash
# 새 패키지 설치
npm install [package-name]

# 개발 의존성 설치
npm install -D [package-name]

# 패키지 제거
npm uninstall [package-name]

# 전역 패키지 설치
npm install -g [package-name]
```

---

## 🔐 환경 변수 설정 (필요시)

```bash
# .env 파일 생성
touch .env

# 내용 예시:
# VITE_API_URL=http://localhost:3000
# VITE_APP_NAME=Recipe Finder
```

**사용법**:
```typescript
const apiUrl = import.meta.env.VITE_API_URL;
```

---

## 📝 Git 명령어 (버전 관리)

```bash
# Git 초기화
git init

# 변경사항 추가
git add .

# 커밋
git commit -m "Initial commit"

# 원격 저장소 추가
git remote add origin [URL]

# 푸시
git push -u origin main
```

---

## 🚀 배포 명령어

### Vercel 배포
```bash
npm install -g vercel
vercel
```

### Netlify 배포
```bash
npm install -g netlify-cli
netlify deploy
```

### GitHub Pages 배포
```bash
# package.json에 추가:
# "homepage": "https://username.github.io/repo-name"

npm run build
git add dist -f
git commit -m "Deploy"
git subtree push --prefix dist origin gh-pages
```

---

## 🧪 테스트 명령어 (추가 설정 필요)

```bash
# Jest 설치
npm install -D jest @testing-library/react

# 테스트 실행
npm test

# 커버리지 확인
npm test -- --coverage
```

---

## 📱 모바일 개발 도구

```bash
# Chrome DevTools에서 모바일 시뮬레이션
# F12 → Toggle device toolbar (Ctrl+Shift+M)

# 또는 실제 모바일 기기에서:
# 같은 Wi-Fi → http://[PC-IP]:3000
```

---

## 🎯 빠른 명령어 체크리스트

```bash
✅ 설치:           npm install
✅ 실행:           npm run dev
✅ 빌드:           npm run build
✅ 미리보기:       npm run preview
✅ 청소:           rm -rf node_modules dist
✅ 재설치:         npm install
✅ 포트 확인:      lsof -ti:3000 (Mac/Linux)
✅ 버전 확인:      node -v && npm -v
```

---

## 🆘 긴급 복구 명령어

```bash
# 1단계: 완전 초기화
rm -rf node_modules package-lock.json dist .cache

# 2단계: npm 캐시 삭제
npm cache clean --force

# 3단계: 재설치
npm install

# 4단계: 실행
npm run dev

# 여전히 안 되면: Node.js 재설치
# https://nodejs.org/
```

---

## 📊 성능 최적화 명령어

```bash
# 빌드 크기 분석
npm run build
npm install -g source-map-explorer
source-map-explorer dist/assets/*.js

# 또는 rollup-plugin-visualizer 사용
npm install -D rollup-plugin-visualizer
```

---

## 🔍 로그 확인

```bash
# 개발 서버 로그
npm run dev

# 빌드 로그
npm run build -- --debug

# 자세한 로그
npm run dev -- --debug --force
```

---

## 🎉 완료!

이제 필요한 모든 명령어를 알고 있습니다!  
**가장 많이 사용하는 명령어**: `npm run dev` 🚀
