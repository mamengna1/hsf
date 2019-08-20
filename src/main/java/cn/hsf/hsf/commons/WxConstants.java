package cn.hsf.hsf.commons;

/**
 * @author kaituozhe
 */
public class WxConstants {

    public static final String APPID = "wx8bf85bd98eaddb86";

    public static final String APPSECRET = "89ae850b9c5fb4dc50440a711367225f";


    /**
     * 时间格式
     */
    public static final String DATE_YY = "yyyy-MM-dd HH:mm";
    public static final String DATE_YEAR = "yyyy年MM月dd日 HH:mm";


    public static final String SAVE_IMG_URL = "http://java.86blue.cn/images/";

    public static final String SAVE_POSTER_URL = "http://java.86blue.cn/";

    /**
     * 获取Token的URL
     */
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     *
     */
    public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**
     *  拉取用户信息
     */
    public static String GET_BASE_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 获取用户信息的URL
     */
    public static String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 获取二维码的ticket
     */
    public static String GET_EWN_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
    /**
     * 获取二维码地址
     */
    public static final String GET_EWM_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
    /**
     * 创建菜单的URL
     */
    public static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 获取jspai ticket
     */
    public static String GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    /**
     * 获取临时素材
     */
    public static String GET_DATA_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

}
