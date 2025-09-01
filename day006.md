## Track001 -  github
# ■ Github   
### 12. 깃허브 협업 
  
---
■ 1. 어필 본인 소개  
■ 2. 랜덤 팀 뽑기

ㅁ3. 프로젝트 들어가즈음 - 내팀장을찾아서 팀장뽑아주기 ... 다음번 프로젝트시
ㅁ4. 팀장 팀원 ... 다음번 프로젝트시
1. 팀 - 팀장
2. 팀 - 자리 ( 팀장님들 체크)
3. 팀 - 팀명  
4. 팀 - 우리팀에 관한 질문
> This Week~!
■ 1. 이력서사진  → ~이번주  > .png , .gif
■ 2. 팀뽑기


---
1. html + css + js/jquery 
2. spring + mybatis + jstl  + thymeleaf 
3. node + react
4. spring boot + jpa + mybatis + react
5. flutter + spring boot + jpa + mybatis + react


### [실습1] 정리

- [ ] 1.  vs code
- [ ] 2.  github 
- [ ] 3.  git  (서명바꾸기)
- [ ] 4.  자료옮기기 ( 본인주소 )

```bash
git clone https://github.com/사용자명/저장소명.git
git clone https://github.com/sally03915/fullstack_20250825.git
```


### [실습2] 친구저장소 (포트폴리오1) 가져오기
- [] 1. 친구저장소 fork 누르기
- [] 2. git clone  https://github.com/내아이디/복사된저장소.git
```bash
https://github.com/sally03915/fullstack_parkHJ.git
```
- [] 3. cd 복사된 저장소
- [] 4. 새작업공간만들기(branch) git checkout -b  dev-본인닉네임
- [] 5. dev-본인닉네임.md  파일에 내용추가
- [] 6. git add .  / git commit -m "인사말추가" / git push origin  dev-본인닉네임

> Q question 
1. [팀장] - portfolio 저장소   트러블슈팅 올리고 의견나눌 저장소( 정리 ) 
2. [팀원] - fork / git clone / 인사파일올리기


###  [실습3] 팀프로젝트
1. 팀장 - 저장소 / 친구들 초대 (Settings / Collaborators and teams ) - add
   sally03915@gmail.com  
2. 팀원 - 이메일( 초대잘받았다~ )  / 수락
3. 팀원 - 저장소복제  ( 저장소클록 / 폴더이름지정 )
         조장이 만든 상자를 내컴퓨터로 가져오기 - 폴더명은 teacher
```
git clone  https://github.com/sally03915/fullstack_20250825  teacher
cd  teacher
```
4. 팀원 - 팀플시작 (1)
```
cd  teacher 
git checkout -b   dev-소문자이름
dev-소문자이름.md      파일만들기 - teacher폴더안
git add dev-소문자이름.md
git commit -m "first dev-소문자이름"
git push origin  dev-소문자이름
```
5. 팀원 - 팀플시작 (2)
```
1. 팀장 깃허브주소
2. Compard & pull  request 클릭
3. 제목과 메시지 작성 - 작업마무리완료입니다. 제안
```
 



### [실습4] 팀프로젝트

3i1e         : https://github.com/kyoungsjjj0211/portfolio.git
드래곤치킨     : https://github.com/taehun00/dragonchicken.git
트리플윈      : https://github.com/suda77881/triple1

1조 : 유희재, 한승현, 김정민, 강서현
2조 : 성태훈, 길상현, 박현주, 임경민
3조 : 최상욱, 김영민, 신준용


> 각 조장님 포함 브랜치만들기
> 파일체크

> 다음파일  - 파일이름 myteam.md  [하나로 해결해서 합치기]

| 항목 | 질문 예시 | 활용 방향 |
|------|-----------|------------|
| 팀명  | 우리 팀 이름은 무엇이며 어떤 의미를 담고 있나요? | 브랜딩 요소 |
| 팀장  | 우리 팀의 리더는 누구이며 어떤 스타일인가요? | 리더십 유형 분석 |
| 팀원1 | 팀장님들의 자리는 어디에 있나요? (역할 중심) | 역할 분담 시각화 |
| 팀원2 | 팀장님들의 자리는 어디에 있나요? (역할 중심) | 역할 분담 시각화 |
| 팀원3 | 팀장님들의 자리는 어디에 있나요? (역할 중심) | 역할 분담 시각화 |
| 좋아하는 것 | 각 팀원이 좋아하는 분야나 기술은 무엇인가요? | 역량 매핑 |
| 팀규칙 | 카톡 읽씹하지 말기, 지각조퇴결석 연락주기| 성장 중심 팀 문화 강조 가능 |
| **함께 만든 것** | 우리가 함께 만들고 싶은 프로젝트는 무엇인가요? | 포트폴리오 콘텐츠 |



# ■ WebBasic
### 3. Tag  (연습문제 - a태그까지 정리)
1. 웹페이지의 구성요소를 알려주는 표시언어
2. h1~h6  제목
   - h1 : 로고, 상호명
   - h2 : 주메뉴
   - h3~h6 : 중요도 상세메뉴 (소제목)
3. p, pre : 문단
   - p : 일반문단
   - pre : 줄바꿈과 공백을 유지하는 서식텍스트
4. img  : 이미지
   - src : 경로  , alt : 대체용어 
5. a : 링크
   - href : 다른페이지나 파일로 연결 , target="_blank" 새창열기
6. 리스트태그 : ul , ol, dl
   - ul  : 순서없는리스트
   - ol  : 순서있는리스트
   - dl  : 설명리스트  
7. 그룹핑 : div

`web002_basic.html`      

### 4. CSS
1. 여백 - margin / padding
   1. margin : 바깥쪽여백
   2. padding : 안쪽여백

2. 글자스타일
   1.글자색상
      color:red
   2.글자크기
      font-size:16px
   3.정렬
      text-align:center /left /right/justify 문단의 정렬을 체크해주는 부분인데 left(왼쪽정렬) , right(오른쪽정렬) , center(중앙) , justify(수평정렬)
   4.밑줄
      text-decoration:none; 깃허브
   5. 글꼴
      font-family:sans-serif;
   6. 굵게
      font-weight:bold;

3. 배경꾸미기
      background-color: gold;
      background:black;
      background:linear-gradient(to right, pink, orange) background:linear-gradient(45deg, pink, orange)
      background: red url(./img/five.png) repeat center / 20px 20px
                  색상 경로 반복여부 중앙배치 가로사이즈 세로사이즈  

4. 박스디자인
   1. 가로 : width:300px
   2. 선 : border:3px solid red
   3. 둥근모서리 : borer-radius : 20px
   4. 그림자효과 : box-shadow: 0 4px 12px rgba(0,0,0,0.5)

> 1. CSS 연습문제  `web003_1_user.html`
> 2. MARKUP 연습문제  `web003_2_users.html`



# ■ Java
#### ■9. 자료형 (1)
1) 자료형의 구분
- 기본형 : 실제값 저장
- 참조형 : 주소값 저장 ( String ) 

2) 기본자료형 
- 논리형 - boolean  true/false
- 정수형 - byte(1byte) -short(2byte) - int(4byte) ★  - long(8byte)  
                                                       long l = 4L;
- 실수형 - float(4byte)      - double(8byte) ★
          float f= 3.14f;     double d = 3.14;

***★ 자바 기본처리단위 *** 
***★ 실수는 정수보다 크다(표현가능한 값의 범위와 타입의 복잡성) *** 
ㅁ long(8byte)           <   float(4byte) 소수점 포함한 실수표현
  큰물통(딱물정량-정수)         고압탱크(압축해서 엄청난 물 담기 - 실수+지수)
 


## 📚 day006복습  
Q1. 복습문제 - GITHUB
복습문제1) FORK 와 팀원을 초대했을때의 차이점은?

복습문제2) 팀원들을 EMAIL로 초대해서 작성시 다음부분을 코드 채우시오. 
1-1. 팀원 -  팀장이 만든 상자를 내컴퓨터로 가져오기 - 폴더명은  portfolio

1-2. 팀원 - 브랜치 만들어서 파일한개 올리기

1-3. 팀원 - pr하는방법은?
 

Q2. 복습문제 - WEB 
- 다음 TEST에 해당하는 CSS 코드를 작성하시오.
<div>TEST</div>
✅ <div> 카드
1) 배경색 흰색 →    #fff
2) 너비 250px
3) 페이지 가운데 정렬 →   0 auto 
4) 모서리 둥글게 →   20px
5) 그림자 효과 →  0 4px 12px rgba(0,0,0,0.1)
6) 텍스트 가운데 정렬 →   center
7) 안쪽 여백 20px


Q3. 복습문제 - JAVA
복습문제1) 
1) 자료형의 구분
- 기본형 : [ ① ] 저장
- 참조형 : [ ② ] 저장 ( String ) 

2) 기본자료형 
- 논리형 - [ ③ ]  true/false
- 정수형 - [ ④  ](1byte) -[ ⑤  ](2byte) - [  ⑥ ](4byte) ★  - [ ⑦  ](8byte)  
                                                       long l = 4L;
- 실수형 - float(4byte)      - double(8byte) ★
          float f= 3.14f;     double d = 3.14;
 

복습문제2)  다음에 해당하는 프로그리밍을 작성하시오.
패키지명 : com.company.java002_ex
클래스명 : DataTypeEx002
출력내용 :  Scanner이용해서 나이 입력받고 출력하시오.
    좋아하는 수(정수)   입력하시오 > _입력받기
    좋아하는 숫자는 ** 입니다.


## 📚 day006복습  - Answer

Q1. 복습문제 - GITHUB
복습문제1) FORK 와 팀원을 초대했을때의 차이점은?
```
FORK: 원본 저장소를 복사해서 내 계정으로 가져오는 방식. 독립적으로 작업 가능하며, 
원본 저장소에는 직접 수정 권한이 없음.

팀원 초대: 팀장이 저장소에 직접 초대하면, 해당 저장소에 직접 커밋, 브랜치 생성, PR 등을 할 수 있는 권한을 부여받음.
```

복습문제2) 팀원들을 EMAIL로 초대해서 작성시 다음부분을 코드 채우시오. 
1-1. 팀원 -  팀장이 만든 상자를 내컴퓨터로 가져오기 - 폴더명은  portfolio
```
git clone  https://github.com/sally03915/fullstack_20250825  portfolio 
```
1-2. 팀원 - 브랜치 만들어서 파일한개 올리기
```
cd  portfolio 
git checkout -b   dev-소문자이름
dev-소문자이름.md      파일만들기 - teacher폴더안
git add dev-소문자이름.md
git commit -m "first dev-소문자이름"
git push origin  dev-소문자이름
```
1-3. 팀원 - pr하는방법은?
```
1. 팀장 깃허브주소
2. Compard & pull  request 클릭
3. 제목과 메시지 작성 - 작업마무리완료입니다. 제안
```
 

Q2. 복습문제 - WEB 
- 다음 TEST에 해당하는 CSS 코드를 작성하시오.
<div>TEST</div>
✅ <div> 카드
1) 배경색 흰색 →    #fff
2) 너비 250px
3) 페이지 가운데 정렬 →   0 auto 
4) 모서리 둥글게 →   20px
5) 그림자 효과 →  0 4px 12px rgba(0,0,0,0.1)
6) 텍스트 가운데 정렬 →   center
7) 안쪽 여백 20px

<div style="
  background-color: #fff;
  width: 250px;
  margin: 0 auto;
  margin-bottom: 20px;
  border-radius: 20px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  text-align: center;
  padding: 20px;
">
  TEST
</div>


Q3. 복습문제 - JAVA
자료형의 구분
기본형 : [ ① 값 ] 저장
참조형 : [ ② 주소 ] 저장 (예: String)

기본자료형
논리형 - [ ③ boolean ]
정수형
[ ④ byte ] (1byte)
[ ⑤ short ] (2byte)
[ ⑥ int ] (4byte) ★
[ ⑦ long ] (8byte)
```


복습문제2)  다음에 해당하는 프로그리밍을 작성하시오.
패키지명 : com.company.java002_ex
클래스명 : DataTypeEx002
출력내용 :  Scanner이용해서 나이 입력받고 출력하시오.
    좋아하는 수(정수)   입력하시오 > _입력받기
    좋아하는 숫자는 ** 입니다.

```java
package com.company.java002_ex;
import java.util.Scanner;

public class DataTypeEx002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("나이를 입력하시오 > ");
        int age = scanner.nextInt();
 
        System.out.println("내 나이는 " + age + " 입니다.");
    }
}
```
