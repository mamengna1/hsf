var count = 0;
var time = 60; // 设置点击标记  防止60秒内再次点击
var flag = true;

var code;

// 验证手机号
function verify(phone) {
    if (!(/^1[3456789]\d{9}$/.test(phone))) {
        alert("手机号码有误，请重填");
        return false;
    }
    return true;
}

// 发送短信
function sendMessage() {
    var phone = $("#phone").val();
    var isDef = $("#phone").attr("readonly") == 'readonly' ? true : false;
    alert("ISDEF" + isDef);
    if (!verify(phone)) {
        return;
    }
    // 禁用
    $("#send").attr("disabled");
    if (count < 3) {
        // 必须是能点击的
        if (flag) {
            var timer = setInterval(function () {
                if (time == 60 && flag) {
                    flag = false;
                    $.getJSON("/_api/sendMessage", {"phone": phone, "isDef": isDef}, function (data) {
                        if (data == "false") {
                            $("#msg").html("该手机号已经绑定账号，请核对");
                            return;
                        }
                        count++;
                        // 验证码
                        code = data;
                        flag = false;
                        time = 60;
                    });
                } else if (time == 0) {
                    $("#send").removeAttr("disabled");
                    $("#send").val("免费获取验证码");
                    clearInterval(timer);
                    time = 60;
                    flag = true;
                } else {
                    $("#send").val(time + " s 重新发送");
                    time--;
                }
            }, 1000);
        }
    } else {
        alert("今日获取验证码操作已达上限");
    }
}