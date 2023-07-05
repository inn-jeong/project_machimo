
    //페이지 로딩 후 파라미터 값에 따라 알림창 출력


///////////////////////////카카오 스크립트///////////////////////////////////

Kakao.init('336fe318422138c11d889862658eb427'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
    //카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
        //카카오 로그인 성공 시
        success: function (response) {
            //카카오 개인정보 조회
            Kakao.API.request({
                url: '/v2/user/me',
                //개인정보 조회 성공 시
                success: function (response) {
                    console.log(response);
                    //ajax로 컨트롤러단에 데이터 전달하여 기존 회원인지 조회 후 분기 처리
                    $.ajax({
                        url: "/loginT/kakaoLogin_process",
                        type: "POST",
                        dataType: "text",
                        // processData:true,
                        contentType: "application/json; charset=UTF-8",
                        data: JSON.stringify(
                            {
                                usocial: response.id,
                                uemail: response.kakao_account.email,
                                unickname: response.kakao_account.profile.nickname
                            }
                        ),
                        success: function (res) {
                            if (res == "confirm") {
                                alert("로그인 성공!");
                                window.location.href = "/loginT/kakaoLogin_ok?login_ok=yes";
                            } else {
                                alert("가입된 계정이 없어 회원가입 화면으로 이동합니다.");
                                window.location.href = "/loginT/kakaoLogin_ok?login_ok=no";
                            }
                        },
                        error: function (e) {
                            alert("실패");
                        }
                    });//end error ajax
                },
                fail: function (error) {
                    console.log(error)
                },
            });//end kakao.API
        },
        fail: function (error) {
            console.log(error)
        },
    });//end kakao Auth
}

    //카카오로그아웃
function logout() {
    //카카오 SDK의 함수를 이용하여 토큰 삭제하여 로그아웃
    //다음 로그인 시에도 개인정보 제공 동의가 필요함
    if (Kakao.Auth.getAccessToken()) {
        Kakao.API.request({
            url: '/v1/user/unlink',
            success: function (response) {
            console.log(response);
                alert("로그아웃 하였습니다.");
            },
            fail: function (error) {
                console.log(error);
            }
        })
        Kakao.Auth.setAccessToken(undefined);
    }
    //네이버는 로그아웃 url 호출하여 로그아웃
    //다음 로그인 시 개인정보 동의가 필요하지 않음
    if($("#naverUser").val() != null){
        naverLogout();
    }
    //바로 이동하면 closePopup 함수가 씹혀서 0.5초 뒤 이동
    setTimeout(function() {
        window.location.href="/loginT/logout";
    }, 500);
    // window.location.href="/loginT/logout";
}


///////////////////////////////로그아웃하기 위한 과정//////////////////////////////////////
var testPopUp;
//팝업 창을 만들어 거기에 네이버 계정을 로그아웃하게 하는 url을 입력함
function openPopUp() {
    //네이버 사이트에서 로그아웃하는 url
    testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
    //testPopUp= window.open("https://accounts.kakao.com/logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
}
function closePopUp(){
    testPopUp.close();
}

//위 기능들을 사용해 네이버 계정을 로그아웃하는 기능 구현
function naverLogout() {
    openPopUp();
    setTimeout(function() {
        closePopUp();
    }, 100);
    // closePopUp();
}

function findPassword(){
    window.open("/loginT/findPassword_page","pop","width=570,height=420, scrollbars=no, resizable=no");
}

function  findId(){
    window.open("/loginT/findId_page","pop","width=570,height=420, scrollbars=no, resizable=no");
}
