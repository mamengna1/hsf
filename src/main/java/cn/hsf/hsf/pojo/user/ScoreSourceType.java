package cn.hsf.hsf.pojo.user;

/**
 * @author kaituozhe
 */
public class ScoreSourceType {

    private Integer id;
    private String sourceName;

    @Override
    public String toString() {
        return "ScoreSourceType{" +
                "id=" + id +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
