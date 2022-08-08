package com.yang.api.validator.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yang.api.validator.JsonString;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Yang
 * @version 1.0.0
 * @description JsonStringValidator
 * @createTime 2020年12月09日 14:24:00
 */
public class JsonStringValidator implements ConstraintValidator<JsonString, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isBlank(value)) {
            return true;
        }

        return JSONUtil.isJsonObj(value);
    }
}
