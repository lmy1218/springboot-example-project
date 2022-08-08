package com.yang.api.pojo.dto;

import com.yang.api.validator.SortBy;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author wangsiqian
 * @version 1.0.0
 * @date 2022-01-19
 */
@Data
public class PagedQueryDto {
    @SortBy
    @ApiModelProperty(value = "按字段排序，例：createTime desc,updateTime asc")
    protected String sortBy;

    @Min(value = 1, message = "页码不能小于1")
    @ApiModelProperty(value = "开始页码")
    protected Integer page = 1;

    @Min(value = 1, message = "分页不能小于1")
    @ApiModelProperty(value = "数据条数")
    protected Integer limit = 10;

    @ApiModelProperty(value = "返回的字段，例：id,createTime,updateTime")
    private String fields;
}
