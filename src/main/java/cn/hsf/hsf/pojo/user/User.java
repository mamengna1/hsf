package cn.hsf.hsf.pojo.user;

import java.util.Date;

/**
 * @author kaituozhe
 */
public class User {

    private Integer id;
    private String nickName;
    private String openId;
    private Integer sex;
    private String headPic;
    private String country;
    private String province;
    private String city;
    private String phone;
    private Integer isSub;
    private Integer memberType;
    private String sourceType;
    private Double totalMoney;
    private Double blanceMoney;
    private Double totalScore;
    private Double balanceScore;
    private String userParent;
    private Date createDate;
    private Date lastLoginTime;
    private Integer detailId;

    private UserDetail userDetail;

    public User() {
    }

    public User(String nickName, String openId, Integer sex, String headPic, String country, String province, String city, String userParent) {
        this.nickName = nickName;
        this.openId = openId;
        this.sex = sex;
        this.headPic = headPic;
        this.country = country;
        this.province = province;
        this.city = city;
        this.userParent = userParent;
    }

    public User(String nickName, String openId, Integer sex, String headPic, String country, String province, String city) {
        this.nickName = nickName;
        this.openId = openId;
        this.sex = sex;
        this.headPic = headPic;
        this.country = country;
        this.province = province;
        this.city = city;
    }

    public User(String openId, String headPic) {
        this.openId = openId;
        this.headPic = headPic;
    }


    public User(String openId, String phone, Integer detailId) {
        this.openId = openId;
        this.phone = phone;
        this.detailId = detailId;
    }

    public User(String openId, Double balanceScore) {
        this.openId = openId;
        this.balanceScore = balanceScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", openId='" + openId + '\'' +
                ", sex=" + sex +
                ", headPic='" + headPic + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", isSub=" + isSub +
                ", memberType=" + memberType +
                ", sourceType='" + sourceType + '\'' +
                ", totalMoney=" + totalMoney +
                ", blanceMoney=" + blanceMoney +
                ", totalScore=" + totalScore +
                ", balanceScore=" + balanceScore +
                ", userParent='" + userParent + '\'' +
                ", createDate=" + createDate +
                ", lastLoginTime=" + lastLoginTime +
                ", detailId=" + detailId +
                ", userDetail=" + userDetail +
                '}';
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsSub() {
        return isSub;
    }

    public void setIsSub(Integer isSub) {
        this.isSub = isSub;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getBlanceMoney() {
        return blanceMoney;
    }

    public void setBlanceMoney(Double blanceMoney) {
        this.blanceMoney = blanceMoney;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getBalanceScore() {
        return balanceScore;
    }

    public void setBalanceScore(Double balanceScore) {
        this.balanceScore = balanceScore;
    }

    public String getUserParent() {
        return userParent;
    }

    public void setUserParent(String userParent) {
        this.userParent = userParent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }
}
