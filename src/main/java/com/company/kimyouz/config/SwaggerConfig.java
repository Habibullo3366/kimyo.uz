package com.company.kimyouz.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedUserApi() {
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedCardApi() {
        return GroupedOpenApi.builder()
                .group("Card")
                .pathsToMatch("/card/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedBasketApi() {
        return GroupedOpenApi.builder()
                .group("Basket")
                .pathsToMatch("/basket/**")
                .build();
    }
    @Bean
    public GroupedOpenApi groupedCategoryApi() {
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToMatch("/category/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOrdersItemApi() {
        return GroupedOpenApi.builder()
                .group("Order-Item")
                .pathsToMatch("/ordersItem/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedPaymentApi() {
        return GroupedOpenApi.builder()
                .group("Payment")
                .pathsToMatch("/payment/**")
                .build();
    }
    @Bean
    public GroupedOpenApi groupedProductApi() {
        return GroupedOpenApi.builder()
                .group("Product")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOrdersApi() {
        return GroupedOpenApi.builder()
                .group("Orders")
                .pathsToMatch("/orders/**")
                .build();
    }

}
