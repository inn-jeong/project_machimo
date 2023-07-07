function t() {
    console.log("동작잘함")

}

$(document).ready(function () {
    console.log("돔 준비됨")
    $('.modal-body').on("click", '#alert a', function (e) {
        e.preventDefault();
        console.log('a태그가 클릭되었습니다.');
        let alert_id = $(this).data('value');
        console.log(alert_id + "의 값");

        let val = $("#alertRes").val();
        console.log(val)


        $.ajax({

            url: "/alert/delete",
            type: "post",
            data: {
                id: alert_id
            },
            success: function () {
                console.log("삭제됨")
                $('[id^=alert' + alert_id + ']').remove();
                if ((val - 1) == 0) {
                    $('[id=alerts]').remove()

                } else {
                    $("#alNum").text(val - 1);
                    $("#alertRes").val(val - 1);
                    if ($("#alNum").text()=="10"){
                        $("#alNum").text(9+);
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
});