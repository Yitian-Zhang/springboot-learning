package cn.zyt.springbootlearning.vo;

import lombok.Data;

@Data
public class CommonResult<T> {
    private Boolean success = false;
    private Integer msgCode = 0;
    private String msgInfo = null;
    private T data;

    public CommonResult() {}

    /**
     * 不带数据的返回结果
     */
    public CommonResult(Boolean success, Integer msgCode, String msgInfo) {
        this.success = success;
        this.msgCode = msgCode;
        this.msgInfo = msgInfo;
    }

    /**
     * 不带数据和code的返回结果
     */
    public CommonResult(Boolean success, String msgInfo) {
        this.success = success;
        this.msgInfo = msgInfo;
    }

    public CommonResult(Boolean success, String msgInfo, T data) {
        this.success = success;
        this.msgInfo = msgInfo;
        this.data = data;
    }

    /**
     * 带data和code返回结果
     */
    public CommonResult(Boolean success, Integer msgCode, String msgInfo, T data) {
        this.success = success;
        this.msgCode = msgCode;
        this.msgInfo = msgInfo;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "success=" + success +
                ", msgCode=" + msgCode +
                ", msgInfo='" + msgInfo + '\'' +
                ", data=" + data +
                '}';
    }
}
