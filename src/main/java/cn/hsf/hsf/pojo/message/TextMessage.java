package cn.hsf.hsf.pojo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 回复文本消息
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String, String> requestMap, String content) {
        super(requestMap);
        this.setMsgType("text");
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
