# project_machimo(마이너한 취미 모음집)

## 작업일지
<table>
  <thead>
    <tr>
      <th>날짜</th>
      <th>작성자</th>
      <th>Done(작업내용)</th>
      <th>To DO(해야할 작업)</th>
      <th>이슈사항</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="5">06월 16일</td>
      <td>이진영</td>
      <td>
        1. 총괄 <br>
        2. 회의록 작성 <br>
        3. 로그인 로직 설계 구성도 작성 <br>
        4. 네이버 로그인 API 사용법 숙지 <br>
        5. 일정계획 수립 및 타임라인 작성
      </td>
      <td>
        1. ERD 마무리 보조 <br>
        2. 스프링부트 공부 <br>
        3. 클래스 설계
      </td>
      <td></td>
    </tr>
    <tr>
      <td>조수현</td>
      <td>
        1. ERD피드백 <br>
        2. 스토리보드 설계서 작성
      </td>
      <td>
        1. 스프링부트 공부<br>
        2. 부트스트랩 공부<br>
        3. 마이페이지 구현
      </td>
      <td></td>
    </tr>
    <tr>
      <td>최규하</td>
      <td>
        <pre>
          1. 경매 프로세스 구성 완료
          2. 경매 erd 추가
        </pre>
      </td>
      <td>
        <pre>
          1. 게시글 등록 구성도 끝내야함
          2. 스프링 부트
          3. ERD 마무리보조 클래스설계
          4. 콜백함수 조사
          5. 게시글 구성 작성중
        </pre>
      </td>
      <td></td>
    </tr>
    <tr>
      <td>박기현</td>
      <td>
        <pre>
          1.ERD 데이터 타입 변경 및 수정
          2.ERD 관계도 수정
          3.ERD 1차 정규화
        </pre>
      </td>
      <td>
        <pre>
          1.ERD마무리
          2.스프링부트
        </pre>
      </td>
      <td></td>
    </tr>
    <tr>
      <td>조현지</td>
      <td>
        <pre>
          1.디자인 시안 제작
          2.메인 페이지 구상
          3.nav , footer  구현
        </pre>
      </td>
      <td>
        <pre>
          1. 메인 페이지 구현
          2. 로그인 페이지
          3. 상품상세페이지 등 프론트 설계
          4. 스프링부트 공부
        </pre>
      </td>
      <td></td>
    </tr>
  </tbody>
</table>













## 230708 1차 병합
jolocal의 Dto :PageDto -> LocalPageDto 로 변경 후 수동 리펙터링 처리

### 1차 병합 이슈
configuration Bean 충돌
- "/basket" 컨트롤러 이름 중복으로 충돌 발생 : mypage/basket의 컨트롤러 "basketView" 로 변경및 수동 리펙터링 처리

jomuragi, park / categoryDto 충돌
- jomiragi가 categoryVO로 수정하여 수동 리펙터링 처리

jomuragi, park / ShopService 충돌
- 알고보니 class 파일 충돌이라 out/ 경로에 있는 class 파일 삭제

gyuha 컨트롤러 문제
- 알고보니 규하께 문제가 아닌 jomuragi,park 의 shopDao 가 충돌이 일어났던 것

