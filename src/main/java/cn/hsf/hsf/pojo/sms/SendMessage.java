package cn.hsf.hsf.pojo.sms;

/**
 * @author kaituozhe
 */
public class SendMessage {

    private Integer id;
    private String phone;
    private String code;
    private String message;
    private String ip;
    private String sendDate;

    public SendMessage() {
    }

    public SendMessage(String phone, String code, String message, String ip) {
        this.phone = phone;
        this.code = code;
        this.message = message;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", ip='" + ip + '\'' +
                ", sendDate='" + sendDate + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}
