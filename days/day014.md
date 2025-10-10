## Track001 -  github

> 1. (~18:00) 깃허브정리 day013.md
> 2. BANK 상담정리

1. 팀장뽑기 (아~ 이팀장이면 믿을만하다~~! )
2. 팀장이  2,3,4,5차까지 만들 포트폴리오 주제 안내
3. 팀원들은 가고 싶은팀 익명으로 투표
4. 팀장은 팀원뽑기 (익명)
5. 다시 고르기

# ■ Github   
### 12. 깃허브 협업 
---
1조 : 유희재, 한승현, 김정민, 강서현
2조 : 성태훈, 길상현, 박현주 
3조 : 최상욱, 김영민, 신준용
3i1e         : https://github.com/kyoungsjjj0211/portfolio.git
드래곤치킨     : https://github.com/taehun00/dragonchicken.git
트리플윈      : https://github.com/suda77881/triple1

# ■ Java
■ 복습문제
■1. Java  복습문제
■1. Java  복습문제
■1. Java  복습문제
>>>>>>>>>>>>>>>> 
~09:40
>>>> java
[필기]다음 문제를 적으시오.
Q1.  숫자를 입력받으려고 한다. 다음에 해당하는 코드를 적으시오. 
숫자 입력> __입력받기
```
//변수 선언
Scanner scanner = new Scanner(System.in);
int num=0;   //① 해당변수
//입력받기
System.out.println("숫자입력 > ");      // ② 안내문구 출력
num = scanner.nextInt();      // ③ 입력받기
```

Q2.  문자를 입력받으려고 한다. 다음에 해당하는 코드를 적으시오. 
문자 입력> __입력받기
```
//변수 선언
Scanner scanner = new Scanner(System.in);
char ch=' ' ;   //④ 해당변수
//입력받기
System.out.println("문자입력 > ");   // ⑤ 안내문구 출력
ch = scanner.next().charAt(0);   // ⑥ 입력받기
```

Q3.  해당하는 for문의 코드를 적으시오.
1 2 3 4 5 6 7 8 9 10
```
for(int i=1; i<=10; i++){ System.out.print(i ); }  
```



[실습] 다음문제를 풀으시오
■실습1 -  if문 버전 문제
[eclipse]
사용자로부터 문자를 입력받아 다음과 같이 출력하세요.
x를 입력받으면 "x-ray" 출력
y를 입력받으면 "yogurt" 출력
z를 입력받으면 "zebra" 출력
그 외에는 "x, y, z가 아닙니다" 출력

```
import java.util.Scanner;

public class Repeat014_1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자를 입력하세요 (x, y, z): ");
        char ch = sc.next().charAt(0);

        if (ch == 'x') {
            System.out.println("x-ray");
        } else if (ch == 'y') {
            System.out.println("yogurt");
        } else if (ch == 'z') {
            System.out.println("zebra");
        } else {
            System.out.println("x, y, z가 아닙니다");
        }
 
    }
}

```

■실습2 - switch문 버전 문제
[eclipse]
사용자로부터 문자를 입력받아 다음과 같이 출력하세요.
x를 입력받으면 "x-ray" 출력
y를 입력받으면 "yogurt" 출력
z를 입력받으면 "zebra" 출력
그 외에는 "x, y, z가 아닙니다" 출력

```
import java.util.Scanner;

public class Repeat014_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자를 입력하세요 (x, y, z): ");
        char ch = sc.next().charAt(0);

        switch (ch) {
            case 'x':
                System.out.println("x-ray");
                break;
            case 'y':
                System.out.println("yogurt");
                break;
            case 'z':
                System.out.println("zebra");
                break;
            default:
                System.out.println("x, y, z가 아닙니다");
        }
 
    }
}

```

■실습3 -  무한반복 버전 문제
[eclipse]
사용자에게 숫자를 계속 입력받으세요.
숫자 7을 입력할 때까지 무한 반복하며 입력을 받습니다.
7을 입력하면 "종료합니다!"를 출력하고 반복을 종료하세요.

```
import java.util.Scanner;

public class Repeat014_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;

        for(;;) {
            System.out.print("숫자를 입력하세요 (7을 입력하면 종료): ");
            num = sc.nextInt();

            if (num == 7) {
                System.out.println("종료합니다!");
                break;
            }
        }
 
    }
}

```
■실습4  
for , while , do while문을 이용해서 다음과 같이 출력하시오 :  1 2 3 4 5
```
public class Repeat014_3 {
    public static void main(String[] args) {

        // for문 출력
        System.out.println("▶ for문 출력:");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println(); // 줄바꿈

        // while문 출력
        System.out.println("▶ while문 출력:");
        int j = 1;
        while (j <= 5) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println(); // 줄바꿈

        // do while문 출력
        System.out.println("▶ do while문 출력:");
        int k = 1;
        do {
            System.out.print(k + " ");
            k++;
        } while (k <= 5);
        System.out.println(); // 줄바꿈
    }
}


```





■14. array 배열
1. 배열?
- [같은 타입]의 데이터를 [연속된 공간에 저장]하는 자료구조
- 각 데이터의 저장위치 [인덱스]를 통해서 접근

int  a     = 10;  // 비교

int[]  arr =  {  1,2,3 };
-----------------------------------------------------
[heap-동적데이터]              [stack-임시데이터] 
1번지{ 1,       2,       3}  ←  arr : 1번지
    arr[0]  arr[1]  arr[2]
-----------------------------------------------------


연습문제1)  array
패키지명 : com.company.java006_ex
클래스명 :  ArrayEx001
    1. 배열명 : arr
    2. 값 넣기 : 1.1  , 1.2  , 1.3  , 1.4  , 1.5
    3. for + length 로 출력

연습문제2)  array
패키지명 : com.company.java006_ex
클래스명 :  ArrayEx002
    1. 배열명 : arr
    2. 값 넣기 : 'A' , 'B' , 'C' , 'D'
    3. for + length 로 출력

연습문제3)  array
패키지명 : com.company.java006_ex
클래스명 :  ArrayEx003
    1. 배열명 : arr
    2. 값 넣기 : "아이언맨" , "헐크" , "캡틴"
    3. for + length 로 출력



연습문제4)  array
패키지명 : com.company.java006_ex
클래스명 :  ArrayEx004
    1. 배열명 : ch
    2. 값 넣기 : 'B' , 'a' , 'n' , 'a', 'n' , 'a'
    3. ch 배열에서 a의 갯수 세기


연습문제5)  array
패키지명 : com.company.java006_ex
클래스명 :  ArrayEx005
    1. 배열명 : ch
    2. 값 넣기 : 'B' , 'a' , 'n' , 'a', 'n' , 'a'
    3. ch 배열에서 대문자의 갯수카운트, 소문자의 갯수 카운트




int[] arr2 = new int[3];
-----------------------------------------------------
[heap-동적데이터]              [stack-임시데이터] 
1번지{ 1,       2,       3}  ←  arr : 1번지
    arr[0]  arr[1]  arr[2]
-----------------------------------------------------

연습문제6)  array
패키지명 : com.company.java007_ex
클래스명 :  ArrayEx006
    new 연산자 이용해서 배열만들기
    1. 배열명 : arr     
    2. 값 넣기 : 1.1  , 1.2  , 1.3  , 1.4  , 1.5    for+length 이용해보기
    3. for + length 로 출력


연습문제7)  array
패키지명 : com.company.java007_ex
클래스명 :  ArrayEx007
    new 연산자 이용해서 배열만들기
    1. 배열명 : arr     
    2. 값 넣기 : A	B	C	D	E    for+length 이용해보기
    3. for + length 로 출력


연습문제8)  array
패키지명 : com.company.java007_ex
클래스명 :  ArrayEx008
배열을 이용하여 다음의 프로그램을 작성하시오.   

    String[] name={"아이언맨","헐크","캡틴","토르","호크아이"};
    int[] kor={100,20,90,70,35};   
    int[] eng={100,50,95,80,100};
    int[] mat={100,30,90,60,100};
    int[] aver=new int[5];

     1. 평균구하시오.
     2. 합격,불합격   -  평균이 60점이상이면 합격, 아니면 불합격으로 처리하시오.
     3. 장학생   - 평균 95점이상이면 장학생으로 처리하시오. 
	   
3.출력예시: 	 
	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	이름		국어	영어	수학	평균	합격여부	장학생	
	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	아이언맨	100	100	100	100		합격	장학생	
	헐크		20	50	30	33	불합격	----	
	캡틴		90	95	90	91	합격	----	
	토르		70	80	60	70	합격	----	
	호크아이	35	100	100	78		합격	----	
	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

	※ 힌트1)	 
 	/////// 처리1 : 평균구하기  
 	1-1. (국어+영어+수학)/3
	1-2.  아이언맨의 평균 =  아이언맨의 국의 + 아이언맨의 영어 + 아이언맨의 수학 
	1-3.  aver[0] = kor[0] + eng[0] + mat[0]
	
	1-2.  헐크의 평균 =  아이언맨의 국의 + 아이언맨의 영어 + 아이언맨의 수학 
	1-3.  aver[1] = kor[1] + eng[1] + mat[1]

	/////// 처리2 : 합격,불합격 
	2-1. 평균이 60점이상이면 합격	 아니면 불합격
	2-2. 어디다가 합격, 불합격을 담아놓을 것인가? ※ 필요한것 : 합격,불합격을 담을 배열 	 
	2-3. 아이언맨의 평균이 60점 이상이라면 합격 / 아니면 불합격
	2-4. if(aver[0] >=60){   합격 } else{불합격} 

	/////// 처리3 : 장학생
	3-1. 평균 95점이상 장학생
	3-2. 어디다가 장학생을 담아놓을 것인가? ※ 필요한것 :  장학생 여부를 저장할 배열 
	3-3. 아이언맨의 평균이 95점이상이면 장학생 / 아니라면 ----
	3-4.  if(aver[0] >=95){ 장학생 } else{ ---- } 




# ■ WebBasic
## 6 배치요소

■1. Web  Basic  복습문제
■1. Web  Basic  복습문제
■1. Web  Basic  복습문제
■1. Web  Basic  복습문제

>>>> CSS 
CSS 다음 빈칸에 알맞은 코드를 채우시오.
  <div class="container c1">
    <div class="item i1">aaa</div>
    <div class="item i2">bbbbb</div>
    <div class="item i3">cc</div>
  </div>


/* [c1] 기본 html 흐름  display의 어떤속성이다  
AAA
BBB
CCC
*/
.c1{ ①  display:block; }

/* [c2] 자식요소들을 가로로 정렬하고, 유연한 레이아웃 사용하려고한다.   
AAA  BBB   CCC
*/
.c2{ ② display:flex;   }

/* [c3] flex 방향 :기본  
AAA  BBB   CCC
*/
.c3{  ③  display:flex;   ④ flex-direction:row;   }

/* [c4] flex 방향 : column - 거꾸로 세로방향  
CCC
BBB
AAA     
*/
.c4{ ⑤ display:flex;  ⑥  flex-direction:column-reverse; }

/* [c5] flex 방향 : 네비게이션 + 오른쪽정렬  
HOME  ABOUT		CONTACT
*/
.c5{  ⑦ display:flex;  }  	   /* 5-1. 네이게이션메뉴 가로배치 - 줄바꿈 방지 */
.c5 .item{ ⑧  flex:none;  }	    /* 5-2. 항목들 사이즈-크기를 한번에 설정 (none)  */
.c5 .item.i3{ ⑨ margin-left:auto;   }  /*  5-3.  마지막항목을 오른쪽 끝으로 정렬 */ 



■ ■ ■  복습문제 DAY014
■ ■ ■  복습문제 DAY014
■ ■ ■  복습문제 DAY014
 
>>>>>>  JAVA

■ 이론
Q1.  해당하는 for문의 코드를 적으시오.
1 2 3   

■ 실습1 - if문 버전 문제  
[eclipse] 사용자로부터 문자를 입력받아 다음과 같이 출력하세요.
m을 입력받으면 "mango" 출력
n을 입력받으면 "noodle" 출력
o를 입력받으면 "orange" 출력
그 외에는 "m, n, o가 아닙니다" 출력
  

■ 실습2 - switch문 버전 문제  
[eclipse] 사용자로부터 숫자를 입력받아 다음과 같이 출력하세요.
1을 입력받으면 "mango" 출력
2을 입력받으면 "noodle" 출력
3를 입력받으면 "orange" 출력
그 외에는 "1,2,3이 아닙니다" 출력
 


■ 실습3 - 반복문 응용
[eclipse] for, while, do while문을 이용해서 다음과 같이 출력하시오: 5 4 3 2 1
 


■ 실습4 - 배열 응용
[eclipse] 배열명 : nums
for문과 .length를 이용하여 값 넣기 : 10, 20, 30
for문과 .length를 이용하여 출력


>>>>>>>>>>>>>>>>>>>>>>>>>>
CSS 다음 빈칸에 알맞은 코드를 채우시오.
  <h3>📦 003. 푸터 (아래 고정)</h3>
  <div class="container c3">
    <div class="item i1">© 2025 Pizza Planet</div>
  </div>

/* Q1: 푸터를 아래쪽에 고정하려면?  */
/* Q2: 푸터를 아래쪽으로 밀어내려면?   */
    .c3{  ①     ②  ③   }
    .c3 .i1{  ④ }    


■ ■ ■  복습문제 DAY014 - ANSWER
■ ■ ■  복습문제 DAY014 - ANSWER
■ ■ ■  복습문제 DAY014 - ANSWER

>>>>>>>> JAVA
■ 이론
Q1.  해당하는 for문의 코드를 적으시오.
1 2 3  
```
for(int i=1; i<=3; i++){ System.out.print(i ); }  
```

■ 실습1 - if문 버전 문제  
[eclipse] 사용자로부터 문자를 입력받아 다음과 같이 출력하세요.
m을 입력받으면 "mango" 출력
n을 입력받으면 "noodle" 출력
o를 입력받으면 "orange" 출력
그 외에는 "m, n, o가 아닙니다" 출력
```
import java.util.Scanner;

public class Repeat015_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자를 입력하세요: ");
        char input = sc.next().chatAt(0);

        if (input== 'm') {
            System.out.println("mango");
        } else if (input== 'n') {
            System.out.println("noodle");
        } else if (input== 'o') {
            System.out.println("orange");
        } else {
            System.out.println("m, n, o가 아닙니다");
        }
        sc.close();
    }
}

```

■ 실습2 - switch문 버전 문제  
[eclipse] 사용자로부터 숫자를 입력받아 다음과 같이 출력하세요.
1을 입력받으면 "mango" 출력
2을 입력받으면 "noodle" 출력
3를 입력받으면 "orange" 출력
그 외에는 "1,2,3이 아닙니다" 출력
```
import java.util.Scanner;

public class Repeat015_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("숫자를 입력하세요: ");
        int num = sc.nextInt();

        switch (num) {
            case 1:
                System.out.println("mango");
                break;
            case 2:
                System.out.println("noodle");
                break;
            case 3:
                System.out.println("orange");
                break;
            default:
                System.out.println("1,2,3이 아닙니다");
        }
        sc.close();
    }
}

```


■ 실습3 - 반복문 응용
[eclipse] for, while, do while문을 이용해서 다음과 같이 출력하시오: 5 4 3 2 1
```
public class Repeat015_3 {
    public static void main(String[] args) {
        // for문
        for (int i = 5; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while문
        int j = 5;
        while (j >= 1) {
            System.out.print(j + " ");
            j--;
        }
        System.out.println();

        // do-while문
        int k = 5;
        do {
            System.out.print(k + " ");
            k--;
        } while (k >= 1);
    }
}

```


■ 실습4 - 배열 응용
[eclipse] 배열명 : nums
for문과 .length를 이용하여 값 넣기 : 10, 20, 30
for문과 .length를 이용하여 출력

```
public class Repeat015_4 {
    public static void main(String[] args) {
        int[] nums = new int[3];
        int data = 10;

        // 값 넣기
        for (int i = 0; i < nums.length; i++) {
            nums[i] = data; data+=10;
        }

        // 값 출력
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

```


>>>>>>>>> CSS - ANSWER
CSS 다음 빈칸에 알맞은 코드를 채우시오.
  <h3>📦 003. 푸터 (아래 고정)</h3>
  <div class="container c3">
    <div class="item i1">© 2025 Pizza Planet</div>
  </div>

/* Q1: 푸터를 아래쪽에 고정하려면?  */
/* Q2: 푸터를 아래쪽으로 밀어내려면?   */
    .c3{  ① display: flex;   ②flex-direction: column; ③ height: 200px; }
    .c3 .i1{  ④margin-top: auto;}    
