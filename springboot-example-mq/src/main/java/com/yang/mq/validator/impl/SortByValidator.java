package com.yang.mq.validator.impl;


import com.yang.mq.validator.SortBy;
import cn.hutool.core.util.StrUtil;
import jodd.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Yang
 * @version 1.0.0
 * @description sort by validator
 * @createTime 2020年12月08日 15:23:00
 */
public class SortByValidator implements ConstraintValidator<SortBy, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isEmpty(value)) {
            // 允许为空
            return true;
        }

        String sortType;
        for (String field : value.split(",")) {
            String[] group = field.split(" ");
            if (group.length != 2) {
                return false;
            }

            // 验证 sort 字段
            if (StringUtil.isEmpty(group[0])) {
                return false;
            }
            // 验证 sort 类型
            sortType = group[1];
            if (!"DESC".equalsIgnoreCase(sortType) && !"ASC".equalsIgnoreCase(sortType)) {
                return false;
            }
        }

        return true;
    }
}
