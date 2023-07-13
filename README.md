# project_machimo(마이너한 취미 모음집)

# 작업일지
## 230616
### Done
<table>
  <tr>
    <td>test</td>
    <td>test2</td>
  </tr>
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

