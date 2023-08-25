# 205번가

<img src="https://github.com/gayeonP/spring-OneZo/assets/62829894/35ffc8b8-e573-4cc8-bbbd-e84f48221480">

<br><br>

## 개요

본 프로젝트는 카카오 클라우드 스쿨 3기 최종 프로젝트를 위한 스프링 공부 용도로 한 미니 프로젝트이다. <br>
웹 프로젝트의 기본이 되는 쇼핑몰을 개발함으로써 기본적인 웹 개발 능력을 향상시킨다. <br>

<br><br>

## 팀원 소개

<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/JANG-WON-JUN"><img src="https://avatars.githubusercontent.com/u/123234152?v=4" width="100px;" alt=""/><br /><sub><b>팀장 : 장원준</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/jgm0327"><img src="https://avatars.githubusercontent.com/u/100139942?v=4" width="100px;" alt=""/><br /><sub><b>팀원 : 장규민</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/ljkhyeong"><img src="https://avatars.githubusercontent.com/u/115821049?v=4" width="100px;" alt=""/><br /><sub><b>팀원 : 임정규</b></sub></a><br /></td>
     <tr/>
      <td align="center"><a href="https://github.com/icj4153"><img src="https://avatars.githubusercontent.com/u/115138216?v=4" width="100px;" alt=""/><br /><sub><b>팀원 : 임창준</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/gayeonP"><img src="https://avatars.githubusercontent.com/u/62829894?v=4" width="100px;" alt=""/><br /><sub><b>팀원 : 박가연</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/Hwangseokjun97"><img src="https://avatars.githubusercontent.com/u/88317274?v=4" width="100px;" alt=""/><br /><sub><b>팀원 : 황석준</b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

<br><br>

## 기술 스택

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springdatajpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/querydsl-003545?style=for-the-badge&logoColor=white">
<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

<br><br>

## ERD

<img src="https://github.com/gayeonP/spring-OneZo/assets/62829894/c9e44589-0f91-40e2-b252-72f40ba89c0d">

<br><br>

## 명명규칙

- ## [소스파일 위치]

1. /api

   - Rest API에 관련된 클래스가 위치한다.

2. /config

   - 프로젝트 설정 관련 클래스가 위치한다.

3. /domain

   - 엔티티, 서비스, 리포지토리 등 비즈니스 로직에 관련된 클래스가 위치한다.

4. /utils

   - 프로젝트 전체에서 사용하는 유틸성 클래스가 위치한다.

5. /web

   - Presentation layer (클라이언트와 직접 연결되는 계층)에 관련된 클래스가 위치한다.

   - 컨트롤러, dto, 예외 처리 등의 클래스가 있다.

6. /resouces/templates

   - 화면을 구성하는 html 파일이 위치한다.

- ## [파일명 명명 규칙]

1. /api 하위의 클래스 명명 규칙

   - RestAdvice의 경우 접미어로 RestAdvice를 붙인다.

     ex) ExHandlerApiAdvice

   - Rest Controller의 경우 ApiController를 붙인다.

     ex) OrderApiController

2. /config 하위의 클래스 명명 규칙

   - 설정파일 성격에 따라 하위 패키지를 만든다.

     ex) security/SecurityConfig

     ex) querydsl/QueryDslConfig

   - 접미어로 Config를 붙인다.

     ex) MessageConfig, QueryDslConfig

3. /domin 하위의 클래스 명명 규칙

   - 엔티티의 경우 Entity를 접미어로 붙이지 않는다.

     ex) Order, OrderItem

   - 서비스의 경우 접미어로 Service를 붙인다.

     ex) OrderService, OrderItemService

   - 리포지토리의 경우 접미어로 Repository를 붙인다.

   - QueryDsl이 관련된 인터페이스의 경우 QueryRepository를 접미어로 붙인다.

   - QueryDsl이 관련된 클래스의 경우 QueryRepositoryImpl을 접미어로 붙인다.

   - 인터페이스를 구현하는 클래스를 작성하는 경우 impl 패키지 하위에 클래스를 작성한다.

4. /utils 하위의 클래스 명명 규칙

   - Utils를 접미어로 붙인다.

5. /web 하위의 클래스 명명 규칙

   - advice의 경우 접미어로 Advice를 붙인다.

     ex) ExHandlerAdvice

   - argumentResolver의 경우 접미어로 ArgumentResolver를 붙인다.

     ex) LoginMemberArgumentResolver

   - 컨트롤러의 경우 접미어로 Controller를 붙인다.

     ex) OrderController

   - dto의 경우 성격에 따라 하위 패키지를 만든다.

   - 하위 패키지에서 request, response 패키지를 만든다.

   - request 클래스의 경우 Create 혹은 Edit 접두어를 붙인다.

   - response 클래스의 경우 SimpleView 혹은 ComplexView 접미어를 붙인다.

6. resouces/templates 하위의 html 파일 명명 규칙

   - 성격에 따라 디렉토리를 만든다.

   - 단일 건을 보여주는 화면은 View를 접미어로 붙인다.

   - 다수 건을 보여주는 화면은 Views를 접미어로 붙인다.

- ## [변수 명명 규칙]

1. 카멜 케이스와 자바의 기본 명명 규칙을 따른다.

2. 다수 건을 조회하는 컬렉션의 경우 복수형으로 변수명을 작성한다.

   ex) orders, items

3. 변수명 짓기 어려울 때 참고 링크

   Curioustore

4. 뭔가 애매하면 반드시 팀원과 상의해서 결정 후, 명명 규칙에 추가하기

- ## [URL 규칙]

1. 요청 방식 별 HTTP 메소드

   - Form 요청인 경우 Get, Post 메소드만 사용 (form에서 get, post만 지원함)

   - Rest API 요청인 경우 Get, Post, Put, Patch, delete 등의 메소드 사용

2. 권한에 따른 URL

   - 비회원이 접근 가능한 URL → 별도의 접두어 없음

   - 회원만 접근 가능한 URL → member/\*\*

   - 판매자만 접근 가능한 URL → seller/\*\*

   - 관리자만 접근 가능한 URL → admin/\*\*

3. Form 요청 시 URL

   - {권한에 따른 접두어}/{API 성격에 따른 접두어}/\*\* 로 작성한다.

   - 단일 건 조회 시 → get 메소드 + 아무것도 붙이지 않는다.

     ex) @GetMapping("member/order”)

   - 다수 건 조회 시 → get 메소드 + API 성격에 따른 접두어의 복수형

     ex) @GetMapping("member/orders”)

   - 데이터 생성 시 → post 메소드 + create 접두어

     ex) @PostMapping("member/create”)

   - 데이터 수정 시 → post 메소드 + edit 접두어

     ex) @PostMapping("member/edit”)

   - 데이터 삭제 시 → post 메소드 + delete 접두어

     ex) @PostMapping("member/delete”)

   그 외의 경우에는 의미를 잘 나타내도록 작성하면 된다.

- ## [DB 테이블 명명 규칙]

1. 기본적으로 테이블명, 컬럼명은 스네이크 케이스를 사용한다.

2. DB 테이블은 대문자로 작성한다.

3. DB 테이블명에는 \_TB 접미어를 붙인다.

4. 컬럼은 소문자로 작성한다.

- ## [소스코드 주석 규칙]

1. 메소드 위에서 /\*\* 입력 후 자동 생성되는 java 문서화 주석을 입력한다.

2. 아주 간단한 메소드는 작성할 필요는 없지만 비즈니스 로직에 관한 메소드에는 주석을 작성한다.

3. 나중에 특정 코드를 누가 작성했는지, 언제 최초 작성되었고 수정되었는지 주석을 활용하여 추적이 가능하다.

4. 주석에 들어갈 필수 내용

   - 간단한 기능 설명

   - 최초 작성자, 최초 작성일, 수정자, 수정일

   - @param, @return, @throw (이 3가지는 /\*\*로 주석을 만들면 자동으로 완성됨)

5. 주석을 작성한 예시

```java
   public class ItemService {

	/**
	 * 기능 : 상품의 이름 리스트를 조회한다.
	 * 작성자 - 장원준
	 * 작성일 - 2020.07.21
	 * 수정자 - 임창준
	 * 수정일 - 2020.07.25
	 * @param searchCondition
	 * @return
	 */
	public List<String> getItemNames(String searchCondition) {
		return List.of("name1", "name2");
	}
}
```

6. 주석을 활용하는 예시

   - getItemNames를 굳이 들어가지 않고 어떤 메소드인지 확인할 수 있다.

   - 마우스 커서를 올려서 아래처럼 팝업으로 확인할 수 있다.

   - 마우스 커서를 두고 F1을 눌러 팝업으로 확인할 수 있다. (맥은 F1인데 윈도우는 다를 수 있음)

7. Java 문서화 주석 관련 블로그

   https://parkadd.tistory.com/137

8. 팁!

   git의 git blame (파일명) 명령어로 수정자를 확인할 수 도 있다.

<br>

## 참고문서 공유

<details>
<summary>MySQL & Hibernate에서 @Lob 사용 시</summary>

1.  이렇게 생성 시 DB 에서는 tiny text 타입으로 테이블이 생성되며, 최대 길이는 255 밖에 안된다.

```java
@Lob
private String contens;

// 만약 text 타입으로 컬럼을 만들고 싶다면 다음과 같이 작성하자.
@Column(columnDefinition = "TEXT DEFAULT ''", nullable = false)
private String content;
```

</details>

<br>

<details>
<summary>git에서</summary>

1. 다른 branch 에 있는 특정 file 만 merge 하고 싶을 때

   git checkout -p [가져올 파일이 있는 branch 명] --[가져올 파일 경로]

```
ex.
git checkout -p feature/item --src/main/java/org/kakao/kakaoshopping/KakaoShoppingApplication.java
```

2. git merge 후 local 에서 pull & merge 오류

   오류 코드:

```
src/main/java/org/kakao/kakaoshopping/KakaoShoppingApplication.java: needs merge
error: you need to resolve your current index first

// 해결:
git reset --merge
```

3. 작업해야 하는 branch 가 아닌 다른 branch 에서 작업 했을 때

```
git stash // 1. 작업한 내용을 임시 저장소로 옮김
git checkout {원하는 브랜치} // 2. branch 이동
git stash pop stash@{0} // 3. 가장 최근에 push 한걸 pop
// 이후 commit & push 동일
```

</details>

<br>

<details>
<summary>code with me & 페이징 성능 개선</summary>

- code with me 참고

  https://not-null.tistory.com/70

- 페이징 성능 개선

  https://jojoldu.tistory.com/528

  https://jojoldu.tistory.com/529

  https://jojoldu.tistory.com/530

  https://jojoldu.tistory.com/531

</details>
