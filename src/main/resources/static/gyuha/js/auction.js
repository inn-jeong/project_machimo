function clip() {

    var url = '';
    var textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    url = window.document.location.href;
    textarea.value = url;
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
    alert("주소가 복사되었습니다.")
}

$(document).ready(function () {
    $("#buyDirect").click(function (event) {
        event.preventDefault();

        $.ajax({
            url: "/payment/checkSession",
            type: "post",

            success: function (response) {
                $("form[action='/order/buyDirect']").submit();
            },
            error: function (response) {
                alert(response.responseText)
                window.location.href = '/login/login';
            }
        })

    })


})

$(document).ready(function () {
    $("#compare").click(function (event) {
        event.preventDefault();

        var bidsInput = $('input[name="bids"]').val();
        var productIdInput = $('input[name="productId"]').val();
        var bidsHistoryInput = $('input[name="bidsHistory"]').prop('checked');
        var firstPriceInput = $('input[name="firstPrice"]').val();

        $.ajax({
            url: "/auction/auction-list/amountCheck",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                bids: bidsInput,
                productId: productIdInput,
                bidsHistory: bidsHistoryInput,
                firstPrice: firstPriceInput
            }),
            success: function (response) {
                alert("입찰성공")
                document.getElementById("test1").click()
                location.reload()

            },
            error: function (response) {
                if (response.responseText == "로그인이 필요한 서비스입니다") {
                    alert(response.responseText);
                    document.getElementById("test1").click();

                }

                window.location.replace('http://localhost:8090/login');
            }
        });
    });
});

$(document).ready(function () {
    $("#report_btn").click(function () {
        let report_content = $("#report_content").val();
        if (report_content == null || report_content ==  ''){
            alert("내용을 입력해주세요")
            return 
        }

        let pid = $("#pid").val();
        let cancelBtn = $("#report_cancel");
        let seller_user_id = $("#seller").val()
        let user_id = $("#sss").val();

        console.log("세일러 아이디" + seller_user_id);

        $.ajax({
            url: "/report/send",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                user_id: user_id,
                product_id: pid,
                report_content: report_content,
                seller_user_id: seller_user_id


            }),
            success: function () {
                cancelBtn.click();
                alert("신고를 완료하였습니다.")
            },
            error: function (response) {
                cancelBtn.click();
                alert(response.responseText)
            }
        })

    })
});

$(document).ready(function () {


    $('#likebox').click(function (event) {

        event.preventDefault();

        let checkbox = $("#likebox");
        let pid = $("#pid").val();
        let user_id = $("#sss").val(); // 사용자 아이디 가져오기

        if (checkbox.prop('checked')) {
            console.log("체크 안 된 상태에서 누름")
            $.ajax({
                url: '/wishlists/insert',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify({
                    user_id: Number(user_id),
                    product_id: Number(pid)
                }),
                error: function (response) {
                    checkbox.prop('checked', false);
                    alert(response.responseText)
                }, success: function () {
                    checkbox.prop('checked', true);
                }
            });
        } else {
            console.log("체크 된 상태에서 누름")
            $.ajax({
                url: '/wishlists/delete',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify({
                    user_id: user_id,
                    product_id: pid
                })
                ,success:function (){
                    checkbox.prop('checked', false);
                }
            });
        }
    });
})

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
            window.location.href = '/login/login';
        }
    })
}