package com.yang.api.pojo.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 18:04:00
 */
@Data
public class SaveUserBo {
    private String account;
    private String password;
    private String email;

}
