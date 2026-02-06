##  PROJECT
[project]
    ㄴback  : boot+jwt+redis+oauth2.0+jpa+mybatis
    ㄴfront : react+next+antd

    
###1. [BACK] boot+security+jwt+redis+oauth2.0+jpa+mybatis

1. JAVA 17
2. SPRING BOOT (gradle)
3. boot+security+jwt+redis+oauth2.0+jpa+mybatis 

- SPRING boot (애플리케이션 기반의 프레임워크/ 내장 tomcat / 자동설정 )
- SPRING security (인증,인가/필터체인의 요청보호/oauth2.0 같은 인증방식으로 쉽게 연동)
- jwt( json web token : 토큰기반의 인증방식 / 토큰안에 사용자 정보와 권한을 담아 전달 ,
       서버가 세션을 직접 관리하지 않고, 클라이언트가 토큰을 보관)
- redis( 캐시/세션을 관리 , refresh token을 저장, 캐싱처리에 활용, 분산환경에서 세션공유 가능 )
- oauth2.0 (외부인증 연동, 구글, 네이버, 카카오로그인)
- jpa(  orm기반의 데이터베이스 접근 , 엔티티클리스와 db테이블 매핑, sql작성없이 객체중심의 데이터 처리)
- mybatis ( 복잡한 쿼리 작성 )

1.  SPRING boot  → 애플리케이션 실행기반
2.  SPRING security + jwt/oauth2.0   → 인증/인가 처리
3.  redis   →  토큰/세션/캐시관리
4.  jpa + mybatis  → 데이터베이스 접근 (orm + sql mapper 병행)

<br/>

##### [실습]  1. 스프링부트 프로젝트 
- [x] 1. 개발개요안내
- [x] 2. java.sun.com - JAVA 17 다운로드 - 설치
- [x] 3. SPRING BOOT   - https://spring.io/ - 다운로드 - 설치
  > 이전버젼
  https://github.com/spring-projects/spring-tools/wiki/Previous-Versions
- [x] 4. SPRING BOOT 프로젝트 만들기
- [x] 5. lombok


<br/>

##### [실습] 2. model  (엔티티 → 레파지토리 → 서비스)
1. 엔티티 관계도
2. 엔티티 작성

사람( AppUser ) → 글(Post) → 댓글(Comment)
사람( AppUser ) → 글(Post) → 좋아요(PostLike)
사람( AppUser ) → 글(Post) → 리트위(Retweet)
사람( AppUser ) → 다람 사람( AppUser ) → 팔로우 (Follow)
글(Post) → 해시태그(Hashtag)
글(Post) → 사진(Image)

```
👤 User(AppUser)
   ├── 📝 Post(글)
   │     ├── 💬 Comment(댓글)
   │     ├── ❤️ PostLike(좋아요)
   │     ├── 🏷️ Hashtag(해시태그)
   │     ├── 🖼️ Image(사진)
   │     └── 🔄 Retweet(리트윗)
   │
   ├── 👣 Follow(팔로우) → 다른 User
   └── 🚫 Block(차단) → 다른 User
```

- [x] 1. AppUser
- [x] 2. Post
- [x] 3. Image
- [x] 4. Hashtag
- [x] 5. Comment
- [x] 6. Follow
- [x] 7. Retweet
- [x] 8. PostLike


AppUser 관계매핑
###1. 사람 → 글
- 내가 쓴 게시글
- 한 사람이 여러글을 쓸수 있다. (OneToMany)
- 글(Post) 쪽에서는 누가썼는지 기억 (ManyToOne)


Post 관계매핑
###1. 글(Post.java : 테이블명 Posts) → 이미지(Image.java : 테이블명 Images)
- 글은 많은 이미지를 갖는다.   (OneToMany)
- 이미지는 글 하나에만 속한다.   (ManyToOne)
```
Long id, String content,  AppUser user;  boolean deleted = false;
LocalDateTime createdAt;  LocalDateTime updatedAt;
```

Image
```
Long id, String src, Post post;
```


2. 레파지토리
[com.thejoa703.repository]
- [x] 1. AppUserRepositoy
- [ ] 2. PostRepositoy
- [ ] 3. ImageRepositoy
- [ ] 4. HashtagRepositoy
- [ ] 5. CommentRepositoy
- [ ] 6. FollowRepositoy
- [ ] 7. RetweetRepositoy
- [ ] 8. PostLikeRepositoy

> 참고경로 : https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/?utm_source=copilot.com#reference
```
@Repository
public interface  AppUserRepositoy extends JpaRepository<AppUser, Long>{ //Entity,PK

}

CREATE : save     -   INSERT INTO appuser (컬럼1,컬럼2,,) values (?,?,,)
READ   : findAll  -   SELECT  * from appuser 
         findById -   SELECT  * from appuser  where id=? 
UPDATE : save     -   update  appuser  set 컬럼1=? ,컬럼2=?  where   id=? 
DELETE : deleteById - delete from appuser  where id=?
```

          사용자      관리자
CREATE    ◎회원가입    ◎회원가입
READ      로그인, 이메일중복, 닉네임중복 
UPDATE    ◎닉네임수정, ◎이미지수정
DELETE    ◎회원탈퇴


---
3. mybatis

- [x] 1. build.gradle
- [x] 2. application.yml
- [x] 3. [com.thejoa703.domain] - DeptUser  @Entity
- [x] 4. [com.thejoa703.mapper] 
        - @Mapper  (복잡한 SQL)  / 기본CRUD @Repository (save, findById, findAll, deleteById)
- [ ] 5. [com.thejoa703.service] - DeptService
- [ ] 6. [com.thejoa703.controller] - DeptController


---
4. service

- [ ] 1. dto - requestDto / responseDto
- [ ] 2. UTIL : 이미지업로드
      - application.yml 이미지설정
      - service 작성
- [ ] 3. UTIL : 암호화
- [ ] 4. AppUserService  <유저>
- [ ] 5. PostService     <게시글>
- [ ] 6. Etc Service


---
5. security + jwt + redis + oauth2.0
 
 ■ 1. 전체구조
 1) 회원가입 / 로그인 방식 2가지
  - 로컬 회원가입 : 이메일/비밀번호 가입 
                    → PasswordEncoder 암호화
                      → 로그인 JWT 발급

  - 소셜 로그인 : 구글 / 카카오 / 네이버 인증 성공
                    → Oauth2SuccessHandler 에서 JWT 발급

 2) JWT 발급구조
  - Access Token : 짧은 기간 유효(출입증) → api 호출 시 사용
  - Refresh Token : 긴 기간 유효(장기체류) → redis(ex 냉장고)에 안전보관

 3) 프론트엔드 처리
  - Acess Token을 localStorage에 저장
  - Api 호출 `Authorization : Bearer <token>` 헤더에 붙임

 4) 서버처리
  - `JwtAuthenticationFilter` 가 모든 요청 앞에서 토큰검증
  - 검증 성공 시 `SecurityContext` 사용자 정보 저장
  - Controller/Service에서 현재 사용자 id로 db조회 응답

STEP1)
```
[사용자]
   ├─▶ 로컬 회원가입/로그인
   │       - 이메일/비밀번호 → DB 저장
   │       - 로그인 성공 시 JWT 발급
   │
   └─▶ 소셜 로그인(OAuth2)
           - 구글/카카오/네이버 인증
           - OAuth2SuccessHandler 실행
             • 사용자 정보 추출
             • DB 저장/조회
             • Access Token 발급 (출입증)
             • Refresh Token 발급 (장기체류증) → Redis 저장 + 쿠키
```

STEP2)
```
[프론트엔드]
   └─▶ Access Token localStorage 저장
        API 호출 시 Authorization 헤더에 Bearer 붙임
```

STEP3)
```
[Spring Boot 서버]
   ├─▶ JwtAuthenticationFilter
   │       - 토큰 검증 (출입증 검사)
   │       - SecurityContext에 사용자 정보 저장
   │
   └─▶ Controller/Service
           - userId 기반 DB 조회
           - 응답 반환 (사원증 스캔)
```
■ 2. JWT구조
 1) Header → 토큰의 머릿말(이 토큰은 HS256 알고리즘으로 서명했어! 정보)
```json
    { "alg": "HS256", "typ": "JWT" }
```

 2) Payload(Claims) → 토큰의 몸통(누가, 어떤권한, 언제까지 사용가능한지 = 사용자의 신분증 정보)
```json
   {
     "iss": "thejoa703",   // 발급자
     "sub": "12345",       // 사용자 ID
     "role": "USER",       // 권한
     "email": "user@test.com",
     "exp": 1737000000     // 만료 시간
   }
```

 3) Signature → 토큰의 도장(서버만 아는 비밀키로 찍은 도장 → 위변조 방지)
```json
   HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
```

■ 3. 핵심정리
 1) JWT vs 세션
  - 세션 : 서버 메모리에 사용자 상태를 저장 → 서버 확장 시 부담
                                        (서버에서 출입명단 직접 들고있음)
  - JWT(Json Web Token) : 토큰자체에 인증정보를 포함 → 서버 확장 시 부담 → 확장성
                                        (사용자가 출입증을 직접 들고다님)

 2) Access Token vs Refresh Token
  - Access Token : 짧은 기간 유효(출입증) → api 호출 시 사용
  - Refresh Token : 길 기간 유효(장기체류증) → redis에 안전보관

 3) Redis 사용이유?
  - 토큰냉장고 → 장기체류증 안전하게 보관, 필요시 꺼내 씀
  - Refresh Token 중앙에서 관리
  - TTL(만료 시간)로 가종 만료처리
  - 로그아웃 시 즉시 삭제

[실습1]
1. docker 에서 redis 설정
 - https://www.docker.com/products/docker-desktop/
 - 다운로드 및 설치

```bash
docker --version
docker ps
docker pull redis
docker run -d --name my-redis -p 6379:6379 redis
```
```bash
docker exec -it my-redis redis-cli
docker exec -it my-redis redis-cli FLUSHALL
keys *
get 저장이름
```

2. build.gradle → security, jwt, redis, oauth2.0
3. application.yml / application-oauth.yml, .dot(중요키 보관)

[실습2]
1. security + jwt + redis
 - JwtProperties : 토큰
   * issuer, secret, expSeconds 기본속성
 - JwtProvider   : 토큰발급
   * Access Token(출입증), Refresh Token 생성, 파싱
 - TokenStore    : 토큰 저장소
   * Redis 저장소, Refresh Token refresh:<userId>
 - JWTAuthenticationFilter : 보안게이트
   * 매 요청마다 Authorization 헤더확인 → Bearer token 검증 → SecurityContext에 사용자 정보 저장
   * api 입구에서 신분등 검사하는 게이트

2. oauth2.0
 - Oauth2 : 입국 심사대
 - UserInfoOAuth2 / UserInfoNAver / UserInfoKakao / UserInfoGoogle
 - CustomOAuth2User
 - OAuth2SuccessHandler

3. 설정파일
 - SecurityConfig
 - WebConfig
 - RedisConfig
 - SwagerConfig

[실습3]
1. sercive
    >> AuthUserJwtService [공통]
2. controller
    >> 각각의 컨트롤러에서 
---
6. Controller





