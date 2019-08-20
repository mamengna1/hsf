package cn.hsf.hsf.pojo.user;

/**
 * @author kaituozhe
 */
public class UserSkill {

    private Integer id;
    private String skillName;

    public UserSkill() {
    }

    public UserSkill(Integer id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
