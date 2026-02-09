## Part002. aws
■1. aws 회원가입
: https://ap-southeast-2.signin.aws.amazon.com/

■2. 콘솔로그인 
: 수업시작하기전에 콘솔로그인~!

■3. EC2 인스턴스 생성
**실행 항목**
- [x] EC2 인스턴스 생성  
- [x] OS 선택 (Ubuntu 권장)  
- [x] 보안 그룹 설정 (22, 80, 443)  
- [x] 키페어 다운로드 및 저장  
- [ ] SSH 접속 테스트 완료  

   1. 퍼블릭 IPv4 주소  / ssh
      54.180.142.97 

      ssh -i "thejoa703.pem" ubuntu@ec2-13-124-236-156.ap-northeast-2.compute.amazonaws.com

   ```접속오류 
   PS D:\cicd> ssh -i "thejoa703.pem" ubuntu@ec2-54-206-106-20.ap-southeast-2.compute.amazonaws.com
   Bad permissions. Try removing permissions for user: NT AUTHORITY\\Authenticated Users (S-1-5-11) on file D:/cicd/thejoa703.pem.
   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
   @         WARNING: UNPROTECTED PRIVATE KEY FILE!          @
   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
   Permissions for 'thejoa703.pem' are too open.
   It is required that your private key files are NOT accessible by others.
   This private key will be ignored.
   Load key "thejoa703.pem": bad permissions
   ubuntu@ec2-54-206-106-20.ap-southeast-2.compute.amazonaws.com: Permission denied (publickey).
   PS D:\cicd> 

   ```
   > vs code에서 powershell 접속
   ```
   icacls "C:\Users\TJ-BU-703-강사PC\Desktop\cicd\thejoa703.pem" /reset
   icacls "C:\Users\TJ-BU-703-강사PC\Desktop\cicd\thejoa703.pem" /inheritance:r
   echo $env:USERNAME
   icacls "C:\Users\TJ-BU-703-강사PC\Desktop\cicd\thejoa703.pem" /grant:r "$env:TJ-BU-703-강사PC":R
   ```

■4. EC2에서 nginx
- 웹서버연결
- back와 front 연결설정

1. nginx 설치
```
sudo apt update
sudo apt install  nginx  -y
``` 

2. nginx 설정파일 수정
2-1.
```
sudo vi   /etc/nginx/sites-available/default
```

2-2. esc 눌러서 명령모드로 전환 
2-3. :%d 입력한뒤에 enter → 전체삭제
2-4. i 눌러서 입력모드전환  →  붙여넣기
2-5. esc   →  :wq!  저장후 종료
```


server {
    listen 80;
    server_name 54.180.142.97;

    # 프론트엔드 (Next.js SSR 서버)
    location / {
        proxy_pass http://localhost:3000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header Cookie $http_cookie; 
    }

    # 백엔드 - 유저 인증 (/auth)
    location /auth {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 일반 API (/api)
    location /api {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 소셜 로그인 (/oauth2)
    location /oauth2 {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 카카오/구글 리다이렉트 처리
    location /login/oauth2/ {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # 프론트엔드에서 처리해야 하는 콜백
    location /oauth2/callback {
        proxy_pass http://localhost:3000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 정적 파일 경로
    location /uploads/ {
        alias /home/ubuntu/app/back/build/libs/uploads/;
        autoindex off;
    }
}
```
설명)
   location / {    ←  /여기경로로
        proxy_pass http://localhost:3000;   ←   포트번호 3000번호
        proxy_http_version 1.1;    ←  통신시 http 
        proxy_set_header Upgrade $http_upgrade;  ←  헤더 그대로  전달
        proxy_set_header Connection "upgrade";  ←  헤더 강제 설정
        proxy_set_header Host $host;  ← host 백엔드로 전송
        proxy_cache_bypass $http_upgrade;  ←  연결시 캐시 사용안함.
        proxy_set_header Cookie $http_cookie; ←  쿠키백엔드 서버로 전달
    }

3. nginx 실행 및 테스트
```
sudo nginx -t
sudo systemctl restart nginx
```


■5.  ECR 리포지토리
- 애플리케이션을 docker이미지로 빌드해서 
  ecr에 올려두면 어디서든지 가져다가 사용할수 있게

※ AWS콘솔창에서 검색 ECR - 생성

1. **리포지토리 이름 입력 : `thejoa703`**  
   - 예: `my-app-repo`  
   - 규칙:  
     - 소문자로 시작해야 함  
     - 소문자, 숫자, 특수문자(`._-/`)만 사용 가능  
     - 최소 2자, 최대 256자  
   - `734910190986.dkr.ecr.ap-northeast-2.amazonaws.com/` 이건 **리포지토리 URI의 기본 형식**이고, 뒤에 붙는 이름을 직접 정해야 합니다.  
     → 즉, `734910190986.dkr.ecr.ap-northeast-2.amazonaws.com/my-app-repo` 이런 식으로 완성됩니다.  
<br/>

2. **이미지 태그 설정 (Mutable vs Immutable) : `Mutable`**  
   - **Mutable**: 같은 태그(`latest` 등)를 덮어쓸 수 있음 → 개발/연습용에 적합  
   - **Immutable**: 태그를 덮어쓸 수 없음 → 운영 환경에서 안정성 확보용  
   → 연습용이라면 **Mutable**로 두시면 됩니다.  
<br/>

3. **암호화 설정**  
   - 기본값(KMS 관리형 키) 그대로 두셔도 무방합니다.  
<br/>

4. **생성 버튼 클릭**  
   - 그러면 새 리포지토리가 만들어지고, 목록에 `my-app-repo`가 나타납니다.  

677035504456.dkr.ecr.ap-northeast-2.amazonaws.com/thejoa703

■6. 필수 패키지 설치 / 애플리케이션 디렉토리 생성

1. EC2접속 후 시스템 업데이트  
   ```bash
   sudo apt update && sudo apt upgrade -y
   ```
2. Java 17 설치  
   ```bash
   sudo apt install openjdk-17-jdk -y
   java -version
   ```
3. Git 설치  
   ```bash
   sudo apt install git -y
   ```
4. Docker 설치  
   ```bash
   sudo apt install docker.io -y
   sudo systemctl enable docker && sudo systemctl start docker
   sudo usermod -aG docker $USER
   ```
5. Node.js & NPM 설치  
   ```bash
   curl -fsSL https://deb.nodesource.com/setup_24.x | sudo -E bash -

   sudo apt install -y nodejs
   ```
6. PM2 설치  
   ```bash
   sudo npm install -g pm2
   ```

7. Nginx 설치  (위에서 설치완료)
   ```bash
   sudo apt install nginx -y
   ```
8. 실행 디렉토리 생성 확인
   ```bash 
   ubuntu@ip-172-31-43-195:~/app/back/build/libs$ ls 
   BOOT-INF  app.jar  app.tar.gz  back  back-0.0.1-SNAPSHOT.jar  uploads
   ```
   ```bash
   mkdir  /home/ubuntu/app/back/build/libs/uploads/
   mkdir -p /home/ubuntu/app/back/build/libs/uploads/

   sudo chmod 755 /home/ubuntu
   sudo chmod 755 /home/ubuntu/app
   sudo chmod 644 /home/ubuntu/app/back/build/libs/uploads
   sudo chmod 644 /home/ubuntu/app/back/build/libs/uploads/* 
   ```
   user(r:4 w:2 x:1) group(r:4 w:- x:1) other(r:4 w:- x:1)


 
9. Oracle XE 컨테이너 실행
   ```bash
   sudo docker run -d --name oracle-xe  -p 1521:1521 -p 5500:5500   -e ORACLE_PASSWORD=oracle  gvenzl/oracle-xe:11
   ```
10. 접속확인
   ```bash    
   sudo docker logs -f oracle-xe | grep "DATABASE IS READY TO USE"
   sudo docker exec -it oracle-xe  sqlplus system/oracle@XE

   CREATE USER scott IDENTIFIED BY tiger;
   GRANT CONNECT, RESOURCE TO scott;
   exit;

   sudo docker exec -it oracle-xe sqlplus scott/tiger@XE
   ```
  
11. Docker Redis
  ```bash 
  sudo docker run -d --name redis   -p 6379:6379   --restart=always   redis:7
  ```
12. 컨테이너 상태 확인
   ```bash
   sudo docker ps
   ```
   → `redis` 컨테이너가 `Up` 상태인지, `PORTS`에 `0.0.0.0:6379->6379/tcp`가 표시되는지 확인하세요.

13. 컨테이너 내부 접속 후 ping 테스트
   ```bash
   sudo docker exec -it redis redis-cli ping
   ```
   → `PONG` 이 나오면 정상 실행 중입니다.

 14. EC2 자체에서 자동 실행 설정
 ```bash
 sudo docker update --restart=always oracle-xe 
 sudo docker update --restart=always redis
```

 15. oracle 11은 화면에 뜨기까지 2~3분 , 최대 5분까지 걸림
 ```bash
 sudo docker logs -f oracle-xe
 ```

 16. swap
 ```bash
  sudo fallocate -l 2G /swapfile
  sudo chmod 600 /swapfile
  sudo mkswap /swapfile
  sudo swapon /swapfile
  echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
  free -h
 ```

  sudo fallocate -l 2G /swapfile  ← 2GB 파일생성
  sudo chmod 600 /swapfile   ← 권한 유저(r:읽기 , w: 쓰기 , x: 실행)
  sudo mkswap /swapfile  ← 스왑초기화
  sudo swapon /swapfile ← 스왑활성화
  echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab   ← 설정파일 끝에 추가
  free -h  ← 메모리 확인



■7. IM 사용자/역할 생성
1. IAM 콘솔 → 사용자 추가  
2. 권한 정책: `AmazonEC2FullAccess`, `AmazonECS_FullAccess`, `AmazonEC2ContainerRegistryFullAccess`  
3. Access Key / Secret Key 발급  
4. GitHub Secrets에 저장  

- `AmazonEC2FullAccess`   →  EC2 인스턴스 관리 
- `AmazonECS_FullAccess`     →  ECS/Faragate 서비스관리
- `AmazonEC2ContainerRegistryFullAccess`   →  Docker이미지를 푸시/풀 할수 있게. 레지스트리 접근  
 

```
Access Key : YOUR_AWS_ACCESS_KEY
Secret Key : YOUR_AWS_ACCESS_KEY
```


## Part003. ci/cd

■8. GitHub Secrets 설정
> ci/cd
ci : (지속적 통합)
  - 공용저장소에 자주병합
  - 자동빌드/테스트를 통해서 버그 조기에 발견
cd : (지속적 제공/배포)
  - 자동으로 프로덕션 환경에 배포

```
Name: EC2_HOST
Secret: 13.xxx.xxx.xxx (현재 EC2 퍼블릭 IP)   43.200.175.169

Name: EC2_USER
Secret: ubuntu

Name: EC2_SSH_KEY
Secret: .pem 파일 내용을 그대로 붙여넣기

Name: AWS_ACCESS_KEY_ID
Secret: IAM에서 발급받은 Access Key      YOUR_AWS_ACCESS_KEY

Name: AWS_SECRET_ACCESS_KEY
Secret: IAM에서 발급받은 Secret Key      YOUR_AWS_ACCESS_KEY

Name: AWS_REGION
Secret: ap-northeast-2

Name: AWS_ACCOUNT_ID
Secret: 12자리 숫자      677035504456

Name: ECR_REPO
Secret: ECR 저장소 이름 (예: my-app-repo)    thejoa
```
677035504456.dkr.ecr.ap-northeast-2.amazonaws.com/thejoa

```
Name: DB_USERNAME
Secret: scott

Name: DB_PASSWORD
Secret: tiger 

Name: JWT_SECRET
Secret: this-is-a-very-long-random-secret-key-64chars-minimum-1234567890!@#$%^&*() 

Name: GOOGLE_CLIENT_ID
Secret: 64846044024-nh70uh12qkhkvlhnaiifl7q316svssm1.apps.googleusercontent.com

Name: GOOGLE_CLIENT_SECRET
Secret: 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

Name: KAKAO_CLIENT_ID 
Secret: 01e8784b1e7533ea33b7e7a3f9af11f2 

Name: NAVER_CLIENT_ID
Secret: 0OURtR8HMXxp5CL5pVdi

Name: NAVER_CLIENT_SECRET
Secret: qtVfwbBEBl
```
```
Name: NEXT_PUBLIC_API_BASE_URL
Secret: http://43.200.175.169
```



■9. Git Actions 워크플로우 연결

**구조확인**
```
thejoa703/                ← 깃허브 저장소 루트
├── .git                  ← Git 저장소 메타데이터
├── .gitignore            ← 불필요한 파일 제외 설정
├── BACK/                 ← 백엔드 (Spring Boot)
│   ├── src/              ← 소스 코드
│   ├── build.gradle      ← Gradle 빌드 설정
│   └── ...               ← 기타 설정/리소스
├── FRONT/                ← 프론트엔드 (React + Next.js)
│   ├── src/              ← 소스 코드
│   ├── package.json      ← npm 의존성 관리
│   └── ...               ← 기타 설정/리소스
└── .github/
    └── workflows/
        └── deploy.yml    ← GitHub Actions 워크플로우 파일
```

0. back/front 코드 수정  
```
54.180.92.192
ssh -i "thejoa703.pem" ubuntu@ec2-54-180-92-192.ap-northeast-2.compute.amazonaws.com
```
```
깃허브: secret 
EC2_HOST , NEXT_PUBLIC_API_BASE_URL
```

```
```
sudo vi   /etc/nginx/sites-available/default

2-2. esc 눌러서 명령모드로 전환 
2-3. :%d 입력한뒤에 enter → 전체삭제
2-4. i 눌러서 입력모드전환  →  붙여넣기
2-5. esc   →  :wq!  저장후 종료 

 
server {
    listen 80;
    server_name 43.200.175.169;

    # 프론트엔드 (Next.js SSR 서버)
    location / {
        proxy_pass http://localhost:3000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header Cookie $http_cookie; 
    }

    # 백엔드 - 유저 인증 (/auth)
    location /auth {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 일반 API (/api)
    location /api {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 소셜 로그인 (/oauth2)
    location /oauth2 {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 카카오/구글 리다이렉트 처리
    location /login/oauth2/ {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # 프론트엔드에서 처리해야 하는 콜백
    location /oauth2/callback {
        proxy_pass http://localhost:3000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 정적 파일 경로
    location /uploads/ {
        alias /home/ubuntu/app/back/build/libs/uploads/;
        autoindex off;
    }
}

```


1. .github/workflows/deploy.yml
```
name: Deploy Fullstack App

on:
  push:
    branches:
      - main   # main 브랜치에 push 될 때만 실행    

jobs:
  backend:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Grant execute permission for gradlew
        run: chmod +x ./gradlew
        working-directory: back

      - name: Build Spring Boot App
        run: ./gradlew clean build -x test
        working-directory: back

      - name: Debug Backend Files
        run: |
          echo "=== back 디렉터리 ==="
          ls -al back
          echo "=== build/libs 디렉터리 ==="
          ls -al back/build/libs

      - name: Find JAR file (fat jar만 선택)
        run: echo "JAR_FILE=$(ls back/build/libs/*SNAPSHOT.jar | grep -v plain | head -n 1)" >> $GITHUB_ENV
        # plain.jar 제외하고 실행 가능한 fat JAR만 선택

      - name: Debug JAR_FILE
        run: echo "선택된 JAR_FILE=${{ env.JAR_FILE }}"

      - name: Ensure JAR exists
        run: |
          if [ ! -f "${{ env.JAR_FILE }}" ]; then
            echo "❌ JAR file not found: ${{ env.JAR_FILE }}"
            ls -al back/build/libs
            exit 1
          fi
          echo "✅ JAR file found: ${{ env.JAR_FILE }}"

      - name: Create Backend .env from Secrets
        run: |
          echo "DB_USERNAME=${{ secrets.DB_USERNAME }}" >> back/.env
          echo "DB_PASSWORD=${{ secrets.DB_PASSWORD }}" >> back/.env
          echo "JWT_SECRET=${{ secrets.JWT_SECRET }}" >> back/.env
          echo "GOOGLE_CLIENT_ID=${{ secrets.GOOGLE_CLIENT_ID }}" >> back/.env
          echo "GOOGLE_CLIENT_SECRET=${{ secrets.GOOGLE_CLIENT_SECRET }}" >> back/.env
          echo "KAKAO_CLIENT_ID=${{ secrets.KAKAO_CLIENT_ID }}" >> back/.env
          echo "NAVER_CLIENT_ID=${{ secrets.NAVER_CLIENT_ID }}" >> back/.env
          echo "NAVER_CLIENT_SECRET=${{ secrets.NAVER_CLIENT_SECRET }}" >> back/.env

      - name: Ensure app directory exists on EC2
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: mkdir -p /home/ubuntu/app

      - name: Copy Backend build/libs to EC2
        uses: appleboy/scp-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          source: "back/build/libs/*"
          target: "/home/ubuntu/app/back/build/libs"
        # 🔧 build/libs 전체를 복사해서 EC2에서 직접 실행

      - name: Debug EC2 app directory
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: ls -lh /home/ubuntu/app/back/build/libs

      - name: Copy Backend .env to EC2
        uses: appleboy/scp-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          source: "back/.env"
          target: "/home/ubuntu/app"

      - name: Wait for Oracle & Redis readiness on EC2
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: |
            echo "⏳ Waiting for Oracle DB (1521) and Redis (6379)..."
            for i in {1..30}; do
              nc -z localhost 1521 && nc -z localhost 6379
              if [ $? -eq 0 ]; then
                echo "✅ Oracle & Redis are ready"
                break
              fi
              echo "Not ready yet... retry in 10s ($i/30)"
              sleep 10
            done

      - name: Run Backend on EC2 with pm2 (build/libs에서 실행)
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: |
            cd /home/ubuntu/app/back/build/libs
            pm2 delete backend || true
            export $(cat /home/ubuntu/app/.env | xargs)
            pm2 start java --name backend -- -jar ${{ env.JAR_FILE }}



  frontend:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '18'

      # 🔑 Secrets 기반으로 .env.production 먼저 생성
      - name: Create Frontend .env.production
        run: |
          echo "NEXT_PUBLIC_API_BASE_URL=${{ secrets.NEXT_PUBLIC_API_BASE_URL }}" > front/.env.production

      # 빌드 전에 .env.production이 반영되도록 순서 조정   
      - name: Build Frontend
        run: |
          npm install
          npm run build -- --no-lint
        working-directory: front

      - name: Debug Frontend Files
        run: |
          ls -al front
          ls -al front/.next || true
          ls -al front/public || true

      - name: Ensure front directory exists on EC2
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: mkdir -p /home/ubuntu/front

      # 빌드 결과물(.next, public, package.json,   .env.production)을 압축     
      - name: Archive frontend build
        run: |
          cd front
          tar -czf ../frontend-build.tar.gz .next public package.json .env.production

      - name: Copy frontend archive to EC2
        uses: appleboy/scp-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          source: "frontend-build.tar.gz"
          target: "/home/ubuntu/front"

      - name: Extract archive on EC2 (기존 빌드 정리 후)
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: |
            cd /home/ubuntu/front
            rm -rf .next public
            tar -xzf frontend-build.tar.gz

      - name: Run Frontend on EC2 with pm2
        uses: appleboy/ssh-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_SSH_KEY }}
          script: |
            cd /home/ubuntu/front
            npm install --production
            pm2 delete frontend || true
            pm2 start npm --name "frontend" -- run start

```

2. 빌드
```
git add .
git commit -m "test deploy"
git push origin main
```

3.  ec2접속해서 확인
```
pm2 list
pm2 logs backend
```


4. 외부테스트 :  http://54.180.142.97


```
Run ./gradlew clean build -x test
  ./gradlew clean build -x test
  shell: /usr/bin/bash -e {0}
  env:
    JAVA_HOME: /opt/hostedtoolcache/Java_Temurin-Hotspot_jdk/17.0.18-8/x64
    JAVA_HOME_17_X64: /opt/hostedtoolcache/Java_Temurin-Hotspot_jdk/17.0.18-8/x64
Error: Unable to access jarfile /home/runner/work/thejoa703-iam/thejoa703-iam/back/gradle/wrapper/gradle-wrapper.jar
Error: Process completed with exit code 1.
````

```
sudo  rm -rf  app
sudo  rm -rf  front
```

 
■10. Social

> kakao  / naver 주소 바꾸기
