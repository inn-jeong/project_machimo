function all_use_point() {

    const result = Number(document.getElementById('result').innerText);
    if(result ===0){
        return;
    }
    var use_point = document.getElementById("user_point").innerText

    document.getElementById('used_point').innerText = document.getElementById("u_point").value
    console.log(use_point)
    if (use_point>result){
     use_point = result
    document.getElementById('user_point').innerText = use_point
    }else {
        document.getElementById('user_point').innerText = 0
    }
    console.log(result)
    document.getElementById('result').innerText = Number(result - use_point);




}

function onClickCheckboxValue() {

    const query = 'input[name="amount"]:checked';
    const selectedEls =
        document.querySelectorAll(query);


    let result = 0;
    selectedEls.forEach((el) => {
        result += Number(el.value);
    });


    document.getElementById('result').innerText
        = result;
}


window.onload = function () {

    const query = 'input[name="amount"]:checked';
    const selectedEls =
        document.querySelectorAll(query);

    let result = 0;
    selectedEls.forEach((el) => {
        result += Number(el.value);
    });

    document.getElementById('result').innerText
        = result;


};
var used_point = 0;

function points() {

    const user_point = document.getElementById("user_point").innerText;
    if (user_point==0){
        return;
    }
    const point = document.getElementById("point").value
    console.log("사용할려는 포인트" + point)
    console.log("가지고 있는 포인트" + user_point)

    if (point > user_point) {
        alert("적용가능한 포인트보다 큽니다")
        return;
    }


    const result = Number(document.getElementById('result').innerText);
    document.getElementById('result').innerText = Number(result - point);

    document.getElementById("user_point").innerText = Number(user_point - point)

    used_point = (used_point + Number(point))
    console.log("사용한 포인트" + used_point)

    document.getElementById("used_point").innerText = used_point.toLocaleString('ko-kr')

}


var IMP = window.IMP;
IMP.init("imp67282556");

function requestPay() {
    var radioVal = $('input[name="payWith"]:checked').val();
    var amount = Number(document.getElementById("result").innerText)
    console.log(amount)
    console.log(radioVal + "콘솔의 값")

    let used_point_result = document.getElementById("used_point").innerText
    console.log("총 사용한 포인트는 =  = = " + used_point_result)
    var p_name = null;

    let p_names = document.getElementsByClassName("p_name");
    let product_ids = document.getElementsByClassName("product_Id");

    var product_id_list = [];
    if (p_names.length >= 2) {
        for (let i = 0; i < product_ids.length; i++) {
            product_id_list.push(product_ids[i].value);
        }
        p_name = p_names[0].value + "외 " + (p_names.length - 1) + " 건"


    } else {
        p_name = p_names[0].value;
        product_id_list.push(product_ids[0].value)
    }

    let order_id = document.getElementById("orderId").value
    let phone = document.getElementById("u_phone").value;
    let email = document.getElementById("u_email").value;
    let post_code = document.getElementById("u_addr_postcode").value;
    let name = document.getElementById("u_name").value;
    let address = document.getElementById("u_address").value;
    let address_sub = document.getElementById("u_address_sub").value;
    let full_address = address + " " + address_sub;
    let order_req = $("#order_req").val();
    let today = new Date();
    let isoDate = today.toISOString();
    let user_id = document.getElementById("u_id").value;

    if (order_req.length <= 0) {
        order_req = "조심히 안전하게 와주세요";
    }
    console.log(order_req)

    var json = {
        order: order_id,
        order_req: order_req,
        amount: amount
    }

    console.log("제이슨 데이터" + JSON.stringify(json))

    if (radioVal === '무통장 입금') {

    } else {
        IMP.request_pay(
            {

                pg: radioVal,		//pg파라미터 값
                pay_method: "card",		//결제 방법
                merchant_uid: "machimm" + 10,//주문번호
                name: p_name,		//상품 명
                amount: amount,			//금액
                buyer_email: email,
                buyer_name: name,
                buyer_tel: phone,
                buyer_addr: full_address,
                buyer_postcode: post_code,


            },
            function (rsp) {

                if (rsp.success) {
                    //서버 검증 요청 부분
                    console.log(rsp.imp_uid)
                    console.log(rsp.merchant_uid)
                    console.log(rsp.paid_amount)
                    $.ajax({
                        type: "get",
                        url: '/payment/verify/' + rsp.imp_uid
                    }).done(function (data) {
                        console.log(data.response.amount)
                        if (rsp.paid_amount === data.response.amount) {
                            $.ajax({
                                type: "post",
                                url: '/payment/success',
                                contentType: "application/json",
                                data: JSON.stringify({
                                    order_id: order_id,
                                    order_price: rsp.paid_amount,
                                    product_id_list: product_id_list,
                                    order_req: order_req,
                                    created_at: isoDate,
                                    updated_at: null,
                                    order_address: address,
                                    order_address_sub: address_sub,
                                    user_id: user_id,
                                    used_point_result: used_point_result
                                })
                                , success: function () {
                                    alert("결제 성공")
                                    location.href = "/order/complete?user_id=" + user_id + "&order_id=" + order_id;

                                }
                                , error: function () {
                                    alert("결제정보 저장중 오류가 발생하였습니다.")

                                }

                            })

                        } else {

                            alert("결제 실패");
                            $.ajax({
                                type: "post",
                                url: '/pay/cancel',
                                contentType: "application/json",
                                data: JSON.stringify({
                                    imp_uid: rsp.imp_uid,
                                    merchant_uid: order_id,
                                    amount: rsp.paid_amount,
                                    product_list: product_list
                                })

                            })
                        }
                    })
                } else {
                    alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
                }
            }
        );
    }

}