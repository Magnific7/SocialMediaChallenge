package com.scalablescripts.socialMediaRestAPI.configs

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.service.ApiKey

@Configuration
@SecurityScheme(
    name = SwaggerConfig.BEARER_AUTH,
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)

@OpenAPIDefinition(
    info = Info(
        title = "Social Media Challange ",
        description = "API for a social media application",
        contact = Contact(
            name = "Uwikuzo Marie Magnificat",
            url = "https://www.linkedin.com/in/uwikuzo-marie-magnificat-907b57192/",
            email = "uwikuzo7@gmail.com"),
        license = License(
            name = "Github",
            url = "https://github.com/Magnific7/SocialMediaChallenge"
        )
    )
)
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.scalablescripts.socialMediaRestAPI.controllers"))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(listOf(apiKey()))
    }

    private fun apiKey(): ApiKey {
        return ApiKey("Authorization", "Authorization", "header")
    }

    companion object {
        const val BEARER_AUTH = "bearerAuth"
    }

}