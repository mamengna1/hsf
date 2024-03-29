package cn.hsf.hsf.pojo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 消息公共父类
 */
public class BaseMessage {

    /**
     * 接收方账号
     */
    @XStreamAlias("ToUserName")
    private String toUserName;

    /**
     * 开发者微信号
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间戳
     */
    @XStreamAlias("CreateTime")
    private String createTime;

    /**
     * 消息类型
     */
    @XStreamAlias("MsgType")
    private String msgType;

    public BaseMessage(String toUserName, String fromUserName, String createTime, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public BaseMessage(Map<String, String> requestMap) {
        this.toUserName = requestMap.get("FromUserName");
        this.fromUserName = requestMap.get("ToUserName");
        this.createTime = System.currentTimeMillis() / 1000 + "";
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
