# blog
blog 관련 나만의 기능 구현
 기능1) 블로그 검색 (검색키워드, 정확도/최신, Pagination) : /blog/search
 기능2) 인기 검색어 목록 (Refresh 버튼) : /blog/history 

기능 점검을 위한 빌드 결과물
 - 개인 구글 드라이브 : blog-0.0.1-SNAPSHOT.jar (아래링크에서 다운로드가능)
 - https://drive.google.com/file/d/1UxK4DnbsU9DGnhUZYuhp3kVwPPZNs1XD/view?usp=share_link

제약사항
  - JAVA 17 기반
  - Spring Boot 사용
  - Gradle 기반의 프로젝트
  - 블로그 검색 API는 서버(백엔드)에서 연동 처리
  - DB는 인메모리 DB인 h2 를 사용하였으며,  DB 컨트롤은 JPA로 구현함.
  - 외부 라이브러리 및 오픈소스 사용
      - jquery, bootstrap 사용, 목적은 front-end 관련 화면 개발용도
