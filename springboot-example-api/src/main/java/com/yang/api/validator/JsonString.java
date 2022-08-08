package com.yang.api.validator;


import com.yang.api.validator.impl.JsonStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author wangsiqian
 *
 * @version 1.0.0
 * @date 2022-01-19
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = JsonStringValidator.class)
public @interface JsonString {
    String message() default "无效的 JSON 字符串";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
