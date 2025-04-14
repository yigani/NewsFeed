## 📌 개요
- 한줄 소개: SNS처럼 사용자 간 팔로우 및 게시글 조회가 가능한 뉴스피드 서비스입니다.
- 프로젝트 기간: 2025.04.07-2025.04.14

## 🧑‍🤝‍🧑 팀원 소개
| 이름 | 역할 | 세부사항                                          |
| --- | --- |-----------------------------------------------|
| 이은지 | Users | 회원가입 / 비밀번호 변경 / 회원 탈퇴                        |
| 이소미 | Users | 본인 및 친구 프로필 조회 / 유저 검색 및 프로필 수정, 팔로우 관련 전체 기능 |
| 박소윤 | Users | 로그인 인증/인가                                     |
| 이관희 | Posts | 게시글 목록조회 및 전체조회와 페이징                          |
| 심재민 | Posts | 게시글 생성 수정 삭제 단건조회, 전체 예외 처리                   |

## 🧱 ERD
![](https://velog.velcdn.com/images/deabaind/post/c3c2d35a-dcbe-4a00-880a-ad8b18b5cfaa/image.png)

## 📑 API 명세서

### 👤 Users
![](https://velog.velcdn.com/images/deabaind/post/c1061024-b37d-4fce-b8ab-54267c26bee7/image.png)

### 👔 Profiles
![](https://velog.velcdn.com/images/deabaind/post/b1cda616-03b1-47fe-a35f-00531c71321a/image.png)

### 📑 Posts
![](https://velog.velcdn.com/images/deabaind/post/bbfd12e5-a107-45ab-b503-5d0ca92fe1e3/image.png)

### 👥 Follows
![](https://velog.velcdn.com/images/deabaind/post/317648d6-bd85-41f3-8229-3d329775f7d8/image.png)

## 🛠 기술 스택
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">



- Back-End: Java 17, Spring Boot 3.4.4, JPA, MySQL
- Tools: GitHub, Postman, Notion

## 🚀 주요 기능

---

### 👤 User 기능

#### 🗝️ 회원 관리
- 회원 가입: `email`, `password`, `username` 입력
    - 📛 이메일은 정규표현식으로 형식 제한
    - 📛 이미 등록된 이메일은 사용 불가
- 로그인: Cookie & Session 기반 인증
- 로그아웃
- 회원 탈퇴: 비밀번호 확인 후 **비활성화로 상태변경**
- 비밀번호 변경
    - 📛 기존 비밀번호와 같은 값으로 변경 불가

#### 🙋‍♀️🙋‍♂️ 내 정보
- 내 정보 조회: 로그인한 유저의 `username`, `email` 확인 가능
- 내 프로필 생성
    - 입력 가능 필드: `gender`, `birthday`, `introduction`, `image`
    - ⚠️ 4개 중 하나만 입력해도 생성됨
- 내 프로필 수정: 위 4개 필드 수정 가능

#### ️💁‍♀️💁‍♂️ 타 유저 관련 기능
- 유저 이름 조회: `userId`로 `username` 조회 가능
    - 📛 이메일은 민감 정보로 조회 불가
- 유저 검색: `username`에 검색어 포함된 유저 전체 조회
- 유저 프로필 조회: `userId`로 `gender`, `birthday`, `introduction`, `image` 조회 가능
- 유저의 팔로워/팔로잉 수 조회: `userId` 기준

---

### 🤝 Follow 기능

- 팔로우 / 언팔로우
- 팔로잉 수 조회 / 팔로워 수 조회
- 팔로잉 목록 조회 / 팔로워 목록 조회

---

### 📝 Post 기능

- 게시글 생성: `title`, `content` 필수 입력
  - 미 입력시 예외 처리하여 Json 형식으로 반환 
  - 예시: { "`필드`" : "`오류 메세지`" }
- 게시글 단건 조회: `id`를 조회하여 게시글 조회
- 게시글 수정: `title`, `content` 필수 입력하여 수정
  - 미 입력시 예외 처리하여 Json 형식으로 반환
  - 예시: { "필드" : "오류 메세지" }
- 게시글 삭제: `id`를 조회하여 존재 유무 확인 후 삭제
  - 게시글이 조회되지 않는다면 예외 처리
- 내 게시글 목록 조회
- 특정 유저(ID)의 게시글 목록 조회
- 전체 게시글 조회

