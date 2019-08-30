function receiving() {
    var releaseId = $("#releaseId").val();
    alert(releaseId);
    $.ajaxSettings.async = false;
    // 验证是否被人接过了
    $.getJSON("/_api/isReceiving", {"releaseId": releaseId}, function (res) {
        // 还没有人接单 可以 接单
        if (res) {
            alert("目前还没有人接单");
            var id = $("#id").val();
            var sfId = $("#sfId").val();
            alert(id);
            $.getJSON("/_api/receiving", {
                "id": id,
                "releaseId": releaseId,
                "statusId": 2,
                "sfId": sfId
            }, function (result) {
                // 订单已近被人接了
                if (!result) {
                    alert("订单已不存在");
                    location.href = "/_api/goOrderList";
                } else {
                    alert("跳转列表");
                    location.href = "/_api/goOrderShow?id=" + parseInt(id);
                }
            })
        } else {
            alert("订单已不存在");
            location.href = "/_api/goOrderList";
        }
    })
    $.ajaxSettings.async = true;
}

function jujue() {
    var message = prompt("请输入拒单原因");
    if (message) {
        var releaseId = $("#releaseId").val();
        var id = $("#id").val();
        $.getJSON("/_api/turnDown", {
            "id": id,
            "releaseId": releaseId,
            "statusId": 3,
            "refusedMessage": message
        }, function (res) {
            if (res) {
                alert("已拒单");
                location.href = "/_api/goOrderShow?id=" + parseInt(id);
            }
        })
    }
}

/**
 *  取消订单
 */
function callOff() {

    if (confirm("您确定要取消订单吗?")) {
        var refusedMessage = prompt("取消订单原因（选填，点击确定执行下一步）");
        if (refusedMessage) {
            var id = $("#id").val();
            var releaseId = $("#releaseId").val();
            $.getJSON("/_api/callOf", {
                "id": id,
                "releaseId": releaseId,
                "statusId": 5,
                "refusedMessage": refusedMessage
            }, function (res) {
                if (res) {
                    alert("已取消");
                    location.href = "/_api/goOrderShow?id=" + parseInt(id);
                }
            })
        }
    }

}

function comple() {
    var id = $("#id").val();
    var releaseId = $("#releaseId").val();
    $.getJSON("/_api/comple", {"id": id, "releaseId": releaseId, "statusId": 7}, function (res) {
        if (res) {
            alert("已申请");
            location.href = "/_api/goOrderShow?id=" + parseInt(id);
        }
    })
}

/**
 *  用户评论
 */
function comment() {
    var releaseId = $("#releaseId").val();
    var comments = $("#comments").val();
    var userOrderId = $("#userOrderId").val();
    var id = $("#id").val();

    $.getJSON("/_api/comment", {
        "releaseId": releaseId,
        "comments": comments,
        "id": userOrderId,
        "statusId": 6,
        "disId": id
    }, function (res) {
        if (res) {
            alert("已完工");
            location.href = "/_api/goOrderShow?id=" + parseInt(id);
        }
    })
}