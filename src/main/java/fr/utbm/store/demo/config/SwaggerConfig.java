package fr.utbm.store.demo.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("fr.utbm.store.demo.controller")
                .title("e-commerce")
                .description("mall后台相关接口文档")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
