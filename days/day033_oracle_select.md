## Track001 -  github
 
# ■ 자바
# ■ 깃허브(협업)
# ■ oracle
# ■ 네트워크관리사 - 이론(2) / 실기

> 집에서 oracle 설치확인 스크린샷

1. --------------- 
# ■ 자바
# ■ Collection Framework

연습문제1)  Collection  Framework
패키지명 : com.company.java014_ex
클래스명 : SetEx001
1. UserInfo2    Dto 클래스만들기  - 속성 : private int no; private  String name; private  int age;
2. users   HashSet만들기
3. 다음의 데이터 넣기
   new UserInfo2(1, "아이언맨" , 50) , 
   new UserInfo2(2, "헐크" , 40) , 
   new UserInfo2(3, "캡틴" , 120), 
   new UserInfo2(3, "캡틴" , 120)
4. 향상된 for / Interator 이용해서 데이터 출력 (3명만 출력되게- 같은자료 중복안되게)
5. 사용자들의 이름 입력받기 - 이름을 입력받으면 해당하는  유저의 자료출력
6. 사용자들의 나이 평균처리



연습문제2)  Collection  Framework
패키지명 : com.company.java014_ex
클래스명 : MapEx001
1. MAP 만들기
KEY   VALUE
피구왕   통키
---------------------
제빵왕   김탁구
---------------------
요리왕   비룡

Map<String, String> map = new HashMap<>();

2 다음과 같이 문제풀기
==============================
KING   NAME
==============================
피구왕   통키
---------------------
제빵왕   김탁구
---------------------
요리왕   비룡
---------------------
KING의 정보를 제공중입니다
이름을 입력하세요> 제빵왕

ㅁ제빵왕 : 김탁구





2. ---------------
# ■ oracle
# 1. 복습문제
# 2. where    (2) - 집합연산
# 3. function (2) - String
  

> 복습문제
```
-- Ex001 union 과 union all 의 차이 -  중복없이 합집합, 중복있게 합집합

-- Ex002 EMP테이블에서 COMM 이 없고 , MGR은 존재하면 JOB 이 'MANAGER', 'CLERK' 인 사원 중 사원의 이름2번째 글자기 L 이 아닌 사원의 정보를 조회하시오
-- EMP테이블에서 
-- COMM 이 없고 , 
-- MGR은 존재하면 
-- JOB 이 'MANAGER', 'CLERK' 인 사원 중 
-- 사원의 이름2번째 글자가 L 이 아닌 사원의 정보를 조회하시오
select  *
from    emp
where   comm is null   
and     mgr is not null
and     job in('MANAGER', 'CLERK' )
and     ename not like '_L%';

--Ex003
-- EMP 테이블에서 UPPER를 이용하여 ENAME에 AM인 포함된 데이터를 조회하시오.
-- 대소문자 상관없이 AM인 사람을 조회하는 것이 가능해짐. 
select  ename from    emp   where   upper(ename) like upper('%AM%'); 

--EX004 해당하는 함수를 적으시오.
-- 1-1. (upper)  대문자, (lower) 소문자, (initcap) 대소문자 변환
-- 1-2. (length) 문자열길이
-- 1-3. (substr) 부분문자열 ,   (instr)  위치문자열
-- 1-4. (replace) 바꾸기 ,  (lpad), rpad 채우기
-- 1-5. (trim) , (ltrim), (rtrim)  공백빼기
-- 1-6. (concat) 문자열연결

