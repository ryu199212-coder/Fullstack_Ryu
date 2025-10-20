## Track001 -  github
 
# ■ 자바
# ■ 깃허브(협업)
# ■ oracle
# ■ 네트워크관리사 - 이론(2) / 실기

> 집에서 oracle 설치확인 스크린샷

1. --------------- 
# ■ 자바
# ■ Lambda (1)




2. ---------------
# ■ 자바
# ■ 깃허브(협업)

# 1. fetch

##### ■4. fetch  vs  pull
- fetch : 데이터 가져오기 ( 충돌이 날지 먼저 확인 ) 
- pull  : 데이터 가져오고 바로 합치기 (위험의 가능성)

```bash
#1. [ github ]  test.md  - 새로운 사항 업데이트
#2. [local] 작업 - 새로운 변경사항있는지 확인
   git fetch  origin
#3. 가져온 변경사항 확인
   git log     a1dc3bc..36ff096
   git log     HEAD..orgin/master
   git diff    HEAD  origin/master  -- 변경된 내용파일
   git diff -- HEAD  origin/master 
#4. 확인후 합치기 
   git merge orgin/master   

Q1.  git pull 이 아니라 get fetch 데이터 가져오기  
1-1. 팀장 - [깃허브] test2.md  
    ★ 자격증 공부
    - day001 이론완료 : 이름붙여주기
1-2. 팀원 - [깃허브] test2.md 수정 
    -  git pull 이 아니라 get fetch 데이터 가져오기  
```



##### ■4. restore  vs  checkout
- restore  : 파일을 예전 상태로 되돌리는 기능  (최근상태로 되돌리기) , commit 안했을때사용가능
- checkout : 브랜치 바꾸거나 파일 되돌리기

``` bash
git restore  파일명   # 마지막으로 저장한상태로 돌아감.
git restore   --source=HEAD~1  파일명   # HEAD~1  1단계  , HEAD~2  2단계  커밋상태로 돌아감.
```

Q1. day034.md - restore 2번 commit 하고 1번째 시점으로 돌아가기
    first      - commit
    second     - commit


---------------
# ■ oracle
# 1. 복습문제
# 2. function

---------------
# ■ 네트워크관리사 -구성 이론(2) / 실기(2회분씩)
# 1. 이론



   

> 복습문제 
/////////////////////////////////////////////////////////////////////////////// JAVA

Ex1)  다음 인터페이스를 확인하고 두가지의 방법으로 구현하시오
```
iinterface InterC2{  String hi(); }
```
1) 익명객체
2) 람다식

```answer
//3-1. 익명객체 Good :Day출력
InterC2 c = new InterC2() {
	@Override public String hi() { return "Good :Day"; } 
};  
System.out.println( c.hi() );

//3-2. 람다식  ()->{}
InterC2 c2 = ()->{ return "G:ood Day"; };
System.out.println(c2.hi());

InterC2 c3 = ()->  "G:ood :Day";  
System.out.println(c3.hi());
```


Ex2)  다음 인터페이스를 확인하고 다음과 같이 나오게 람다식을 구현하시오
interface  Ex2{  void print(String s);     }  // (String s)-> x

```answer
  Ex2 ex2     = System.out::println;  
  ex2.print("lambda:)");
```

/////////////////////////////////////////////////////////////////////////////// GIT

# 1. fetch  와  full의 차이점은?   
- fetch : 데이터 가져오기 ( 충돌이 날지 먼저 확인 ) 
- pull  : 데이터 가져오고 바로 합치기 (위험의 가능성)

#2. 다음 빈칸에 코드를 채우시오.
```bash
#1. [ github ]  test.md  - 새로운 사항 업데이트
#2. [local] 작업 - 새로운 변경사항있는지 확인
      ★ 빈칸채우기   git fetch  origin
#3. 가져온 변경사항 확인
  ★ 빈칸채우기  (다음중에 하나)
   git log     a1dc3bc..36ff096
   git log     HEAD..orgin/master
   git diff    HEAD  origin/master  -- 변경된 내용파일
   git diff -- HEAD  origin/master 

#4. 확인후 합치기 
   ★ 빈칸채우기 	
   git merge orgin/master   
```

#5.  폴더의 시점이 아니라  파일을 예전 상태로 되돌리는 기능을  restore라고 하는데.   [  ★  commit  ] 안했을때사용가능

#6. 커밋 2단계이전으로 돌아가려면? 
``` bash
git restore   --source=HEAD~2  파일명   
```
/////////////////////////////////////////////////////////////////////////////// SELECT


-- QEx1  EMP테이블에서 NVL 함수를 사용하여 empno와  연봉은 ( sal*12 + comm) 한 값이 null 이 안나오게 이 다 나오게 처리하시오.
select empno,   sal + nvl(comm, 0)
from   emp;

-- QEx2  EMP테이블에서 NVL2 함수를 사용하여 empno와  연봉은 ( sal*12 + comm) 한 값이 null 이 안나오게 이 다 나오게 처리하시오.
select empno,  sal*12 + nvl2( comm , comm , 0)
from   emp;

-- QEx3  EMP테이블에서 DECODE 함수를 사용하여  empno, 인상된급여를   출력하시오.  (decode)
--JOB이 MANAGER 는 급여의 10% 인상한 급여       SAL*1.1
--SALESMAN 는 급여의 5% 인상한 급여             SAL*0.5
--ANALYST 는 그대로                            SAL 
--나머지는 3% 인상된 급여         SAL*0.3
select  empno,  
        decode(  job 
            , 'MANAGER'  ,  SAL*1.1    -- when   then
            , 'SALESMAN' ,  SAL*1.05   -- when   then
            , 'ANALYST'  ,  SAL
            , SAL*1.03  -- else
        ) upsal
from    emp;

-- QEx4
-- EMP테이블에서 CASE 함수를 사용하여 위의 문제를 출력하시오.  (case when  then   else  end)
select  empno, 
        case  job
            when  'MANAGER'   then SAL*1.1 
            when  'SALESMAN'  then SAL*1.05 
            when  'ANALYST'   then SAL
            else  SAL*1.03  
        end   upsal
from    emp;

