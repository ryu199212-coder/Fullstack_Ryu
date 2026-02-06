# 💻 Web Basic Portfolio

안녕하세요!  
웹 개발자로 커리어를 시작하기 위해 필수 기술들을 직접 구현하고 정리한 포트폴리오입니다.  
실무자가 바로 이해하고 검토할 수 있도록, 구조적이고 유지보수가 쉬운 코드를 지향했습니다.

---

## 🎯 개발 목적

- 웹 개발의 핵심 기술을 직접 구현하며 실무 감각 익히기
- 사용자 중심의 UI/UX 설계 경험
- 코드 품질, 가독성, 반응형 대응 등 실무 기준 충족

---

## 🛠️ 사용 기술 스택

| 기술        | 설명 |
|-------------|------|
| HTML5       | 시맨틱 태그 기반 구조화된 마크업 |
| CSS3        | Flexbox, Grid, Media Query, 애니메이션 |
| JavaScript  | DOM 조작, 이벤트 처리, 로직 구현 |
| Git & GitHub| 버전 관리 및 협업을 위한 기본 사용법 |
| VS Code     | 주요 개발 환경 |

---

## 📁 프로젝트 구성

### 1. 🏠 Landing Page
- 목적: 브랜드 소개용 반응형 웹 페이지
- 주요 기능:
  - 시맨틱 태그 기반 구조
  - 반응형 레이아웃 (모바일/태블릿/PC 대응)
  - CSS 애니메이션 및 트랜지션
- 실무 포인트:
  - 디자인 시스템 기반 컴포넌트 설계
  - 접근성 고려 (ARIA 속성 포함)

---

### 2. ✅ Todo List App
- 목적: JavaScript 기반 CRUD 기능 구현
- 주요 기능:
  - 할 일 추가/삭제/완료 처리
  - LocalStorage를 활용한 데이터 저장
  - 날짜별 필터링 기능
- 실무 포인트:
  - 모듈화된 JS 구조
  - 이벤트 위임 및 상태 관리

---

### 3. 👤 Portfolio Site
- 목적: 자기소개 및 프로젝트 소개
- 주요 기능:
  - 자기소개, 기술 스택, 프로젝트 목록
  - 반응형 디자인
  - GitHub 연동 및 외부 링크 제공
- 실무 포인트:
  - 실무용 자기소개 페이지로 활용 가능
  - SEO 최적화 기본 적용

---

## ✅ 실무 적용 역량

- **코드 가독성**: 네이밍 컨벤션, 주석, 폴더 구조 정리
- **반응형 웹**: 다양한 디바이스에서 테스트 완료
- **접근성 고려**: 시맨틱 태그 및 ARIA 속성 활용
- **버전 관리**: 커밋 메시지 규칙 및 브랜치 전략 적용
- **문서화 습관**: README 및 기능 설명 문서 포함





---

## 트러블슈팅
---
### 문제발생(1)
```bash
- 카드에 수치값 적용 불가
.card {
    width: 250;
    margin: 30px auto;
    border-radius: 20px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0);
    text-align: center;
    padding: 20px;
    transition: all 0.3s ease;}
```

### 문제해결(1)
```bash
- width : 230(px이 빠짐);
단위를 항상 잊지말고 달아주자
```
---
### 문제발생(2)
```bash
<h2 style="font-size: 18px;
                 color: #333;
                 padding-top: 10px;
                 padding-bottom: 5px;">유희재</h2>
```
### 문제해결(2)
```bash
코드상 문제는 없지만.
padding을 사용하면 관리해야하는 영역이 늘어나서 padding보다는 margin을 주는게 더 편함
오류가 안나더라도 작업의 편의성을 위해 신경써서 코딩해야한다.
```
---
### 문제발생(3)
```bash
    <title>002. tag</title>
</head>
<body>
    <div>
         <h3>제목 : h1~h6</h3>
         <p>제목태그 - h1(로고), h2(주메뉴)</p>
    
    
    <div>
        <h3>문단</h3>
        <p>일반문단
           공백 줄바꿈 인식 안됨
        </p>
        <pre>
         코드, 미리보기 문단
         공백 줄바꿈 인식 됨
        </pre>
    </div>
-------------------------------------------------------------------------------------------------
https://validator.w3.org/nu/#textarea

Error: End of file seen and there were open elements.

From line 18, column 5; to line 18, column 10

/pre>↩    </div>
```
### 문제해결(3)
```bash
<div>태그를 써서 작업을 했지만 </div>태그로 마무리를 하지 않았다.
열었으면 닫아야한다!
```
---
### 문제발생(4)
```bash
애니메이션 효과를 1번 2번 3번 순서대로 주고싶은데 순서대로 나오지 않음
.card:nth-of-type(1){animation-delay: 0.3s;}
.card:nth-of-type(2){animation-delay: 0.6s;}
.card:nth-of-type(3){animation-delay: 0.9s;}

<div>
    <div class="container">
            <h1>나의 포트폴리오</h1></div>
        
        <div class="card">
                <p><img src="web004_3/portfolio1.jpg" alt=""></p>
                <h2>쇼핑몰 웹앱</h2>
                <p>React + Firebase 기반의 쇼핑몰 웹 애플리케이션</p>
                <p>사용 기술: React, Firebase, Styled-components</p>
                <p><a>더보기</a></p>
            </div>
        
        <div class="card">
            <p><img src="web004_3/portfolio2.jpg" alt=""></p>
            <h2>AI 챗봇 서비스</h2>
            <p>Python 기반의 자연어 처리 챗봇 시스템</p>
            <p>사용 기술: Flask, TensorFlow, NLP</p>
            <p><a>더보기</a></p>
        </div>

        <div class="card">
            <p><img src="web004_3/portfolio3.jpg" alt=""></p>
            <h2>자연어 서비스</h2>
            <p>Python 기반의 자연어 처리 챗봇 시스템</p>
            <p>사용 기술: Flask, TensorFlow, NLP</p>
            <p><a>더보기</a></p>
        </div>

        <div class="clear">
        이 포트폴리오는 실제 프로젝트를 기반으로 구성되었으며, 각 프로젝트는 GitHub에서 확인할 수 있습니다.
        </div>    
</div>
```
### 문제해결(4)
```bash
<div>
    <div class="container">
            <h1>나의 포트폴리오</h1>
        
        <div class="card">
                <p><img src="web004_3/portfolio1.jpg" alt=""></p>
                <h2>쇼핑몰 웹앱</h2>
                <p>React + Firebase 기반의 쇼핑몰 웹 애플리케이션</p>
                <p>사용 기술: React, Firebase, Styled-components</p>
                <p><a>더보기</a></p>
            </div>
        .
        .
        .
        .
    <div class="clear">
        이 포트폴리오는 실제 프로젝트를 기반으로 구성되었으며, 각 프로젝트는 GitHub에서 확인할 수 있습니다.
        </div>
       
    </div>
</div>
-------------------------------------------------------------------------------------------------
<div class="container">
            <h1>나의 포트폴리오</h1></div>

        "container" 이라는 class 안에 card라는 class를 넣었어야지
        "relate"와 "absolute"코드를 인식해서 순서를 정할 수 있다
```

### 문제발생(5) - 단순오타
```bash
//Q1. #color 선택하기 color라는 변수에 담기
      window.addEventListener("load", function(){
      let color = document.getElementById("color");
      //Q2. 안에 있는 li태그들 선택
      let lis = color.getElementsByTagName("li"); 
      //Q3. 첫번째 RED의 fontweight="bold"
      lis[0].style.fontweight="bold";
    });
```
### 문제해결(5)
* JavaScript에서 CSS 속성은 카멜 케이스(camelCase)로 써야 하므로 fontWeight로 써야한다

### 문제발생(6) - border 스타일 적용 오류

```html
<input type="button" value="Change Border Style" 
       id="borderStyleBtn" class="btn btn-danger" />

<script>
  window.addEventListener("load", function(){
    document.getElementById("borderStyleBtn").onclick = function(){
      let style = prompt("테두리 스타일을 입력하시오 : ");
      document.getElementById("test2").style.border = "10px" + style + "red";
    }
  });
</script>
```

<br/>

**원인 분석**

* `solid`를 입력했을 때 기대한 `"10px solid red"` 대신 `"10pxsolidred"`로 인식되어 브라우저가 스타일을 무시함
* CSS 속성값은 `"굵기 스타일 색상"` 형태로 **각 값 사이에 반드시 공백이 필요**함
* 문자열 연결 시 `" "` (공백)을 넣지 않으면 `"10pxsolidred"`와 같은 잘못된 값이 되어 CSS 파싱 불가 → 결과적으로 스타일이 적용되지 않음

<br/>

### 문제해결(6)

* 문자열 결합 시 각 속성 사이에 **공백을 명시적으로 추가**해야 함
* 수정된 코드:

```javascript
document.getElementById("test2").style.border = "10px " + style + " red";
```

* 또는 가독성을 위해 **템플릿 리터럴**을 사용하는 것도 추천:

```javascript
document.getElementById("test2").style.border = `10px ${style} red`;
```

* 이 경우 `"solid"`, `"dashed"`, `"dotted"` 등 입력 시 의도한 대로 정상 적용됨

---
## 참고문헌
- [MDN Web Docs](https://developer.mozilla.org/ko/docs/Learn_web_development)
- [Subicura](https://subicura.com/2021/06/27/study-guide.html)
- ["subicura"네이버 블로그](https://blog.naver.com/daza0531/223747646151)