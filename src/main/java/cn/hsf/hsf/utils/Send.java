package cn.hsf.hsf.utils;

import org.apache.http.impl.client.CloseableHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author kaituozhe
 */
public class Send {
    private static CloseableHttpClient httpClient;// HTTP请求器
    /**
     * 向指定的地址发送get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();

            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  像指定的地址发送一个post，并携带数据
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, String data){

        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            // 要发送数据出去，必须要设置为可发送数据状态
            connection.setDoOutput(true);
            // 获取流  写出数据
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            osw.write(data);
            osw.close();

            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder builder = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                builder.append(new String(b, 0, len));
            }
            return  builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
