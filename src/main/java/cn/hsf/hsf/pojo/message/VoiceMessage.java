package cn.hsf.hsf.pojo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 回复语音消息
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{
    private String mediaId;

    public VoiceMessage(Map<String, String> requestMap,String mediaId) {
        super(requestMap);
        this.setMsgType("voice");
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
