package cn.hsf.hsf.pojo;

public class AccessTocken {
    private String accessTockem;
    // 过期时间
    private long expireTime;

    public String getAccessTockem() {
        return accessTockem;
    }

    public void setAccessTockem(String accessTockem) {
        this.accessTockem = accessTockem;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessTocken(String accessTockem, String expireIn) {
        this.accessTockem = accessTockem;
        //系统时间 + 有效期 =过期时间
        expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn) * 1000;
    }

    /**
     * 判断token是否过期
     *
     * @return
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }
}
