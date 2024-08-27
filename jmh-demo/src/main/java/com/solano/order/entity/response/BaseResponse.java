package com.solano.order.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author solano
 * @date 2024/7/23 19:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private Integer code;
    private String message;
    private static final BaseResponse SUCCESS = new BaseResponse(200, "success");
    private static final BaseResponse ERROR = new BaseResponse(500, "服务开小差啦!");

    public static BaseResponse success() {
        return SUCCESS;
    }

    public static BaseResponse error() {
        return ERROR;
    }

    public static BaseResponse error(String msg) {
        return new BaseResponse(500, msg);
    }

}
