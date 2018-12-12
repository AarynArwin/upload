package com.chengnanhuakai.upload.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResultVO
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/12/11 16:33
 * @Version 1.0
 */
@Data
@ApiModel(value = "统一响应体")
public class ResultVO<T> implements Serializable {

    @ApiModelProperty(value = "访问接口的成功标识")
    private Boolean success;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "http 响应码")
    private Integer responseCode;

    @ApiModelProperty(value = "响应数据")
    private T data;

    public ResultVO() {
    }

    public ResultVO(Boolean success, String errorMsg, Integer responseCode, T data) {
        this.success = true;
        this.errorMsg = errorMsg;
        this.responseCode = responseCode;
        this.data = data;
    }
}
