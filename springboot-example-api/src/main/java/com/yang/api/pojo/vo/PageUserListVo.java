package com.yang.api.pojo.vo;

import com.yang.api.pojo.dto.PagedQueryDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 17:25:00
 */
@Data
public class PageUserListVo extends PagedQueryDto {

    @ApiModelProperty(name = "id", value = "用户ID")
    private Long id;
    @ApiModelProperty(name = "account", value = "账号")
    private String account;
    @ApiModelProperty(name = "password", value = "密码")
    private String password;
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

}
