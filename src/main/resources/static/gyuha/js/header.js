function t() {
    console.log("동작잘함")
}

$(document).ready(function () {

    if ($("#logout").val() == 'yes') {
        location.href = "/";
    }
    console.log("돔 준비됨");
    $("#logout_btn1").on("click", function (e) {
        e.preventDefault();
        location.href = "/loginT/logout";
        // window.location.reload();
    });
    $('.modal-body').on("click", '#alert a', function (e) {
        e.preventDefault();
        console.log('a태그가 클릭되었습니다.');
        let alert_id = $(this).data('value');
        console.log(alert_id + "의 값");

        let val = $("#alertRes").val();
        console.log(val)

        let text = Number($("#alNum").text().replaceAll('+', ''));

        console.log("text val == >" + text);


        $.ajax({
            url: "/alert/delete",
            type: "post",
            data: {
                id: alert_id
            },
            success: function () {
                console.log("삭제됨")
                $('[id=alert' + alert_id + ']').remove();
                if ((val - 1) == 0) {
                    $('[id=alerts]').remove()

                } else {

                    if (val > 10) {
                        console.log("여기왔음 ")
                        $("#alNum").text("9+");
                        $("#alertRes").val(val - 1);
                    } else {
                        $("#alNum").text(val - 1);
                        $("#alertRes").val(val - 1);
                    }
                }

            },
            error: function () {
                console.log("삭제안됨")
            }

        })
    });

    $("#remove_all").on('click', function () {
        $.ajax({
            url: "/alert/del-all",
            type: "post",
            success: function () {
                $("#al_All").remove();
                $("#alerts").remove();


            },
            error: function () {

            }
        })
    });

    $("#search").on('click', function () {
        let keyword = $("#keyword").val();

        if (keyword) {
            $("#frm").submit();
        } else {
            return;
        }


    })
        $("a[name='al_hr']").on("click", function (e) {
        e.preventDefault();
        let val1 = $("#h-al").val();
        console.log("동작함")
        $.ajax({
            url: "alert/test",
            contentType: "application/json",
            type: 'POST',
            data: JSON.stringify({
                id: val1
            }),
            success: function () {
                window.location.href = "/mypage/mypage_page";
            }
        });
    });

});
function add_basket() {
    let pid = $("#pid").val();
    let user_id = $("#sss").val();
    console.log(user_id)
    console.log(pid)
    $.ajax({

        url: "/basket/addBasket",
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            user_id: user_id,
            product_id: pid
        }),
        success: function () {
            alert("장바구니에 추가되었습니다.")
        },
        error: function (response) {
            alert(response.responseText)
        }
    })
}