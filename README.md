**개요**
- Spring Security 학습용 데모 프로젝트입니다. 로그인/로그아웃, 권한별 접근 제어, 회원가입(JPA), Mustache 기반 뷰 렌더링을 포함합니다.

**기술 스택**
- Java 17, Gradle
- Spring Boot `4.0.0`
- Spring Security, Spring Web MVC, Spring Data JPA
- Mustache 템플릿 엔진
- PostgreSQL, Lombok

**주요 기능**
- 인증 방식: 현재 HTTP Basic 활성화(브라우저 팝업 인증). In-Memory 사용자(`user1`/`ADMIN`, `user2`/`USER`, 비밀번호 모두 `1234`)로 인증 가능
- 폼 로그인: `/login` 페이지 제공(뷰 존재). 실제 폼 로그인을 사용하려면 `SecurityConfig`에 `formLogin()` 설정 추가 필요
- 회원가입: `JoinService`가 신규 사용자를 DB에 저장(`ROLE_USER`, 비밀번호 BCrypt). 단, 현재 인증은 In-Memory 기반이라 가입 사용자는 로그인에 아직 미연동
- 권한 제어: `/admin`은 `ADMIN`만, `/my/**`는 `ADMIN` 또는 `USER`만 접근, `/`, `/login`, `/join` 등은 모두 허용. 그 외 요청은 인증 필요
- 세션/로그아웃: 동시 세션 1개 제한, 세션 고정 보호. 로그아웃은 Spring Security 필터로 처리되며 기본적으로 `POST /logout` 요청과 CSRF 토큰이 필요(성공 시 `/`로 리다이렉트)

**디렉터리 구조**
- `src/main/java/com/example/TestSecurity`
  - `config/SecurityConfig.java`: 보안 설정 및 In-Memory 사용자 정의
  - `controller/*.java`: `/`, `/login`, `/join`, `/admin`, `/logout` 등 라우트 컨트롤러
  - `service/*.java`: 회원가입 로직, UserDetailsService 구현
  - `entity/UserEntity.java`: 사용자 엔티티
  - `repository/UserRepository.java`: 사용자 리포지토리(JPA)
- `src/main/resources`
  - `templates/*.mustache`: `main`, `login`, `join`, `admin` 화면
  - `application.properties`: DB 및 JPA, Mustache 설정

**환경 준비**
- 필수: Java 17, PostgreSQL 설치/실행, Gradle Wrapper 사용
- DB 접속 정보(기본값)는 `src/main/resources/application.properties`에서 수정
  - `spring.datasource.url=jdbc:postgresql://localhost:5432/test`
  - `spring.datasource.username=[username]`
  - `spring.datasource.password=[password]`
  - `spring.jpa.hibernate.ddl-auto=none` 이므로 테이블을 미리 생성해야 합니다.


**실행 방법**
- 의존성 설치/빌드 및 실행
  - `./gradlew bootRun`
- 서버 실행 후 접속
  - 기본 포트: `http://localhost:8080`

**엔드포인트**
- `/`(GET): 로그인 사용자 ID와 ROLE 출력
- `/login`(GET): 로그인 페이지(Mustache)
- `/join`(GET): 회원가입 페이지(Mustache)
- `/joinProc`(POST): 회원가입 처리(중복 아이디면 저장하지 않음)
- `/admin`(GET): `ADMIN` 전용 페이지
- `/logout`(GET): 로그아웃 처리 후 `/`로 리다이렉트



**템플릿 위치**
- `src/main/resources/templates`
