package com.company.kimyouz.integration.product;



import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import com.company.kimyouz.repository.ProductRepository;
import com.company.kimyouz.service.mapper.ProductMapper;
import com.company.kimyouz.validation.ProductValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestProductController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;


    @MockBean
    private ProductMapper productMapper;


    @MockBean
    private ProductValidation productValidation;


    @ServiceConnection
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.0");


    @Test
    void connectionDatabase(){
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }


    @Test
    void TestCreateProductPositive() throws Exception {
//        String json = this.objectMapper.writeValueAsString(this.getProductDtoResponse());
        mvc.perform(MockMvcRequestBuilders
                .post("/product")
                .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization" , "This token")
                .content(objectMapper.writeValueAsString(this.getProductDtoResponse())
                )
        ).andDo(print());
    }

    private ResponseProductDto getProductDtoResponse(){
        return ResponseProductDto.builder()
                .prodId(1)
                .prodType("Car")
                .prodPrice(12000.0)
                .prodName("BMW")
                .prodColor("Dark Blue")
                .prodAmount(1000)
                .categoryId(1)
                .build();
    }

    private Product getProduct(){
        return Product.builder()
                .prodId(1)
                .prodType("Car")
                .prodPrice(12000.0)
                .prodName("BMW")
                .prodColor("Dark Blue")
                .prodAmount(1000)
                .categoryId(1)
                .build();
    }
}
