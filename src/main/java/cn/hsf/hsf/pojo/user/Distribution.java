package cn.hsf.hsf.pojo.user;


import cn.hsf.hsf.controller.user.UserOrder;

import java.util.Date;

public class Distribution {

    private Integer id;
    private Integer releaseId;
    private Integer statusId;
    private String refusedMessage;
    private Date createTime;
    private Date updateTime;
    private Integer sfId;
    private Integer orderId;

    private UserRelease userRelease;
    private DistributionStatus status;
    private UserOrder userOrder;

    public Distribution() {
    }



    public Distribution(Integer releaseId, Integer statusId) {
        this.releaseId = releaseId;
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Distribution{" +
                "id=" + id +
                ", releaseId=" + releaseId +
                ", statusId=" + statusId +
                ", refusedMessage='" + refusedMessage + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sfId=" + sfId +
                ", orderId=" + orderId +
                ", userRelease=" + userRelease +
                ", status=" + status +
                ", userOrder=" + userOrder +
                '}';
    }

    public UserRelease getUserRelease() {
        return userRelease;
    }

    public void setUserRelease(UserRelease userRelease) {
        this.userRelease = userRelease;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Integer releaseId) {
        this.releaseId = releaseId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getRefusedMessage() {
        return refusedMessage;
    }

    public void setRefusedMessage(String refusedMessage) {
        this.refusedMessage = refusedMessage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSfId() {
        return sfId;
    }

    public void setSfId(Integer sfId) {
        this.sfId = sfId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public DistributionStatus getStatus() {
        return status;
    }

    public void setStatus(DistributionStatus status) {
        this.status = status;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
