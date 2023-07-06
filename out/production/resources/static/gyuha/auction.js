function clip() {

    var url = '';
    var textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    url = window.document.location.href;
    textarea.value = url;
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
    alert("URL이 복사되었습니다.")
}

$(document).ready(function () {
    $("#buyDirect").click(function (event) {
        event.preventDefault();

        $.ajax({
            url: "/order/checkSession",
            type: "get",

            success: function (response) {
                $("form[action='/order/buyDirect']").submit();
            },
            error: function () {
                alert("로그인이 필요한 서비스입니다.")
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
            error: function (xhr, status, error) {
                var response = JSON.parse(xhr.responseText);
                if (response.message == "로그인이 필요한 서비스입니다") {
                    alert(response.message);
                    document.getElementById("test1").click();
                 window.location.href = '/login/login';

                }
                alert(response.message);
                window.location.replace('http://localhost:8090/login') ;
            }
        });
    });
});

$(document).ready(function () {
    $("#report_btn").click(function () {

        let pid = $("#pid").val();
        let report_content = $("#report_content").val();
        let cancelBtn = $("#report_cancel");
        let seller_user_id = $("#seller").val()
        let user_id = $("#sss").val();

        console.log("세일러 아이디"+seller_user_id);

        $.ajax({
            url: "/report/send",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                user_id:user_id,
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

// $(window).on("load", function() {
//   let user_id =$("#sss").val();
//   let product_id = $("#pid").val();
//
//   $.ajax({
//     url: '/likes/check',
//     type: 'POST',
//     data: {
//       userId: user_id,
//       postId: product_id
//     },
//     success: function(response) {
//       if (response.liked) {
//
//       } else {
//         // "좋아요"가 눌리지 않은 경우, 해당 요소의 상태를 기본 상태로 둠
//       }
//     },
//     error: function(jqXHR, textStatus, errorThrown) {
//       console.error('Error: ' + textStatus + ' - ' + errorThrown);
//     }
//   });
// });

function checkbox(e) {
    let checkbox = $("#likebox")


    let pid = $("#pid").val();
    let user_id = $("#sss").val();
    if (user_id == "0") {
        checkbox.prop('checked', false);
        alert("회원만 가능합니다")
        return;
    }

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

        })
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
        })

    }

}

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