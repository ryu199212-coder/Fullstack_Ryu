## Ubuntu 24.04 컨테이너 실행 방법
1. **이미지 다운로드**
   ```bash
   docker pull ubuntu:24.04
   ```

2. **컨테이너 실행**
   ```bash 
   docker run -it --name myubuntu ubuntu:24.04 bash
   ```
   → 실행하면 컨테이너 내부의 쉘(`bash`) 

3. **컨테이너 내부에서 패키지 업데이트**
   ```bash
   apt update && apt upgrade -y
   ```

4. **컨테이너 종료 후 다시 실행하기**
   - 실행 중인 컨테이너 확인:
     ```bash
     docker ps -a
     ```
   - 컨테이너 재실행:
     ```bash
     docker start -ai <컨테이너_ID>
     docker start -ai myubuntu 
     ```
    - 실행 중인 컨테이너에 접속:
     ```bash
    docker exec -it myubuntu bash
    ```

## 2. linux 사용자
    - # root 사용자
    - $ 일반 사용자

## 3. 기본명령어
```bash
# 날짜
date
# 출력
echo hello
# 명령어 위치 확인
which date
# 명령어 설명서
man
```

```bash
apt upate
apt install man-db
unminimize
```

```bash
help echo → 쉘 내장명령어
man  date → 실행파일
q → 빠져나오기
type echo →
type date →
```

```
Q1. hi 출력
- echo hi
Q2. date 사용방법 확인
- type date
- man date
- esc q
```

## 4.파일
### 파일 및 디렉토리 생성
- touch 파일명 : 빈 파일 생성
- mkdir 디렉토리명 : 새 디렉토리 생성
- mkdir -p 경로/하위디렉토리 : 중첩 디렉토리 생성

### 파일 확인 및 경로 이동
- ls : 현재 디렉토리 목록 보기
- ls -l : 상세 정보 포함 목록
- pwd : 현재 경로 출력
- cd 디렉토리명 : 디렉토리 이동
- cd .. : 상위 디렉토리로 이동

### 삭제 및 복사
- rm 파일명 : 파일 삭제
- rm -r 디렉토리명 : 디렉토리 삭제
- cp 원본 대상 : 파일 복사
- mv 원본 대상 : 파일 이동 또는 이름 변경

Q1. testdir 폴더만들기
Q2. 폴더안에 file1.txt파일만들기
Q3. 파일 확인 - 디렉토리인지 폴더인지

### 파일에 적기(1) >덮어쓰기, >>이어쓰기
- echo "하고싶은말" > 파일명
- cat 파일명
- echo "하고싶은말" >> 파일명

### 파일에 적기(2) 여러줄 쓰기, 파일편집
### 여러줄
- cat > file2.txt 
- 첫 번째 줄 
- 두 번째 줄 
- Ctrl+D # 입력 종료

### vi 에디터
1. sudo vi 파일명 실행  
2. vi 안에서 Esc 눌러 명령 모드로 전환   
3. i 눌러 입력 모드로 전환 → 새 설정 붙여넣기  
4. Esc → :wq → 저장 후 종료   

```
apt update
apt install vim
```
Q1.file1.txt에 입력
    apple
    banana
    coconut

root@53f78cda83a8:~/testdir# echo "apple" > file1.txt
root@53f78cda83a8:~/testdir# echo "banana" >> file1.txt
root@53f78cda83a8:~/testdir# echo "coconut" >> file1.txt
root@53f78cda83a8:~/testdir# cat file1.txt
apple
banana
coconut

Q1. 파일만들기   mylinux.txt
Q2. 파일안에 답채우기
-    출력 echo
-    사용서 man
-    파일생성 touch
-    디렉토리만들기 mkdir
-    목록보기 ls -al
-    상위이동 cd.
-    파일,폴더삭제 rm -r
-    file1.txt 을 back.txt으로 파일복사 cp
-    back.txt를 test.txt로 이름변경 mv
Q3. vi이용해서 맨위에 작성자본인이름 추가
Q5. mylinux.txt 백업해서 ubuntu에 backup.txt로 
Q6. 상위로 이동 testdir 삭제

>> linux에서 한글화 설정
```
apt update
apt install locales
locale-gen ko_KR.UTF-8
update-locale LANG=ko_KR.UTF-8
```

# 사용자 정보 확인
whoami
id
who
users
groups

# 사용자 추가 및 삭제
sudo adduser sally
sudo passwd sally
sudo deluser sally

```
apt update
apt install adduser
adduser sally
```

# 권한 구조 및 변경
ls -l
sudo chown sally:sally hello.txt
chmod 755 hello.txt
umask

```
root@53f78cda83a8:~/home# chmod 755 /home/sally
root@53f78cda83a8:~/home# su - alpha
alpha@53f78cda83a8:~$ cd /home/sally
alpha@53f78cda83a8:/home/sally$ logout
root@53f78cda83a8:~/home# chomd 750 /home/sally
bash: chomd: command not found
```

## 6. 쉘스크립트
1. 프로세스 상태확인
```
ps -ef
```
-e : 모든 프로세스
-f : 출력정보 자세히

2. 실시간 모니터링
```
top
```

3. ip주소 확인
```
ifconfig
```
```
apt install net-tools
```

4. Hello world 쉘스크립트 작성
```
vi hello.sh

#!/bin/bash
echo "Hello world"
```
-rw-r--r-- 1 root   root     32 Feb  3 16:36 hello.sh

```
ls -al 권한보기
chomd +x hello.sh 권한주기
```
5. 쉘스크립트 실행
```
./hello.sh
```     