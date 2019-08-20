package cn.hsf.hsf.service.app;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.mapper.app.AppMapper;
import cn.hsf.hsf.mapper.app.AppMenuMapper;
import cn.hsf.hsf.pojo.app.App;
import cn.hsf.hsf.pojo.app.AppMenu;
import cn.hsf.hsf.pojo.message.BaseMessage;
import cn.hsf.hsf.service.event.EventService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author kaituozhe
 */
@Service
public class WXServiceImpl implements WXService {

    @Autowired
    private AppMapper appMapper;
    @Autowired
    private AppMenuMapper appMenuMapper;
    @Autowired
    private EventService eventService;

    /**
     * 验证签名  接入微信
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    @Override
    public boolean check(String timestamp, String nonce, String signature) {
        App app = appMapper.selapp();
        // 1、 将token、timestamp、nonce三个参数进行字典排序
        String[] strs = new String[]{app.getToken(), timestamp, nonce};
        Arrays.sort(strs);

        // 2、 将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0] + strs[1] + strs[2];
        String mysig = WxUtil.sha1(str);
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * 查询所有的菜单列表
     * @param parentMenuId
     * @return
     */
    @Override
    public List<AppMenu> selAllMenu(Integer parentMenuId) {
        return appMenuMapper.selAll(parentMenuId);
    }

    /**
     *  处理用户操作
     * @param requestMap
     * @return
     */
    @Override
    public String getResponse(Map<String, String> requestMap) {
        requestMap.forEach((k, v) -> System.out.println(k + "  " + v));
        BaseMessage msg = null;
        String msgType = requestMap.get("MsgType");
        switch (msgType) {
            case "text":
                // 处理文本消息
                // msg = messageService.dealTextMessage(requestMap);
                break;
            case "image":
                //处理图文
                // msg = dealImage(requestMap);
                break;
            case "voice":
                break;
            case "video":
                break;
            case "music":
                break;
            case "news":
                break;
            case "event":
                msg = dealEvent(requestMap);
                break;
            default:
                break;
        }
        // 把消息对象处理为xml数据包
        return WxUtil.beanToXml(msg);
    }

    /**
     * 处理事件推送
     *
     * @param requestMap
     * @return
     */
    private BaseMessage dealEvent(Map<String, String> requestMap) {

        String event = requestMap.get("Event");
        switch (event) {
            case "CLICK":
                return eventService.dealClickEvent(requestMap);
            case "VIEW":

                return null;
            case "SCAN":
                // 已关注 操作
                return null;
            case "subscribe":
                // 关注 操作
                eventService.dealSubEvent(requestMap, true);
                return null;
            case "unsubscribe":
                // 取消关注操作
                return null;
            default:
                break;
        }
        return null;
    }

    /**
     *  获取带参数二维码的ticket
     * @param openId
     * @return
     */
    @Override
    public String getQrCodeTicket(String openId) {
        String accessToken = WxUtil.getAccessToken();
        String url = WxConstants.GET_EWN_TICKET_URL + accessToken;
        // 生成临时字符二维码
        // expire_seconds ：二维码有效时间  action_name ：二维码类型
        // action_info ：二维码详细信息   scene_str ：场景值ID字符串形式，长度限制为1到64
        String data = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + (openId == null ? null : openId) + "\"}}}";
        System.out.println("生成二维码的数据 ：" + data);
        String result = Send.post(url, data);
        System.out.println("生成二维码的结果 ：" + result);
        return JSONObject.fromObject(result).getString("ticket");
    }





}
