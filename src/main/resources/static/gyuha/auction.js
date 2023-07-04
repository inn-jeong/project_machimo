$(".btn-link").click(function () {
    const url = encodeURI(window.location.href);


})

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
                    return window.location.href = 'http://localhost:8090/login';

                }
                alert(response.message);
            }
        });
    });
});

$(document).ready(function () {
    $("#report_btn").click(function () {

        let pid = $("#pid").val();
        let report_content = $("#report_content").val();


        $.ajax({
            url: "/report/send",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                product_id: pid,
                report_content: report_content

            }),
            success: function () {
                alert("신고를 완료하였습니다.")
            },
            error: function (response) {
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
