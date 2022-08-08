package com.yang.api.validator;



import com.yang.api.validator.impl.SortByValidator;

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
@Constraint(validatedBy = SortByValidator.class)
public @interface SortBy {
    String message() default "无效的排序字段（例: SortBy = title DESC,content DESC）";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
