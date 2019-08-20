package cn.hsf.hsf.pojo.app;


/**
 * @author kaituozhe
 */
public class AppMenu {

    private Integer id;
    private String menuName;
    private Integer menuTypeId;
    private Integer parentMenuId;
    private String message;
    private String key;

    private AppMenuType appMenuType;

    @Override
    public String toString() {
        return "AppMenu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuTypeId=" + menuTypeId +
                ", parentMenuId=" + parentMenuId +
                ", message='" + message + '\'' +
                ", key='" + key + '\'' +
                ", appMenuType=" + appMenuType +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(Integer menuTypeId) {
        this.menuTypeId = menuTypeId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AppMenuType getAppMenuType() {
        return appMenuType;
    }

    public void setAppMenuType(AppMenuType appMenuType) {
        this.appMenuType = appMenuType;
    }
}
