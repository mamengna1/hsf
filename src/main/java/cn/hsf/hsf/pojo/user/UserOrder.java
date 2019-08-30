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

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
                ", starLevel=" + starLevel +
                ", commentTypeId=" + commentTypeId +
                ", commentTime=" + commentTime +
                '}';
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
