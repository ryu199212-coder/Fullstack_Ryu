## Track001 -  github


# ■ Github   
### 12. 깃허브 협업 
 
---
1조 : 유희재, 한승현, 김정민, 강서현
2조 : 성태훈, 길상현, 박현주, 임경민
3조 : 최상욱, 김영민, 신준용
3i1e         : https://github.com/kyoungsjjj0211/portfolio.git
드래곤치킨     : https://github.com/taehun00/dragonchicken.git
트리플윈      : https://github.com/suda77881/triple1


# ■ WebBasic
## 6 배치요소
1. block(그릇) vs  inline(콘텐츠:간장)
  1) box model 
     - 콘텐츠가 자리하는 영역
  2) block 
     - width/hegiht O , 앞뒤줄바꿈 O , div,h3,p
  3) inline
     - width/height X , 앞뒤줄바꿈 X , a , span, strong       

2. 배치(1) - float
   - 좌우배치 (왼쪽 또는 오른쪽 배치)
   - clear:both 끊기 : 다음요소의 흐름 정리

3. 배치(2) - position
   - 절대/고정위치 
   - 부모 relative 위치기준 / 자식 absolute 이동
   - fixed 브라우저 기준

3. 배치(3) - display
   - 요소의 속성바꾸기

`web005_1.html`


# ■ Java

■ 복습문제
복습문제1) 
1. 자바의 자료형은 ( 기본형   / 참종형 )
2. 자바 기본형의 분류는 - boolean ,  ( 정수형 )  , ( 실수형 )
3. 정수형의 종류는( byte  ,  short  ,  int ★    , long  )    
4. 실수형의 종류는( float  ,  double ★  )    


복습문제2)  다음코드에 문제가 있다. 이유를 적고 해결하시오.
short sh1 = 1;
short sh2 = 2;
short result = sh1 + sh2;  
> int 보다 작은 정수형  byte, short,  char는 연산시 int로 처리
short result = (short)(sh1 + sh2);  


복습문제3)  eclipse
패키지명 : com.company.java003_ex
클래스명 : Repeat009
출력내용 :  Scanner이용해서 나누기 프로그램만들기 
   숫자입력1>  _입력받기  10   ( ☆자료형을 int )
   숫자입력2>  _입력받기  3     ( ☆자료형을 int )              
   
   10 / 3 = 3.33



■11. 연산자
1.  연산?
1)  데이터를 처리해서 결과값을 산출하는 과정

2.  종류
먼저  값을구하고                   비교                         조건처리후        대입
( )   ++,--,  +,-,*,/,%(나머지)          <, >, <=, >=, == , !=     && ,||, ?:         = ,+=


연습문제1)
클래스명 : OperatorEx001
    int a=3, b=10;
    System.out.println(  b+=10 - a-- );   
    System.out.println(  a+=5 );
    System.out.println(  a>=10 || a<0 && a>3);

연습문제2)  다음에 해당하는 클래스의 조건을 출력하시오.
클래스명 : OperatorEx002
q1-1 int형 변수 x가 3보다 크고 10보다 작을때 true인 조건식 
q1-2 char형 변수 ch가 'a' 또는 'A'일때   true인 조건식    
q1-3 char형 변수 ch가 숫자('0'~'9')일때   true인 조건식     
q1-4 char형 변수 ch가 영문자(대문자 또는 소문자) 일때   true인 조건식



■1. Web  Basic  복습문제
■1. Web  Basic  복습문제
■1. Web  Basic  복습문제
■1. Web  Basic  복습문제

### 1. block vs inline

1) box model  
- 콘텐츠가 자리하는 영역을 의미하며, 박스의 구성 요소는 ______________, ______________, ______________, ______________로 이루어진다.

2) block 요소  
- width/height 설정이 (O/X): __________  
- 앞뒤 줄바꿈이 (O/X): __________  
- 대표 태그: __________, __________, __________

3) inline 요소  
- width/height 설정이 (O/X): __________  
- 앞뒤 줄바꿈이 (O/X): __________  
- 대표 태그: __________, __________, __________


### 2. 배치(1) - float

- 요소를 좌우로 배치할 때 사용하는 속성은 __________이다.  
- 다음 요소의 흐름을 정리하려면 __________ 속성을 사용한다.  
- float를 끊는 대표적인 방법은 클래스명으로 __________를 사용한다.


### 3. 배치(2) - position

- 자식 요소를 부모 기준으로 위치시키려면 부모에 __________, 자식에 __________를 설정한다.  
- 브라우저 전체 기준으로 고정하려면 __________ 속성을 사용한다.  
- position의 주요 값 4가지는 __________, __________, __________, __________이다.


### 4. 배치(3) - display

- 요소의 기본 속성을 바꾸려면 __________ 속성을 사용한다.  
- block 요소를 inline처럼 보이게 하려면 __________,  
  inline 요소를 block처럼 보이게 하려면 __________ 값을 사용한다.


 

■1. Web  Basic  복습문제 - Answer
■1. Web  Basic  복습문제 - Answer
■1. Web  Basic  복습문제 - Answer
■1. Web  Basic  복습문제 - Answer

### 1. block vs inline

1) box model  
- 콘텐츠가 자리하는 영역을 의미하며, 박스의 구성 요소는 __content, padding, border, margin__ 로 이루어진다.

2) block 요소  
- width/height 설정이 (O/X): __O__  
- 앞뒤 줄바꿈이 (O/X): __O__  
- 대표 태그: __div, h3, p__

3) inline 요소  
- width/height 설정이 (O/X): __X__  
- 앞뒤 줄바꿈이 (O/X): __X__  
- 대표 태그: __a, span, strong__
 

### 2. 배치(1) - float

- 요소를 좌우로 배치할 때 사용하는 속성은 __float__ 이다.  
- 다음 요소의 흐름을 정리하려면 __clear__ 속성을 사용한다.  
- float를 끊는 대표적인 방법은 클래스명으로 __clear__ 를 사용한다.

 

### 3. 배치(2) - position

- 자식 요소를 부모 기준으로 위치시키려면 부모에 __relative__, 자식에 __absolute__ 를 설정한다.  
- 브라우저 전체 기준으로 고정하려면 __fixed__ 속성을 사용한다.  
- position의 주요 값 4가지는 __static, relative, absolute, fixed__ 이다.

 

### 4. 배치(3) - display

- 요소의 기본 속성을 바꾸려면 __display__ 속성을 사용한다.  
- block 요소를 inline처럼 보이게 하려면 __inline__,  
  inline 요소를 block처럼 보이게 하려면 __block__ 값을 사용한다.
 



■1. Java  복습문제
■1. Java  복습문제
■1. Java  복습문제

복습문제1) 
1. 자바의 자료형은 (     /   )
2. 자바 기본형의 분류는 - boolean ,  (   )  , (   )
3. 정수형의 종류는(    ,     ,    ★    ,    )    
4. 실수형의 종류는(    ,    ★  )    


복습문제2)   다음에 해당하는 클래스의 조건을 출력하시오.
클래스명 : OperatorEx002
q1-1 int형 변수 x가 3보다 크고 10보다 작을때 true인 조건식 
q1-2 char형 변수 ch가 'a' 또는 'A'일때   true인 조건식    
q1-3 char형 변수 ch가 숫자('0'~'9')일때   true인 조건식     
q1-4 char형 변수 ch가 영문자(대문자 또는 소문자) 일때   true인 조건식 


복습문제3) eclipse
클래스명 :  Repeat010
출력내용 : 숫자한개를 입력받아 
   만약 1을 입력했다면   one ,   
   만약 2을 입력했다면   two ,
   만약 3을 입력했다면   three ,
   1,2,3이 아니라면  1,2,3이 아니다를 출력하는 프로그램을 작성하시오.




■1. Java  복습문제 - Answewr
■1. Java  복습문제 - Answewr
■1. Java  복습문제 - Answewr

## 복습문제1: 자료형

1. 자바의 자료형은 ( 기본형 / 참조형 )  
2. 자바 기본형의 분류는 - boolean , ( 숫자형 ) , ( 문자형 )  
3. 정수형의 종류는 ( byte , short , ★int , long )  
4. 실수형의 종류는 ( float , ★double )

 

## 복습문제2: 조건식 

// q1-1 int형 변수 x가 3보다 크고 10보다 작을 때
if (x > 3 && x < 10)

// q1-2 char형 변수 ch가 'a' 또는 'A'일 때
if (ch == 'a' || ch == 'A')

// q1-3 char형 변수 ch가 숫자('0'~'9')일 때
if (ch >= '0' && ch <= '9')

// q1-4 char형 변수 ch가 영문자(대문자 또는 소문자)일 때
if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
 
## 복습문제3 : if 
 
```java
import java.util.Scanner;

public class Repeat010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("숫자 한 개를 입력하세요: ");
        int num = sc.nextInt();

        if (num == 1) {
            System.out.println("one");
        } else if (num == 2) {
            System.out.println("two");
        } else if (num == 3) {
            System.out.println("three");
        } else {
            System.out.println("1,2,3이 아니다");
        }
    }
}
```
 