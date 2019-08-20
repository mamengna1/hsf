package cn.hsf.hsf.pojo.app;


/**
 * @author kaituozhe
 */
public class App {

    private Integer id;
    private String appId;
    private String mchId;
    private String appSecret;
    private String token;
    private String key;


    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", mchId='" + mchId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", token='" + token + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
