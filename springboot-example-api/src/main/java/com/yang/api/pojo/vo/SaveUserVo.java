package com.yang.api.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 18:04:00
 */
@Data
public class SaveUserVo {

    @ApiModelProperty(name = "account", value = "账户", required = true)
    @NotNull(message = "账户不能为空")
    @Size(min = 6, max = 11, message = "账号长度在6-11之间")
    private String account;

    @ApiModelProperty(name = "password", value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度在6-11之间")
    private String password;

    private String email;

}
