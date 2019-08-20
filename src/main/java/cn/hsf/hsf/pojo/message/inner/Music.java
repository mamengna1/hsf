package cn.hsf.hsf.pojo.message.inner;

public class Music {

    private String title;

    private String description;

    private String musicUrl;
    // 高质量音乐链接
    private String hqMusicUrl;
    // 缩略图的媒体id
    private String thumbMediald;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediald() {
        return thumbMediald;
    }

    public void setThumbMediald(String thumbMediald) {
        this.thumbMediald = thumbMediald;
    }
}
