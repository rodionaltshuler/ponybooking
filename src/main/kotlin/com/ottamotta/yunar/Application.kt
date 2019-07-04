package com.ottamotta.yunar

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class Application {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .enableUrlTemplating(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ottamotta.yunar"))
                .paths(PathSelectors.any()).build()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}