package cn.hsf.hsf.pojo.message;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    private String mediaId;


    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgType("image");
        this.mediaId = mediaId;
    }
}
