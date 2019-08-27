package cn.hsf.hsf.controller.user;

public class UserOrder {

    private Integer id;
    private String comments;

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
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
}
