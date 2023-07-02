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

var IMP = window.IMP;
IMP.init("imp67282556");

function requestPay() {
    var radioVal = $('input[name="payWith"]:checked').val();
    var amount = Number(document.getElementById("result").innerText)
    console.log(amount)
    console.log(radioVal + "콘솔의 값")

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
    let full_address = address+" "+address_sub;
    let order_req = $("#order_req").val();
    let today = new Date();
    let isoDate = today.toISOString();
    let user_id= document.getElementById("u_id").value;
    if (order_req.length<=0){
        order_req = "조심히 안전하게 와주세요";
    }
    console.log(order_req)

    if (radioVal === '무통장 입금') {

    } else {
        IMP.request_pay(
            {

                pg: radioVal,		//pg파라미터 값
                pay_method: "card",		//결제 방법
                merchant_uid: "machimmo_order_"+order_id,//주문번호
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
                                type:"post",
                                url:'/payment/success',
                                contentType:"application/json",
                                data: JSON.stringify({
                                      order_id : order_id,
                                      order_price : rsp.paid_amount,
                                      product_id_list:product_id_list,
                                      order_req:order_req,
                                      created_at:isoDate,
                                      updated_at:null,
                                      order_address:address,
                                      order_address_sub:address_sub,
                                      user_id:user_id
                                  })
                                ,success:function (){
                                    alert("결제 성공")
                                    location.href="/order/complete?user_id="+user_id+"&order_id="+order_id;

                                }
                                ,error:function (){
                                    alert("결제정보 저장중 오류가 발생하였습니다.")

                                }

                            })

                        } else {

                            alert("결제 실패");
                              $.ajax({
                                  type: "post",
                                  url: '/pay/cancel',
                                  contentType:"application/json",
                                  data: JSON.stringify({
                                      imp_uid : rsp.imp_uid,
                                      merchant_uid : order_id,
                                      amount : rsp.paid_amount,
                                      product_list:product_list
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