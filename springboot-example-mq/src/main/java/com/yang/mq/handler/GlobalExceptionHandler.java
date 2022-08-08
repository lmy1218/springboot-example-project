package com.yang.mq.handler;

import com.yang.mq.enums.EnumResultCode;
import com.yang.mq.exception.BizException;
import com.yang.mq.pojo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @version 1.0.0
 * @description 全局异常处理
 * @createTime 2022年08月05日 17:45:00
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 自定义业务异常BizException
     *
     * @author Yang
     * @date 2022/8/5 17:47
     * @param e: 异常
     * @return ResultVo<Object>
     **/
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo<Object> bizExceptionHandler(BizException e) {
        log.error("发生业务异常", e);
        return new ResultVo<>(EnumResultCode.FAILED, e.getMsg());
    }

    /**
     * 方法参数错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo<Object> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("发生方法参数错误异常", e);
        List<String> list=new ArrayList<>();
        // 从异常对象中拿到ObjectError对象
        if (!e.getBindingResult().getAllErrors().isEmpty()){
            for(ObjectError error:e.getBindingResult().getAllErrors()){
                list.add(error.getDefaultMessage().toString());
            }
        }
        // 然后提取错误提示信息进行返回
        return new ResultVo<>(EnumResultCode.VALIDATE_FAILED, list);
    }


}
