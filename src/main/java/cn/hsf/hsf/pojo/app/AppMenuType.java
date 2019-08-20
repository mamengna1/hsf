package cn.hsf.hsf.pojo.app;

/**
 * @author kaituozhe
 */
public class AppMenuType {

    private Integer id;
    private String typeName;
    private Integer urlOrMessage;

    @Override
    public String toString() {
        return "AppMenuType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", urlOrMessage=" + urlOrMessage +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getUrlOrMessage() {
        return urlOrMessage;
    }

    public void setUrlOrMessage(Integer urlOrMessage) {
        this.urlOrMessage = urlOrMessage;
    }
}
