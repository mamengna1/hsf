package cn.hsf.hsf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //String格式的数据转化成Date格式,Date格式转化成String格式
    static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat fmt= new SimpleDateFormat("yyyy-MM-dd");
    /**
     * String  类型转换为  date
     * @param str
     * @return
     */
    public  static  Date StrToDate(String str){
        Date date = null;//Date格式
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    /**
     * String  类型转换为  date
     * @param str
     * @return
     */
    public  static  Date StrToDate2(String str){
        Date date = null;//Date格式
        try {
            date = fmt.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * String  类型转换为  date
     * @param date
     * @return
     */
    public static String DateToStr(Date date){

        String datestr= formatter.format(date);//String格式
        return datestr;
    }

    /**
     * 时间转换
     * @param l
     * @return
     */
    public static String tranfDate(Long l){
        //new日期对象
        Date date = new Date(l);
        String str = formatter.format(date);
        return  str;

    }
}
