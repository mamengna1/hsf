package cn.hsf.hsf.pojo;

/**
 * @author kaituozhe
 */
public class Result {

    private Integer errCode;
    private String message;
    private Boolean flag;

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }

    public Result(String message, Boolean flag) {
        this.message = message;
        this.flag = flag;
    }

    public Result(Integer errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public Result(Integer errCode, String message, Boolean flag) {
        this.errCode = errCode;
        this.message = message;
        this.flag = flag;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
