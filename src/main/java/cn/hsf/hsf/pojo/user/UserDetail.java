package cn.hsf.hsf.pojo.user;

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
                '}';
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
