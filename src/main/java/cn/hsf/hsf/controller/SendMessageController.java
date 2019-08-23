package cn.hsf.hsf.controller;

import cn.hsf.hsf.pojo.Result;
import cn.hsf.hsf.pojo.sms.SendMessage;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.service.sms.SendMessageService;
import cn.hsf.hsf.service.user.UserService;
import config.AppConfig;
import lib.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import utils.ConfigLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public boolean sendMessage(String phone, Boolean isDef, HttpSession session) throws Exception {
        // 验证手机号是有已绑定其他账号
        if (existsPhone(phone) && !isDef) {
            return false;
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
        session.setAttribute("code", code + "");
        session.setAttribute("phone", phone);
        return true;
    }

    @RequestMapping("/editUser")
    public Boolean addUserPhone(HttpServletRequest request, String phone){
        return userService.updUser(new User((String) request.getSession().getAttribute("openId"), phone,null)) > 0 ;
    }

    @ResponseBody
    @RequestMapping("/isExist")
    public Boolean isExist(String phone){
        return existsPhone(phone);
    }

    /**
     *  验证码 手机判断
     * @param phone
     * @param code
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/isCode")
    public Result isCode(String phone, String code, HttpSession session){
        String oldPhone = (String) session.getAttribute("phone");
        String oldCode = (String) session.getAttribute("code");
        System.out.println("code" + code + " oldCode" + oldCode);
        if (oldCode == null || oldPhone == null) {
            return new Result("请先获取验证码", false);
        } else if (!oldPhone.equals(phone)) {
            return new Result("请勿更改手机号", false);
        } else if (!oldCode.equals(code)) {
            return new Result("验证码不正确", false);
        } else {
            return new Result(null, true);
        }
    }

    /**
     *  验证手机号是否存在
     * @param phone
     * @return
     */
    private Boolean existsPhone(String phone){
        return sendMessageService.selByPhone(phone) > 0;
    }

}
