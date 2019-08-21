package cn.hsf.hsf.pojo.user;

/**
 * @author kaituozhe
 */
public class UserYearWork {

    private Integer id;
    private String yearName;

    @Override
    public String toString() {
        return "UserYearWork{" +
                "id=" + id +
                ", yearName='" + yearName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }
}
