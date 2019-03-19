package com.piers.template.data.response;


import lombok.Data;

/**
 * Controller的基础返回类
 *
 * @author piers
 * @date 19-3-11 下午1:41
 */
@Data
public class BaseResponse<T> {
    /**
     * 是否成功
     */
    int resultCode;

    /**
     * 说明
     */
    String resultMsg;

    /**
     * 返回数据
     */
    T data;

    public BaseResponse() {
    }

    public BaseResponse(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
