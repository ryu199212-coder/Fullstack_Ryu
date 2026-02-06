## 트러블슈팅
### 문제발생(1)
```bash
<h2 style="font-size: 18px;
                 color: #333;
                 padding-top: 10px;
                 padding-bottom: 5px;">유희재</h2>
```
### 문제해결
```bash
코드상 문제는 없지만.
padding을 사용하면 관리해야하는 영역이 늘어나서 padding보다는 margin을 주는게 더 편함
오류가 안나더라도 작업의 편의성을 위해 신경써서 코딩해야한다.
```

### 문제발생(2)
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
### 문제해결(2)
```bash
<div>태그를 써서 작업을 했지만 </div>태그로 마무리를 하지 않았다.
열었으면 닫아야한다!
```

### 문제발생(3)
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
### 문제해결(3)
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