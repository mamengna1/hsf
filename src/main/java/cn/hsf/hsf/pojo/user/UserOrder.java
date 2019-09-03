package cn.hsf.hsf.pojo.user;

import java.util.Date;

/**
 * @author kaituozhe
 */
public class UserOrder {

    private Integer id;
    private String comments;
    private Integer starLevel;
    private Integer commentTypeId;
    private Date commentTime;
    private Integer userId;
    private Integer sfId;

    public UserOrder() {
    }

    public UserOrder(Integer userId, Integer sfId) {
        this.userId = userId;
        this.sfId = sfId;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
                ", starLevel=" + starLevel +
                ", commentTypeId=" + commentTypeId +
                ", commentTime=" + commentTime +
                ", userId=" + userId +
                ", sfId=" + sfId +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSfId() {
        return sfId;
    }

    public void setSfId(Integer sfId) {
        this.sfId = sfId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public Integer getCommentTypeId() {
        return commentTypeId;
    }

    public void setCommentTypeId(Integer commentTypeId) {
        this.commentTypeId = commentTypeId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
