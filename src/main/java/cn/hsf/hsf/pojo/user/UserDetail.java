package cn.hsf.hsf.pojo.user;

import java.util.List;

/**
 * @author kaituozhe
 */
public class UserDetail {

    private Integer id;
    private String name;
    private String card;
    private Integer placeProvince;
    private Integer placeCity;
    private Integer placeArea;
    private String address;
    private String cardOne;
    private String cardTwo;
    private String skills;
    private Integer workProvince;
    private Integer workCity;
    private Integer workArea;
    private Integer status;
    private Integer lineStatus;
    private Integer yearWorkId;
    private UserYearWork userYearWork;

    private Integer totalOrder;
    private Integer totalRefuse;

    private List<UserSkills> skillList;

    public UserDetail() {
    }

    public UserDetail(Integer id, Integer totalOrder, Integer totalRefuse) {
        this.id = id;
        this.totalOrder = totalOrder;
        this.totalRefuse = totalRefuse;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", placeProvince=" + placeProvince +
                ", placeCity=" + placeCity +
                ", placeArea=" + placeArea +
                ", address='" + address + '\'' +
                ", cardOne='" + cardOne + '\'' +
                ", cardTwo='" + cardTwo + '\'' +
                ", skills='" + skills + '\'' +
                ", workProvince=" + workProvince +
                ", workCity=" + workCity +
                ", workArea=" + workArea +
                ", status=" + status +
                ", lineStatus=" + lineStatus +
                ", yearWorkId=" + yearWorkId +
                ", userYearWork=" + userYearWork +
                ", totalOrder=" + totalOrder +
                ", totalRefuse=" + totalRefuse +
                ", skillList=" + skillList +
                '}';
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Integer getTotalRefuse() {
        return totalRefuse;
    }

    public void setTotalRefuse(Integer totalRefuse) {
        this.totalRefuse = totalRefuse;
    }

    public List<UserSkills> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<UserSkills> skillList) {
        this.skillList = skillList;
    }

    public Integer getYearWorkId() {
        return yearWorkId;
    }

    public void setYearWorkId(Integer yearWorkId) {
        this.yearWorkId = yearWorkId;
    }

    public UserYearWork getUserYearWork() {
        return userYearWork;
    }

    public void setUserYearWork(UserYearWork userYearWork) {
        this.userYearWork = userYearWork;
    }

    public Integer getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Integer lineStatus) {
        this.lineStatus = lineStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Integer getPlaceProvince() {
        return placeProvince;
    }

    public void setPlaceProvince(Integer placeProvince) {
        this.placeProvince = placeProvince;
    }

    public Integer getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(Integer placeCity) {
        this.placeCity = placeCity;
    }

    public Integer getPlaceArea() {
        return placeArea;
    }

    public void setPlaceArea(Integer placeArea) {
        this.placeArea = placeArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardOne() {
        return cardOne;
    }

    public void setCardOne(String cardOne) {
        this.cardOne = cardOne;
    }

    public String getCardTwo() {
        return cardTwo;
    }

    public void setCardTwo(String cardTwo) {
        this.cardTwo = cardTwo;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Integer getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(Integer workProvince) {
        this.workProvince = workProvince;
    }

    public Integer getWorkCity() {
        return workCity;
    }

    public void setWorkCity(Integer workCity) {
        this.workCity = workCity;
    }

    public Integer getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Integer workArea) {
        this.workArea = workArea;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
