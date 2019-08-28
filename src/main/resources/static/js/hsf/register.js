$(function () {
    var skills = new Array();
    $(".skills").click(function () {
        var len = $(".skills").filter(":checked").length;
        if (len > 3) {
            if ($(this).prop('checked')) {
                return false;
            }
        }
        if ($(this).prop('checked')) {
            skills.push($(this).attr("sid"));
        } else {
            skills.splice(jQuery.inArray($(this).attr("sid"), skills), 1);
        }
        $("#skills").val(skills.join());
    })
});

/**
 * 获取路径参数
 * @param name
 * @returns {string|null}
 */
function getUrlParam(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

/**
 * 获取用户openId
 * @param code
 */
function getOpenId(code) {
    $.getJSON("/_api/getUserInfo", {"code": code}, function (data) {
        if (data.detailId != 0 && (data.userDetail.status == 2 || (data.userDetail.status == 1 && flag))) {
            $("#name").val(data.userDetail.name);
            $("#card").val(data.userDetail.card);
            $("#address").val(data.userDetail.address);

            var skills = (data.userDetail.skills).split(",");
            for (var i = 0; i < skills.length; i++) {
                $("#skill" + skills[i]).prop("checked", "checked");
            }
            $("#skills").val(skills.join());
            $("#yearWorkId").val(data.userDetail.yearWorkId);

            $("#card1").attr("src", data.userDetail.cardOne);
            $("#cardOne").val(data.userDetail.cardOne);
            $("#card2").attr("src", data.userDetail.cardTwo);
            $("#cardTwo").val(data.userDetail.cardTwo);
            $("#phone").val(data.phone);
            if ($("#phone").val() != '' && $("#phone").val() != undefined && $("#phone").val() != null) {
                $("#phone").attr("readonly", "true");
            }
            $("#status").val(data.userDetail.status);
            $("#id").val(data.userDetail.id);

            chooseProvince(data.userDetail.placeProvince);
            $("#placeProvince").val(data.userDetail.placeProvince);
            chooseCity(data.userDetail.placeCity);
            $("#placeCity").val(data.userDetail.placeCity);
            $("#placeArea").val(data.userDetail.placeArea);

            chooseProvince2(data.userDetail.workProvince);
            $("#workProvince").val(data.userDetail.workProvince);
            chooseCity2(data.userDetail.workCity);
            $("#workCity").val(data.userDetail.workCity);
            $("#workArea").val(data.userDetail.workArea);

            if (data.userDetail.status == 1) {
                $("#name").attr("disabled", true);
                $("#card").attr("disabled", true);
                $("#card1").attr("disabled", true).css("pointer-events", "none");
                $("#card2").attr("disabled", true).css("pointer-events", "none");
            }
        } else if (data.userDetail.status == 1 && !flag){

        } else if (data.detailId != 0 && (data.userDetail.status == 0 || data.userDetail.status == 3) && (data.userDetail.message == null || data.userDetail.message == '')) {
            location.href = "/_api/goAwait";
        } else {
            $("#phone").val(data.phone);
            if ($("#phone").val() != '' && $("#phone").val() != undefined && $("#phone").val() != null) {
                $("#phone").attr("readonly", "true");
            }
        }
    })

}

var bindCount = 0;

function checkForm() {

    // 身份证验证
    var card = $("#card").val();
    var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (!regIdNo.test(card)) {
        alert("身份证格式不正确");
        return false;
    }
    var city = $("#placeCity").val();
    if (city == -1) {
        alert("请选择完整的常住地址");
        return false;
    }

    var address = $("#address").val();
    if (address == '') {
        alert("请输入详细地址");
        return false;
    }

    var len = $(".skills").filter(":checked").length;
    if (len == 0) {
        alert("请选择至少一种 工种");
        return false;
    }
    var education = $("#education").val();
    if (education == -1) {
        alert("选中从业年限");
        return false;
    }

    var yzm = $("#yzm").val();
    var phone = $("#phone").val();
    var status = $("#status").val();
    $.ajaxSettings.async = false;
    if (bindCount < 3) {
        var flag;
        $.getJSON("/_api/isCode", {"phone": phone, "code": yzm}, function (res) {
            if (!res.flag && status != 2) {
                alert(res.message);
                flag = false;
            } else {
                bindCount++;
                flag = true;
            }
        });
        return flag;
    } else {
        alert("多次输入不正确请重新获取");
        return false;
    }
    $.ajaxSettings.async = true;
}
