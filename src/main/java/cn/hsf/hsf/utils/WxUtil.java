package cn.hsf.hsf.utils;

import cn.hsf.hsf.commons.ImageURL;
import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.pojo.AccessTocken;
import cn.hsf.hsf.pojo.app.App;
import cn.hsf.hsf.pojo.message.*;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaituozhe
 */
public class WxUtil {

    private static App app;
    // 用于存储token
    private static AccessTocken accessTocken;

    /**
     * sha1加密
     *
     * @param str
     * @return
     */
    public static String sha1(String str) {
        try {
            // 获取一个加密对象  【此处若想从、使用MD5加密，只需要传入MD5即可】
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(str.getBytes());
            // 16进制
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            // 处理加密结果
            StringBuilder builder = new StringBuilder();
            for (byte b : digest) {
                // & 15 = 00001111   获取高4位的值
                builder.append(chars[(b >> 4) & 15]);
                // 获取低四位的值
                builder.append(chars[b & 15]);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取token  【暴露给外部的】
     *
     * @return
     */
    public static String getAccessToken() {
        // 如果为null  或者是过期了 就从新获取一次
        if (accessTocken == null || accessTocken.isExpired()) {
            System.out.println("============= TOKEN ====================  生成一次 =======================");
            getToken();
        }
        return accessTocken.getAccessTockem();
    }

    /**
     * 获取token
     */
    private static void getToken() {
        String url = WxConstants.GET_TOKEN_URL.replace("APPID", WxConstants.APPID)
                .replace("APPSECRET", WxConstants.APPSECRET);
        String tokenStr = Send.get(url);
        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        // token
        String token = jsonObject.getString("access_token");
        // 有效时间  默认7200秒
        String expiresIn = jsonObject.getString("expires_in");

        // 创建token对象，并保存起来
        accessTocken = new AccessTocken(token, expiresIn);
    }

    /**
     * 解析XML数据包
     *
     * @param is
     * @return
     */
    public static Map<String, String> parseRequest(InputStream is) {

        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            // 读取输入流，获取文档对象
            Document document = reader.read(is);
            // 根据文档对象获取根节点
            Element root = document.getRootElement();
            // 获取根节点的所有子节点
            List<Element> elements = root.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把消息对象处理成xml数据包
     *
     * @param msg
     * @return
     */
    public static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        // 设置需要处理XStreamAlias("xml")注释的类
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        return stream.toXML(msg);
    }


    /**
     * 打印数据
     *
     * @param map
     */
    public static void print(Map map) {
        map.forEach((k, v) -> System.out.println(k + "  :  " + v));
    }

    /**
     * 时间格式转换
     * @param date
     * @param format
     * @return
     */
    public static String dateFormat(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }


    /**
     * 保存到服务器
     *
     * @param mediaId
     * @return
     */
    public static String saveImageToDisk(String mediaId, String saveUrlParent) {
        String filename = "";
        InputStream inputStream = getMediaStream(mediaId);
        byte[] data = new byte[1024];
        int len;
        FileOutputStream fileOutputStream = null;
        try {

            filename =  System.currentTimeMillis() + ".jpg";
            fileOutputStream = new FileOutputStream(saveUrlParent + File.separator + filename);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filename;
    }

    /**
     * 获取临时素材
     *
     * @param mediaId
     * @return
     */
    private static InputStream getMediaStream(String mediaId) {
        String url = WxConstants.GET_DATA_URL.replace("ACCESS_TOKEN", WxUtil.getAccessToken()).replace("MEDIA_ID", mediaId);
        InputStream is = null;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // GET方式请求
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            // 获取文件转化为byte流
            is = http.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
    /**
     * 保存到服务器
     *
     * @param mediaId
     * @return
     */
    public static String saveImageToDisk(String mediaId) {
        String filename = "";
        InputStream inputStream = getMediaStream(mediaId);
        byte[] data = new byte[1024];
        int len;
        FileOutputStream fileOutputStream = null;
        try {

            filename = System.currentTimeMillis() + ".jpg";
            fileOutputStream = new FileOutputStream(ImageURL.BACK_IMG_URL + File.separator + filename);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filename;
    }
}
