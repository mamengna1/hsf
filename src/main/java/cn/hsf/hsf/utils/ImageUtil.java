package cn.hsf.hsf.utils;


import cn.hsf.hsf.commons.ImageURL;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author kaituozhe
 */
public class ImageUtil {


    public static String saveImage(String path1, String path2, String fileName, String nickName) {
        String message01 = nickName;
        String message02 = "扫描下方二维码，关注领红包";
        String outPutPath = ImageURL.POSTERS_URL + fileName;
        overlapImage(path1, path2, message01, message02, outPutPath);
        return fileName;
    }

    /**
     * 解析图片地址
     *
     * @param path
     * @return
     */
    public static BufferedImage zhuanhuan(String path) {
        try {
            URL img = new URL(path);
            InputStream in = img.openStream();
            JPEGImageDecoder decoderFile = JPEGCodec.createJPEGDecoder(in);
            return decoderFile.decodeAsBufferedImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传图片到服务器  网络地址的图片  方法一
     *
     * @param path 网络地址图片
     * @return
     */
    public static String uploadImage(String path, String fileName) {
        try {
            URL url = new URL(path);
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", new File(ImageURL.USER_EWM_SAVE_URL + fileName + ".jpg"));
            InputStream is = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String uploadImage_2(String path, String fileName) {
        try {
            URL url = new URL(path);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File("D:\\software\\Tomcat\\tomcat\\webapps\\img\\quickCode\\" + fileName + ".jpg");
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 得到图片的二进制数据，以二进制封装得到数据，具有通用性
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    /**
     * 合成图片并保存地址
     *
     * @param backgroundPath
     * @param qrCodePath
     * @param message01
     * @param message02
     * @param outPutPath
     * @return
     */
    public static String overlapImage(String backgroundPath, String qrCodePath, String message01, String message02, String outPutPath) {
        try {
            //设置图片大小
//            BufferedImage background = resizeImage(618,1000, ImageIO.read(new File(backgroundPath)));
            BufferedImage background = resizeImage(400, 600, zhuanhuan(backgroundPath));
//            BufferedImage qrCode = resizeImage(150,150,ImageIO.read(new File(qrCodePath)));
            BufferedImage qrCode = resizeImage(150, 150, zhuanhuan(qrCodePath));
            //在背景图片中添加入需要写入的信息，例如：扫描下方二维码，欢迎大家添加我的淘宝返利机器人，居家必备，省钱购物专属小秘书！
            //String message = "扫描下方二维码，欢迎大家添加我的淘宝返利机器人，居家必备，省钱购物专属小秘书！";
            Graphics2D g = background.createGraphics();
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString(message01, 150, 240);
            g.drawString(message02, 150, 250);
            //在背景图片上添加二维码图片
            g.drawImage(qrCode, 150, 260, qrCode.getWidth(), qrCode.getHeight(), null);
            g.dispose();
//            ImageIO.write(background, "jpg", new File("这里是一个输出图片的路径"));
            ImageIO.write(background, "jpg", new File(outPutPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }
}
