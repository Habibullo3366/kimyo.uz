package com.company.kimyouz.integration.product;



import com.company.kimyouz.config.SimpleResponseDto;
import com.company.kimyouz.controller.UserController;
import com.company.kimyouz.dto.LogInResponseDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.TokenResponseDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.Product;
import com.company.kimyouz.entity.Users;
import com.company.kimyouz.repository.ProductRepository;
import com.company.kimyouz.service.UserService;
import com.company.kimyouz.service.mapper.ProductMapper;
import com.company.kimyouz.validation.ProductValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Optional;

import static com.company.kimyouz.integration.mock.MockContent.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Testcontainers
@AutoConfigureMockMvc
@WithMockUser(authorities = {"ROLE_ADMIN" , "ROLE_USER"} , username = "User")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestProductController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductMapper productMapper;


    @Autowired
    private ProductValidation productValidation;

    @MockBean
    private ProductValidation mockProductValidation;



    @ServiceConnection
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.0");


    @Test
    public void connectionDatabase(){
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }


    @BeforeEach
    public void deleteAll(){
        this.productRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "create product positive")
    public void TestCreateProductPositive() throws Exception {
        String json = objectMapper.writeValueAsString(getProductDtoRequest());
        this.mvc.perform(
                post("/product")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)

        ).andDo(print())
                .andExpect(result -> {


                    ResponseDto<ResponseProductDto> fullContent = getFullContent(result.getResponse().getContentAsString() , objectMapper , ResponseProductDto.class);

                    assertThat(fullContent.isSuccess()).isTrue();

                    assertThat(fullContent.getCode()).isEqualTo(0);

                    assertThat(fullContent.getMessage()).isEqualTo("OK");

                    assertThat(fullContent.getErrorList()).isNull();

                 //  assertThat(fullContent.getContent()).isNotNull();

                });

    }


    @Test
    public void TestCreateProductException() throws Exception{
        when(this.mockProductValidation.productValid(any()))
                .thenThrow(RuntimeException.class);


        this.mvc.perform(
                post("/product")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(getProductDtoRequest()))
            )
                .andDo(print())
                .andExpect(result -> {
                    ResponseDto<ResponseProductDto> fullContent = getFullContent(result.getResponse().getContentAsString() , objectMapper , ResponseProductDto.class);

                    assertThat(fullContent.isSuccess()).isFalse();

                    assertThat(fullContent.getCode()).isEqualTo(-2);

//                    assertThat(fullContent.getMessage()).isEqualTo("OK");

//                    assertThat(fullContent.getErrorList()).isNull();
                });
        // --> status code 400 , code -2  , test passed


    }


    @Test
    public void TestCreateProductValidation() throws Exception{


        String json = this.objectMapper.writeValueAsString(getProductDtoRequestWithIds());

        this.mvc.perform(
                post("/product")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(
                result -> {
                    ResponseDto<ResponseProductDto> fullContent = getFullContent(result.getResponse().getContentAsString() , objectMapper , ResponseProductDto.class);

                    assertThat(fullContent.isSuccess()).isFalse();

                    assertThat(fullContent.getCode()).isEqualTo(-3);
                }
        );


//        when(this.productValidation.productValid(any())).thenReturn(new ArrayList<>());

        // --> status code 400 , code -3 , test passed
    }



    @Test
    public void TestGetProductPositive() throws Exception {
                this.productRepository.save(getProduct());

                this.mvc.perform(
                        get("/product").param("id" , getProduct().getProdId().toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                ).andDo(print())
                        .andExpect(result -> {
                            var responseDto = getFullContent(
                                    result.getResponse().getContentAsString() , this.objectMapper , ResponseProductDto.class
                            );

                            assertThat(responseDto.isSuccess()).isTrue();
                            assertThat(responseDto.getCode()).isEqualTo(0);


                        });
    }


    @Test
    public void TestGetProductNegative() throws Exception {


        this.mvc.perform(
                get("/product").param("id" , "-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(result -> {
                    var responseDto = getFullContent(
                            result.getResponse().getContentAsString() , this.objectMapper , ResponseProductDto.class
                    );

                    assertThat(responseDto.isSuccess()).isFalse();
                    assertThat(responseDto.getCode()).isEqualTo(-1);
                });
    }



}
