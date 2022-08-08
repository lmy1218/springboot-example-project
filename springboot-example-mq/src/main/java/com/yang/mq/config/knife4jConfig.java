package com.yang.mq.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Yang
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class knife4jConfig {

    @Bean
    public Docket createClientApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName("客户端接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yang.mq.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("基于Spring Boot的后端业务服务")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .contact(new Contact("Yang","www.lmy1218.icu","1569851867@qq.com"))
                .build();
    }
}
