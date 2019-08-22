package cn.hsf.hsf.pojo.user;

import java.util.Date;

/**
 * @author kaituozhe
 */
public class UserInformation {

    private Integer id;
    private String openId;
    private String content;
    private String images;
    private Integer lookTotal;
    private Date createDate;

    @Override
    public String toString() {
        return "UserInformation{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", conent='" + content + '\'' +
                ", images='" + images + '\'' +
                ", lookTotal=" + lookTotal +
                ", createDate=" + createDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getLookTotal() {
        return lookTotal;
    }

    public void setLookTotal(Integer lookTotal) {
        this.lookTotal = lookTotal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
