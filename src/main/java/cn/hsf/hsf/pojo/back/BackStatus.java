package cn.hsf.hsf.pojo.back;

/**
 * @author kaituozhe
 */
public class BackStatus {

    private Integer id;
    private String backStatusName;

    @Override
    public String toString() {
        return "BackStatus{" +
                "id=" + id +
                ", backStatusName='" + backStatusName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackStatusName() {
        return backStatusName;
    }

    public void setBackStatusName(String backStatusName) {
        this.backStatusName = backStatusName;
    }
}
