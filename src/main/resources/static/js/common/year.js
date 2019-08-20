
//定义年、月、日方法函数
window.onload = function(){
    strYYYY = document.myform.YYYY.outerHTML;
    strMM = document.myform.MM.outerHTML;
    strDD = document.myform.DD.outerHTML;
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

//先给年下拉框赋内容
    var y = new Date().getFullYear();
    var str = strYYYY.substring(0, strYYYY.length - 9);
    for (var i = (y); i < (y+30); i++) //以今年为准，，后30年 (前30年 只需要 var i = (y -30))
    {
        str += "<option value='" + i + "'> " + i + "</option>\r\n";
    }
    document.myform.YYYY.outerHTML = str +"</select>";

//赋月份的下拉框
    var str = strMM.substring(0, strMM.length - 9);
    for (var i = 1; i < 13; i++)
    {
        str += "<option value='" + i + "'> " + i + "</option>\r\n";
    }
    document.myform.MM.outerHTML = str +"</select>";

    document.myform.YYYY.value = y;
    document.myform.MM.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
    writeDay(n); //赋日期下拉框
    document.myform.DD.value = new Date().getDate();
}
function YYYYMM(str) //年发生变化时日期发生变化(主要是判断闰平年)
{
    var MMvalue = document.myform.MM.options[document.myform.MM.selectedIndex].value;
    if (MMvalue == ""){DD.outerHTML = strDD; return;}
    var n = MonHead[MMvalue - 1];
    if (MMvalue ==2 && IsPinYear(str)) n++;
    writeDay(n)
}
//月发生变化时日期联动
function MMDD(str) {
    var YYYYvalue = document.myform.YYYY.options[document.myform.YYYY.selectedIndex].value;
    if (str == ""){DD.outerHTML = strDD; return;}
    var n = MonHead[str - 1];
    if (str ==2 && IsPinYear(YYYYvalue)) n++;
    writeDay(n)
}
//据条件写日期的下拉框
function writeDay(n) {
    var s = strDD.substring(0, strDD.length - 9);
    for (var i=1; i<(n+1); i++)
        s += "<option value='" + i + "'> " + i + "</option>\r\n";
    document.myform.DD.outerHTML = s +"</select>";
}
//判断是否闰平年
function IsPinYear(year){
    return(0 == year%4 && (year%100 !=0 || year%400 == 0))

}