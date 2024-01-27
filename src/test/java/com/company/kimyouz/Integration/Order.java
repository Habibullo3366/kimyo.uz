package com.company.kimyouz.Integration;

import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.repository.OrdersRepository;
import com.company.kimyouz.service.mapper.OrdersMapper;
import com.company.kimyouz.service.validation.OrdersValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Order {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*@Autowired
    private OrdersRepository ordersRepository;

    @MockBean
    private OrdersMapper ordersMapper;

    @MockBean
    private OrdersValidation ordersValidation;*/

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres= new PostgreSQLContainer<>("postgres:16.0");

    @Test
    void connectionToDatabase(){
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    void createPositive(){

    }

    private ResponseOrdersDto getOrderDtoRequest(){
        return ResponseOrdersDto.builder()

                .build();
    }
    private Orders getOrderRequest(){
        return null;
    }

}
