package cn.zyt.springbootlearning.vo;

import lombok.Data;

/**
 * @author yitian
 */
@Data
public class ResultVO {
    private Boolean success;
    private String message;

    public ResultVO() {}

    public ResultVO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
