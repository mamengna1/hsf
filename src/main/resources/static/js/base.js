function clean() {
    $("#headImg").attr("src", "");
    $("#userName").html("");
    $("#address").html("");
    $("#sex").html("");
    $("#userType").html("");
    $("#phone").html("");
}


function formatDate(date) {
    // 日期转换
    var da = new Date(date).toJSON();
    return new Date(+new Date(da)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
}



