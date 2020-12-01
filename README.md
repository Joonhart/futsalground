## futsalground   
<img src="https://user-images.githubusercontent.com/64246924/100685490-d9285000-33bf-11eb-92e6-d268c70a36f1.JPG">


### 프로젝트 사이트 링크
[futsalground바로가기](http://ec2-3-35-65-254.ap-northeast-2.compute.amazonaws.com:8080/)
- 회원 아이디 : test1@test.com ~ test10@test.com 10개, 비밀번호는 1234 입니다.

### 프로젝트 개요
- 기존에 있는 대관사이트와 용병 모집 사이트의 분리로 인한 번거로움을 해소하고자 구장과 용병모집 기능이 함께 있는 사이트를 제작
- 풋살장 대관과 용병 모집 & 지원 기능 제공

### 개발 환경
 * IDE - Intellij
 * frondEnd - Thymeleaf, Bootstrap
 * backEnd - Spring Boot ver.2.3.5, jdk ver.11.0.8, MySQL
 * 서버 구축 - aws
 
### 기능소개
1. 회원 가입 및 로그인
2. 간편 게시판
3. 용병 모집 & 지원
4. 풋살장 대관
5. 마이페이지

### 기능 상세 소개
#### 1. 회원 가입 및 로그인
<div>
<img width="300" src="https://user-images.githubusercontent.com/64246924/100685494-da597d00-33bf-11eb-8f04-2b833f448f39.JPG">
<img width="300" src="https://user-images.githubusercontent.com/64246924/100685492-da597d00-33bf-11eb-882c-06703863f2a8.JPG"> 
</div>
 
- 회원 가입 및 로그인입니다. 선호지역에 따라 구장 리스트 조회 시 선호지역 우선으로 조회됩니다.

#### 2. 간편 게시판
<img src="https://user-images.githubusercontent.com/64246924/100686675-2c9b9d80-33c2-11eb-8897-5a01a9465851.JPG">
<img src="https://user-images.githubusercontent.com/64246924/100686679-2e656100-33c2-11eb-8949-d8bdad94d3e4.JPG"> 

- CRUD 기능을 갖춘 게시판 

#### 3. 용병 모집 & 지원
##### 3-1 용병 모집 회원 관점
<img src="https://user-images.githubusercontent.com/64246924/100690269-76d44d00-33c9-11eb-9783-3f1ef4cf1b2d.JPG">

  - 용병모집 리스트
  
<div>
<img width="450" src="https://user-images.githubusercontent.com/64246924/100690277-79cf3d80-33c9-11eb-8bbd-682a5011b23a.JPG">
<img width="450" src="https://user-images.githubusercontent.com/64246924/100690279-7b990100-33c9-11eb-91d4-0b3e51eaa157.JPG">
</div>
 
 - 용병모집 글 등록 폼 및 조회 창
 - 용병을 모집할 수 있으며, 팀 정보 및 구장정보를 입력하여 지원자들에게 정보를 제공할 수 있습니다.  
<img src="https://user-images.githubusercontent.com/64246924/100690766-9ae45e00-33ca-11eb-9789-2a482320f861.JPG">

 - 마이페이지에서 회원이 등록한 용병모집 글 현황을 확인할 수 있습니다  
<img src="https://user-images.githubusercontent.com/64246924/100690777-9ddf4e80-33ca-11eb-91ea-a452cda20e85.JPG">

 - 마이페이지에서 자신의 용병모집 글에 지원한 지원자들의 정보를 확인할 수 있고, 지원자를 선별할 수 있습니다. 
<img src="https://user-images.githubusercontent.com/64246924/100691043-2c53d000-33cb-11eb-9e8c-81b44f0eecf1.JPG">

  - 용병 모집 인원이 마감되면 용병 게시글에서 모집 마감이라고 변경됩니다.
  
##### 3-2 용병 지원 회원 관점
 
<div>
<img src="https://user-images.githubusercontent.com/64246924/100691176-789f1000-33cb-11eb-86e1-901e73a5d60f.JPG">
<img src="https://user-images.githubusercontent.com/64246924/100691180-7b016a00-33cb-11eb-8b92-d40f196c4220.JPG">
</div>

  - 지원 즉시 마이페이지-용병신청현황으로 넘어가며, 모집 상태를 확인할 수 있습니다.
 


 
####  4. 풋살장 대관
 (로그인을 안했을 경우 풋살장 조회 창)
 로그인을 안했을 경우 최근 등록된 순서대로 풋살장을 보여줍니다.
 (로그인을 하였을 경우 풋살장 조회 창)
 로그인을 하였을 경우 회원 가입시 선택한 선호지역을 우선으로 하여 풋살장을 보여줍니다.
 (검색 결과 창)
 지역 혹은 풋살장 이름으로 검색할 수 있습니다.
 (리스트 화면 소개 창), (상세화면 창)
 예약은 2시간 단위로 가능하며, 현재날짜 이후로 30일 동안만 가능합니다.
 (마이페이지 구장예약 화면)
 예약 즉시 마이페이지로 이동하며, 구장예약 현황 확인 및 취소를 할 수 있습니다. 
  
 #### . 마이페이지

### 추가 개발 예정
 - Spring Security & 소셜 로그인
 - 팀 기능 구현
 - 
