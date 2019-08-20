package cn.hsf.hsf.pojo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 回复视频消息
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    private String mediaId;
    private String title;
    private String description;

    public VideoMessage(Map<String, String> requestMap, String mediaId, String title, String description) {
        super(requestMap);
        this.setMsgType("video");
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
