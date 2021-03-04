package com.lhj.config;

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

import java.util.HashSet;

/**
 * <p>
 * swagger页面展示的配置
 * </p>
 *
 * @author Toby
 * @since 2020/5/13
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        HashSet<String> contentType = new HashSet<>(1);
        contentType.add("application/json");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .consumes(contentType)
                .produces(contentType)
                .select()
                // 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lhj"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("确认收入-api文档")
                .description("确认收入-确认收入模块api文档")
                .version("1.0")
                .contact(new Contact("李洪健","","772147079@qq.com"))
                .build();
    }


}