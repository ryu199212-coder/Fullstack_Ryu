-- 1. 문자열
-- 1-1. upper 대문자, lower 소문자, initcap 대소문자 변환
-- 1-2. length 문자열 길이
-- 1-3. substr 부분 문자열, instr 위치 문자열
-- 1-4. relplace 변경, lpad, rpad 채우기
-- 1-5. trim 양쪽공백 제거, ltrim 왼쪽 제거, rtirm 오른쪽 제거
-- 1-6. concat 문자열 연결

-- 2. 숫자
-- 3. 날짜
-- 4. 변환
-- 5. 조건

--------------------------------------------------------------------------------
-- 1. 문자열
-- 1-1. upper 대문자, lower 소문자, initcap 대소문자 변환
-- 1-2. length 문자열 길이
-- 1-3. substr 부분 문자열, instr 위치 문자열
-- 1-4. relplace 변경, lpad, rpad 채우기
-- 1-5. trim 양쪽공백 제거, ltrim 왼쪽 제거, rtirm 오른쪽 제거
-- 1-6. concat 문자열 연결

-- #1. 대소문자
select ename, upper(ename), lower(ename), initcap(ename)
from emp;

-- #2. 문자열 길이
select ename, length(ename), '킹', length('킹'), lengthb('킹')
from emp;

-- #3. 부분 문자열(substr) : (문자, 시작점, 몇개), 위치 문자열(instr)
select ename, substr(ename, 1, 2), substr(ename, 1, 3), substr(ename, 2, 2), substr(ename, 3, 2) from emp;

select instr('oracle', 'a') from dual;

select ename, instr(ename, 'A') from emp; -- 있으면 위치, 없으면 0

-- #4. relplace 문자열 찾아서 변경, lpad, rpad 채우기
select replace('010-1234-5678', '-', ' ') from dual;
select lpad('oracle', 10, '#') from dual;
select rpad('oracle', 10, '#') from dual;

-- #5. trim 양쪽공백 제거, ltrim 왼쪽 제거, rtirm 오른쪽 제거
select trim(' *oracle* '), ltrim(' *oracle* '), rtrim(' *oracle* ') 
from dual;

-- 1-6. concat 문자열 연결
select concat('Hello ', 'oracle') from dual;
select concat(concat('Hello ', 'oracle'), '★') from dual;

select 'Hello ' || 'oracle ' || '♥' from dual;

-- ##1. 문자열 연습문제
-- Q01 EMP 테이블에서 ENAME을 대문자, 소문자, 첫글자만 대문자로 조회하시오.
select ename, upper(ename), lower(ename), initcap(ename) from emp;

-- Q02 EMP 테이블에서 UPPER를 이용하여 ENAME이 KING인 데이터를 조회하시오.
select ename, upper('KING') from emp;

-- Q03 EMP 테이블에서 UPPER를 이용하여 ENAME에 KING인 포함된 데이터를 조회하시오.
select ename from emp where upper(ename) like '%KING%';

-- Q04 EMP 테이블에서 LENGTH를 이용하여 ENAME의 문자열 길이를 조회하시오.
select ename, length(ename) from emp;

-- Q05 EMP 테이블에서 ENAME의 문자열 길이가 5이상인 데이터를 조회하시오.
select ename from emp where length(ename) >= 5;

-- Q06 LENGTH('한글'), LENGTHB('한글')
--     문자열길이반환, 문자열 바이트 수 반환환
select length('한글'), lengthb('한글') from dual;

-- Q07 문자열 일부분을 추출
--     SUBSTR( 문자열 , 시작위치, 추출길이)
select job, substr(JOB, 1, 2), substr(JOB, 3, 2), substr(JOB, 5)
from emp;

-- Q08 -의 의미는?
--     C(-5)L(-4)E(-3)R(-2)K(-1)
-- 뒤에서부터 표기
select job, substr(job, -length(job)), substr(job, -length(job), 2), substr(job, -length(job), -3)
from emp;

-- Q09 특정문자위치 찾기
--     INSTR(문자열, 찾을거, 시작위치, 몇번째째)
--    'HELLO, ORACLE!' 문자열에서 다음과 같이 찾으시오.
select instr('HELLO, ORACLE!' , 'L') as INSTR_1, 
instr('HELLO, ORACLE!' , 'L', 5) as INSTR_2,
instr('HELLO, ORACLE!' , 'L', 2, 2) as INSTR_3 from dual;

-- Q10 EMP테이블에서 INSTR 함수로 사원이름에 S가 있는 데이터를 조회하시오.
select * from emp where instr(ename, 'S') > 0;

-- Q11 EMP테이블에서 LIKE를 이용하여 사원이름에 S가 있는 데이터를 조회하시오.
select * from emp where ename like '%S%';

-- Q12 REPLACE를 이용하여 연락처의 -을 공백으로, -을 뺀데이터로 조회하시오
select replace('010-1234-5678', '-', '-') as REPLACE_BEFORE,
replace('010-1234-5678', '-', ' ') as REPLACE_1,
replace('010-1234-5678', '-') as REPLACE_2
from dual;

-- Q13 LPAD, RPAD를 이용하여 다음과 같이 데이터를 출력하시오
select 'Oracle',
lpad('Oracle', 10, '#') as LPAD_1,
rpad('Oracle', 10, '*') as RPAD_1,
lpad('Oracle', 10, ' ') as LPAD_2,
rpad('Oracle', 10, ' ') as RPAD_2
from dual;

-- Q14 RPAD를 이용하여 개인정보뒷자리 *로 출력하시오.
select 
rpad('911225-', 14, '*') as RPAD_JMNO,
rpad('010-1234-', 13, '*') as RPAD_PHONE
from dual;

-- Q15 EMP 테이블에서 EMPNO와 ENAME 사이에 :을 넣고 문자열을 연결하시오.
select concat(empno,ename),
concat(empno, concat(':',ename))
from emp
where ename = upper('SCOTT');

-- Q16 TRIM을 이용하여 다음과 같이 공백을 제거하고 출력하시오.
select 
    '[' || trim(' _ _oracle_ _ ') || ']'as trim
,   '[' || ltrim(' _ _oracle_ _ ') || ']'as trim
,   '[' || rtrim(' _ _oracle_ _ ') || ']'as trim
,   '[' || trim(' _ _oracle_ _ ') || ']'as trim 
from dual;

select 
    '[' || trim(' _ _oracle_ _ ') || ']'as trim
,   '[' || trim(leading from' _ _oracle_ _ ') || ']'as trim -- 앞쪽 공백 제거
,   '[' || trim(trailing from' _ _oracle_ _ ') || ']'as trim
,   '[' || trim(both from' _ _oracle_ _ ') || ']'as trim 
from dual;

-- Q17 TRIM을 이용하여 삭제할 문자 삭제후 출력가능
select 
    '[' || trim(both '_' from'_ _oracle_ _') || ']'as trim
,   '[' || trim(leading '_' from '_ _oracle_ _') || ']'as trim -- 앞쪽 공백 제거
,   '[' || trim(trailing '_' from'_ _oracle_ _') || ']'as trim
,   '[' || trim(both '_' from'_ _oracle_ _') || ']'as trim 
from dual;

-- Q18 TRIM, LTRIM, RTRIM 사용하여 문자열 출력하기기
select 
    '[' || trim('_oracle_') || ']'as trim
,   '[' || ltrim('_oracle_ ') || ']'as Ltrim
,   '[' || ltrim('_oracle_>', '_') || ']'as Ltrim
,   '[' || rtrim(' _oracle_') || ']'as Rtrim
,   '[' || rtrim('<_oracle') || ']'as Rtrim 
from dual;
