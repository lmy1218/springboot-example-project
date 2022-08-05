package com.yang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yang
 * @version 1.0.0
 */
@AllArgsConstructor
@Getter
public enum ExampleTypeEnum {

    /** 开启 */
    ENABLE("开启"),

    ;

    private final String desc;

}
