package cn.hsf.hsf.pojo.user;

import java.util.Date;

/**
 * @author kaituozhe
 */
public class UserScoreSource {

    private Integer id;
    private String openId;
    private Double score;
    private Integer scoreSourceId;
    private String sourceOpenId;
    private Date createDate;

    private ScoreSourceType type;

    public UserScoreSource() {
    }

    public UserScoreSource(String openId, Double score, Integer scoreSourceId, String sourceOpenId) {
        this.openId = openId;
        this.score = score;
        this.scoreSourceId = scoreSourceId;
        this.sourceOpenId = sourceOpenId;
    }

    @Override
    public String toString() {
        return "UserScoreSource{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", score=" + score +
                ", scoreSourceId=" + scoreSourceId +
                ", sourceOpenId='" + sourceOpenId + '\'' +
                ", createDate=" + createDate +
                ", type=" + type +
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getScoreSourceId() {
        return scoreSourceId;
    }

    public void setScoreSourceId(Integer scoreSourceId) {
        this.scoreSourceId = scoreSourceId;
    }

    public String getSourceOpenId() {
        return sourceOpenId;
    }

    public void setSourceOpenId(String sourceOpenId) {
        this.sourceOpenId = sourceOpenId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ScoreSourceType getType() {
        return type;
    }

    public void setType(ScoreSourceType type) {
        this.type = type;
    }
}
