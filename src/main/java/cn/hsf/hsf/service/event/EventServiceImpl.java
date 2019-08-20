package cn.hsf.hsf.service.event;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.mapper.app.AppMenuMapper;
import cn.hsf.hsf.mapper.user.UserMapper;
import cn.hsf.hsf.pojo.app.AppMenu;
import cn.hsf.hsf.pojo.message.BaseMessage;
import cn.hsf.hsf.pojo.message.TextMessage;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.service.app.WXService;
import cn.hsf.hsf.service.message.MessageService;
import cn.hsf.hsf.service.user.UserService;
import cn.hsf.hsf.utils.ImageUtil;
import cn.hsf.hsf.utils.WxUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author kaituozhe
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private AppMenuMapper appMenuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private WXService wxService;
    @Autowired
    private MessageService messageService;

    /**
     * 处理click菜单
     *
     * @param requestMap
     * @return
     */
    @Override
    public BaseMessage dealClickEvent(Map<String, String> requestMap) {
        AppMenu appMenu = appMenuMapper.selByKey(requestMap.get("EventKey"));
        if (appMenu == null || appMenu.getMessage() == null || appMenu.getMessage().equals("")) {
            return new TextMessage(requestMap, "建设中");
        }
        return new TextMessage(requestMap, appMenu.getMessage());
    }

    /**
     * 用户跳转
     *
     * @param requestMap
     * @return
     */
    @Override
    public BaseMessage dealViewEvent(Map<String, String> requestMap) {
        return null;
    }

    /**
     * 用户已关注
     *
     * @param requestMap
     * @return
     */
    @Override
    public BaseMessage dealScanEvent(Map<String, String> requestMap) {
        return null;
    }

    /**
     * 用户关注
     *
     * @param requestMap
     * @param flag
     * @return
     */
    @Override
    public Integer dealSubEvent(Map<String, String> requestMap, boolean flag) {

        User user = userMapper.selUserByOpenId(requestMap.get("FromUserName"));

        // 根据openId获取用户基本信息
        String fromUserName = userService.getUserInfo(requestMap.get("FromUserName"));
        Map<String, Object> params = JSON.parseObject(fromUserName);


        // 用于标识用户是否扫描带参数的二维码
        String EventKey = requestMap.get("EventKey");
        if (EventKey != null && EventKey.equals("") && flag) {
            EventKey = EventKey.substring(EventKey.indexOf("_") + 1);
            System.out.println("EVENT_KEY : " + EventKey);
            EventKey = EventKey.equals("null") ? null : EventKey;
        }
        User param = new User((String) params.get("nickname"), (String) params.get("openid"), (Integer) params.get("sex"), (String) params.get("headimgurl"), (String) params.get("country"),
                (String) params.get("province"), (String) params.get("city"));
        System.out.println("PARAM " + param);
        // 第一次关注
        if (user == null) {
            userMapper.insUser(param);
            System.out.println("添加完成 ： " + param);

            String ticket = wxService.getQrCodeTicket(param.getOpenId());
            ImageUtil.uploadImage(WxConstants.GET_EWM_URL + ticket, param.getId() + "");
            // 赠送卡卷

        } else {
            if (user.getDetailId() == 0) {
                userMapper.updUser(param);
            }
        }
        // 不为空 需要给  扫码用户 和 推荐用户发送模板消息
        if (EventKey != null && !EventKey.equals("null") && !EventKey.equals("") && flag) {
            // 给扫码用户发模板
            messageService.sendSubTemplateMessage(user, true);

            // 给推荐用户发模板
            user.setOpenId(EventKey);
            messageService.sendSubTemplateMessage(user, false);
        } else if (flag) {
            // 为空的时候只需要给扫码用户发送模板消息
            messageService.sendSubTemplateMessage(user, true);
        }
        return user.getId();
    }

    /**
     * 用户取消关注
     *
     * @param requestMap
     * @return
     */
    @Override
    public BaseMessage dealUnSubEvent(Map<String, String> requestMap) {
        return null;
    }
}
