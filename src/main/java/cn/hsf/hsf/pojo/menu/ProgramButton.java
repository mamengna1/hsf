package cn.hsf.hsf.pojo.menu;

/**
 * @author kaituozhe
 */
public class ProgramButton extends AbstractButton {
    private String type = "miniprogram";
    private String url;
    private String appid;
    private String pagepath;

    public ProgramButton(String name) {
        super(name);
    }
    public ProgramButton(String name, String url, String appid, String pagepath) {
        super(name);
        this.type = type;
        this.url = url;
        this.appid = appid;
        this.pagepath = pagepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }


}
