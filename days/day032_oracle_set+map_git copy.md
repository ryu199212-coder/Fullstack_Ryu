## Track001 -  github
 
# ■ ORACLE
# ■ 001. Setting

1. sqld 접수  (~09:40)
   https://www.dataq.or.kr/www/main.do
2. select basic
3. select where



# ■ JAVA
# ■ Java Collection Framework
- List
■ Set
■ Map
  
# ■ Github   
### 12. 깃허브 협업 
..............................
##### ■1. basic
```
#1. 가방에 담기  ( 불꽃마법재료, 불씨,,,)
git  add .

#2. 가방에 메시지남기기 (불꽃마법 완성)
git commit -m "메시지"

#3. [공용-github - 마법책] 같은 마법책을 쓰니깐 서로 바뀐거 확인
git pull origin master

#4. [공용-github - 마법책] 에 불꽃마법 올리기 (공유)
git push origin master

```


##### ■2. 깃협업
1. 팀원초대
2. 협업 중 같은 파일ㅇ르 수정하면서 생기는 충돌(conflict) 해결

```
#1. 팀장 - 팀원초대 (github 웹)
#2. 팀원 - repository 클론
  git clone 깃허브주소

#3. 팀원 - branch
  git checkout -b branch명
#4. 팀원 - 파일작성
 git add .
 git commit -m "message"
 git pull   origin  master 
 git push   origin  master

 ※ 충돌 - HEAD , ====== , >>>>>> / 코드수정

#5. 팀원 - pr 
```

> **Question**
```
Q1. 팀장 - test2.md 파일만들기 > 안녕하세요 포젝트입니다.
Q2. 팀원 - test2.md          > 안녕하세요 팀원 **입니다.
      충돌시- 충돌해결 후 코드 
      안녕하세요 포젝트입니다.
      팀장 : 세상에서 젤로 멋찐 홍길동입니다.
      팀원 : 세상에서 젤로 귀여운 가길동입니다.
```


##### ■3. merge  vs  rebase
1. 기능 브랜치에서 작업 중  main브랜치가 업데이트가 된 경우
> 여러 마법사가 함께 주문서를 쓰고있을때 
- 내가 주문을 다시쓰면 친구들의 주문이 사라 질수 있음.

★  브랜치 꼭확인!
```
#1. 불꽃마법사가 본인코드 작성중  (dev-f)
   git add spell2.md
   git commit -m "불꽃마법2 추가"

#2. 메인 마법서가 업데이트가 됨.
    불꽃마법사가 최신 마법서 위에 자신의 주문을 다시 써야 함.

    git checkout master
    git pull origin master

    # 최신 마법서 위에 주문 다시 쓰기 
    git checkout dev-f
    git rebase master   
    # > master 브랜치기준으로 내작업을 다시 정렬
    # > 주문이 겹쳐서 마법서 충돌남. 어떤 주문 쓸지 선택 / 수정완료

    git add  spell2.md
    git rebase  --continue

    # 안전하게 주문서 공유
    git push  --force-with-lease
```

> **Question1**
```
1.  test2.md 파일에 테이블옆에 본인이모지  🔥 붙이기
2.  rebase 해서 파일 다시 올리기
```

> **Question2**
```
1.  팀장님      - test2.md  ( 깃허브에서 응원의 메시지 남겨주기)
2.  팀원        - test2.md 파일에 본인이 다른친구들한테 또는 본인한테 남기는 응원의 메시지적기 
3.  팀장 + 팀원  - rebase 해서 파일 다시 올리기
```


..............................





# ■ 자기소개페이지 
# ■ 포트폴리오 점검- day31완료!
---
1. html + css + js/jquery      [개인]
2. spring + mybatis + jstl     
3. spring boot + thymeleaf + jpa + mybatis + react  
4. node + react
5. flutter + spring boot + jpa + mybatis + react


> 복습문제
```sql
-- Ex1  emp 테이블에서 job 데이터 중복없이 조회
select distinct job from emp;

-- Ex2
--조회할 테이블은 EMP 테이블이며 모든 열을 출력하시오.
--별칭 (AS)
--EMPNO → EMPLOYEE_NO,
--ENAME → EMPLOYEE_NAME,
--MGR → MANAGER,
--SAL → SALARY,
--COMM → COMMISSION,
--DEPTNO → DEPARTMENT_NO

--부서번호를 기준으로 내림차순으로 정렬하되,
--부서번호가 같다면 사원이름을 기준으로 오름차순 정렬하시오.

select  EMPNO AS  EMPLOYEE_NO 
      , ENAME AS  EMPLOYEE_NAME
      , JOB 
      , MGR    MANAGER
      , SAL    SALARY
      , COMM   COMMISSION
      , DEPTNO  DEPARTMENT_NO
from  emp
order by DEPTNO DESC , ENAME ASC;


-- Ex3  
-- Q3. EMP테이블에서 대소비교연산자(<= , >= ) and 를 이용하여 sal 열이 2000이상 3000이하인인 행을 조회
select * from emp  where sal >= 2000 and  sal<=3000;

-- Ex3  
-- Q4. EMP테이블에서 BETWEEN AND 를 이용하여 sal 열이 2000이상 3000이하인인 행을 조회
select * from emp  where sal between 2000 and 3000;

-- Ex4
 EMP테이블에서 OR 를 이용하여 
-- JOB 열이 'MANAGER' ,'SALESMAN' , 'CLERK' 중 하나라도 포함되는 행을 조회
select * from emp  where job='MANAGER' or job='SALESMAN' or job= 'CLERK';

-- Ex5
EMP테이블에서 IN 를 이용하여 
-- JOB 열이 'MANAGER' ,'SALESMAN' , 'CLERK' 중 하나라도 포함되는 행을 조회
-- from → where  → select 
select * from emp  where job in ('MANAGER' ,'SALESMAN' , 'CLERK');


-- Ex6. EMP테이블에서 ENAME이 S로 시작하는 행
select * from emp  where ename like 'S%';


-- Ex7. EMP테이블에서 ENAME의 두번째 글자가 L인 행을 조회
select * from emp  where ename like '_L%';


-- Ex8. EMP테이블에서 ENAME에 AN이 포함되어 있는 행을 조회
select * from emp  where ename like '%AN%';

```