package cn.hsf.hsf.service.message;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaituozhe
 */
@Service
public class MessageServiceImpl implements MessageService {
    /**
     * 订单支付模板
     *
     * @param map
     */
    @Override
    public void sendTemplateMessage(Map<String, String> map) {
        String at = WxUtil.getAccessToken();
        System.out.println(at);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + at;
        String data = "{\n" +
                "           \"touser\":\"" + map.get("openId") + "\",\n" +
                "           \"template_id\":\"UEG-m6y0s-9bJOCvUKSIOXkuPWwhdCWD9PAKYXu8bb8\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"您的订单已支付成功。\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"无名氏\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"" + map.get("orderId") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\"" + map.get("other") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\": {\n" +
                "                       \"value\":\"星冰乐（焦糖味） 家乐氏香甜玉米片*2 乐天七彩爱情糖*3\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"如有问题请致电中蓝客服热线400-8070028或直接在微信留言，客服在线时间为工作日10:00——18:00.客服人员将第一时间为您服务\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = Send.post(url, data);
        System.out.println(result);
    }

    Map<String, String> map = new HashMap<>();

    /**
     * 新用户添加提醒
     *
     * @param user
     */
    @Override
    public void sendSubTemplateMessage(User user, boolean flag) {
        map.put("openId", user.getOpenId());
        map.put("title", flag ? "您的昵称是【" + user.getNickName() + "】,已于" + WxUtil.dateFormat(user.getCreateDate(), WxConstants.DATE_YY) + "成为【中蓝】第" + user.getId() + "位会员，感谢您的支持" : "有新会员加入");
        map.put("id", user.getId() + "");
        map.put("date", WxUtil.dateFormat(user.getCreateDate(), WxConstants.DATE_YY));
        map.put("name", flag ? "您已获得两张优惠卷 和 10 积分，可在个人中心查看" : "微信昵称：" + user.getNickName() + "  请继续努力！！");
        String at = WxUtil.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + at;
        String data = "{\n" +
                "           \"touser\":\"" + map.get("openId") + "\",\n" +
                "           \"template_id\":\"c58cj1d1Eh52NujEYv1-TrWyqu8SdujkVIFA4Act1BY\",\n" +
                "           \"url\":\"http://www.baidu.com\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + map.get("title") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + map.get("id") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"" + map.get("date") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"" + map.get("name") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = Send.post(url, data);
        System.out.println(result);
    }

    /**
     * 关联用户消费  得积分
     *
     * @param map
     */
    @Override
    public void sendSalesTemplateMessage(Map<String, String> map) {
        String at = WxUtil.getAccessToken();
        System.out.println(at);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + at;
        String data = "{\n" +
                "           \"touser\":\"" + map.get("openId") + "\",\n" +
                "           \"template_id\":\"YGq-noIOHzA1JQwHBETy4eB2CrPOA9SJ6vfE__at060\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"亲，您又成功分销出一笔订单了\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + map.get("orderId") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"" + map.get("other") + "元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\"" + map.get("money") + "元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\": {\n" +
                "                       \"value\":\"" + map.get("date") + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"【" + map.get("name") + "】感谢有您，客服热线：8888888\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = Send.post(url, data);
        System.out.println(result);
    }
}
