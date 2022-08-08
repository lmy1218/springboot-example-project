package com.yang.mq.handler;

import com.yang.mq.annotation.EnableResult;
import com.yang.mq.exception.BizException;
import com.yang.mq.pojo.vo.ResultVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Yang
 * @version 1.0.0
 * @description 统一返回值处理
 * @createTime 2022年08月05日 17:50:00
 */
@RestControllerAdvice(annotations = EnableResult.class)
public class ResultAdviceHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().equals(ResultVo.class);
    }


    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType,
                                              Class<? extends HttpMessageConverter<?>> aClass,
                                              ServerHttpRequest serverHttpRequest,
                                              ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVo<>(data));
            } catch (JsonProcessingException e) {
                throw new BizException();
            }
        }
        // 将原本的数据包装在ResultVO里
        return new ResultVo<>(data);
    }
}
