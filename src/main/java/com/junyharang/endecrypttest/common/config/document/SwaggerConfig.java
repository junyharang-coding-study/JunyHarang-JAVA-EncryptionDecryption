package com.junyharang.endecrypttest.common.config.document;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * API Document Swagger 설정 Class
 */

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    /**
     * Swagger 문서 Type과 Document로 보여줄 API 설정
     */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                // Swagger 제공 기본 응답 메시지 표시 여부
                .useDefaultResponseMessages(true)
                .select()
                // Swagger API 탐색 대상 선택 (아래와 같이 설정 시 모든 API 탐색)
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * API 문서 제공, 내용, 설명 등 문서 정보를 위한 내용 정의
     */

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("주니하랑의 컴퓨터 가지고 놀기 😀")
                .description("JAVA Data 암/복호화 실습")
                .version("1.0.0")
                .build();
    }
}
