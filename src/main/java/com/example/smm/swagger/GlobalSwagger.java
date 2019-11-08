package com.example.smm.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author lidongwei
 * @date 2019-11-08
 **/
public class GlobalSwagger implements WebMvcConfigurer {

    protected boolean enabled(){
        return  true;
    }

    protected String title(){
        return "接口文档";
    }

    protected String description(){
        return "接口描述";
    }

    protected String version(){
        return "1.0";
    }

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title(title())
                .description(description())
                .termsOfServiceUrl("")
                .version(version())
                .build();
    }

    /**
     * swagger ui资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * swagger-ui.html路径映射，浏览器中使用/api-docs访问
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api-docs", "/swagger-ui.html");
    }

}
