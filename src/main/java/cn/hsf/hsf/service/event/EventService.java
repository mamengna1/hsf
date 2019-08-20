package cn.hsf.hsf.service.event;

import cn.hsf.hsf.pojo.message.BaseMessage;

import java.util.Map;

public interface EventService {

    /**
     * 用户点击事件
     */
    BaseMessage dealClickEvent(Map<String, String> requestMap);

    /**
     *  跳转
     * @param requestMap
     * @return
     */
    BaseMessage dealViewEvent(Map<String, String> requestMap);

    /**
     *  用户已关注
     * @param requestMap
     * @return
     */
    BaseMessage dealScanEvent(Map<String, String> requestMap);

    /**
     *  用户关注
     * @param requestMap
     * @param flag
     * @return
     */
    Integer dealSubEvent(Map<String, String> requestMap, boolean flag);

    /**
     *  用户取消关注
     * @param requestMap
     * @return
     */
    BaseMessage dealUnSubEvent(Map<String, String> requestMap);
}
