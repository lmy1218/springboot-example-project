package com.yang.api.pojo.vo;


import lombok.Data;

import java.util.Date;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 17:25:00
 */
@Data
public class PageUserListResultVo {

    private Long id;
    private String account;
    private String password;
    private String email;
    private Boolean deleted;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;

}
