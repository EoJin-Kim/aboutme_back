package com.zeroback.aboutme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    /**
     * Swagger를 위한 Docket 빈을 추가한다.
     *
     * @return
     */
    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("About Me API")
                .description("<h3>About Me 에서 사용되는 RestApi에 대한 문서를 제공한다.</h3>")
                .contact(new Contact("Zero-Back", "https://zero-back.com", "zero@zero.com"))
                .license("MIT License")
                .version("1.01")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)        // Swagger 2.0 기반의 문서 작성
                .apiInfo(apiInfo)                             // 문서에 대한 정보를 설정한다.
                .select()                                    // ApiSelectorBuilder를 반환하며 상세한 설정 처리
                .apis(RequestHandlerSelectors.basePackage("com.zeroback.aboutme"))// 대상으로하는 api 설정
                .paths(PathSelectors.any())  // controller에서 swagger를 지정할 대상 path 설정)
                .build();                                    // Docket 객체 생성
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**","/static/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/","classpath:/static/");
    }
}