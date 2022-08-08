package com.yang.api.dao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Yang
 * @version 1.0.0
 * @description 用户
 * @createTime 2022年08月05日 17:16:00
 */
@Data
@TableName("api_user")
public class ApiUser {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String account;
    private String password;
    private String email;
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}
