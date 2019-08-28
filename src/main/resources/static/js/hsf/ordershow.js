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
            $.getJSON("/_api/receiving", {"id": id, "releaseId": releaseId, "statusId": 2, "sfId":sfId}, function (result) {
                // 订单已近被人接了
                if (!result) {
                    alert("订单已不存在");
                    location.href = "/_api/goOrderList";
                } else {
                    location.href = "/_api/goOrderShow?id=" + id;
                }
            })
        } else {
            alert("订单已不存在");
            location.href = "/_api/goOrderList";
        }
    })
    $.ajaxSettings.async = true;
}
