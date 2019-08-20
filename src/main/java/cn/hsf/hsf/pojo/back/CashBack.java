package cn.hsf.hsf.pojo.back;

import java.util.Date;

/**
 * @author kaituozhe
 */
public class CashBack {

    private Integer id;
    private String openId;
    private Double money;
    private Date createDate;
    private Integer backStatusId;
    private String comment;

    private BackStatus backStatus;

    @Override
    public String toString() {
        return "CashBack{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", money=" + money +
                ", createDate=" + createDate +
                ", backStatusId=" + backStatusId +
                ", comment='" + comment + '\'' +
                ", backStatus=" + backStatus +
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getBackStatusId() {
        return backStatusId;
    }

    public void setBackStatusId(Integer backStatusId) {
        this.backStatusId = backStatusId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BackStatus getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(BackStatus backStatus) {
        this.backStatus = backStatus;
    }
}
