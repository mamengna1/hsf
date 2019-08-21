package cn.hsf.hsf.controller;

import cn.hsf.hsf.pojo.sms.SendMessage;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.service.sms.SendMessageService;
import cn.hsf.hsf.service.user.UserService;
import config.AppConfig;
import lib.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ConfigLoader;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;


/**
 * @author kaituozhe
 */
@RestController
public class SendMessageController {

    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private UserService userService;

    @RequestMapping("/sendMessage")
    public String sendMessage(String phone, Boolean isDef) throws Exception {
        // 验证手机号是有已绑定其他账号
        int count = sendMessageService.selByPhone(phone);
        System.out.println("COUNT : " + count + "    FLAG" + isDef);
        if (count > 0 && !isDef) {
            return "false";
        }

        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
        MessageSend submail = new MessageSend(config);
        submail.addTo(phone);
        // 要发送的消息
        Integer code = (int) ((Math.random() * 900000 + 100000));
        String message = "【中蓝】您好，您的验证码是" + code;
        // 获取IP地址
        InetAddress addr = InetAddress.getLocalHost();
        String hostAddress = addr.getHostAddress();
        // 吧发送的消息数据存入数据库
        sendMessageService.insSms(new SendMessage(phone, code + "", message, hostAddress));
        // 给用户发送消息
        submail.addContent(message);
        submail.send();
        return code + "";
    }

    @RequestMapping("/editUser")
    public Boolean addUserPhone(HttpServletRequest request, String phone){
        Map params = new HashMap();
        params.put("openId", request.getSession().getAttribute("openId"));
        params.put("phone", phone);

        int count = userService.updUser(new User());

        return count > 0 ? true : false;
    }

}
