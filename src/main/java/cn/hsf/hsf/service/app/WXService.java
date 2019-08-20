package cn.hsf.hsf.service.app;

import cn.hsf.hsf.pojo.app.AppMenu;

import java.util.List;
import java.util.Map;

/**
 *  一些对接微信的接口
 */
public interface WXService {

    /**
     *  接入微信  验证签名
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    boolean check(String timestamp, String nonce, String signature);

    /**
     *  查询所有APP菜单
     * @return
     */
    List<AppMenu> selAllMenu(Integer parentMenuId);

    /**
     *  用户操作请求
     * @param requestMap
     * @return
     */
    String getResponse(Map<String, String> requestMap);

    /**
     *  获取带参数二维码的ticket
     * @param openId
     * @return
     */
    String getQrCodeTicket(String openId);
}
