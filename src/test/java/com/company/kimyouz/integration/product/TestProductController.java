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
import org.junit.jupiter.api.Assertions;
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

import static com.company.kimyouz.integration.mock.MockContent.getProductDtoRequest;
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


    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private UserController userController;




//
//    @ServiceConnection
//    @Container
//    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.0");
//
//
//    @Test
//    void connectionDatabase(){
//        assertThat(postgreSQLContainer.isCreated()).isTrue();
//        assertThat(postgreSQLContainer.isRunning()).isTrue();
//    }
//
//
//    @Test
//    void TestCreateProductPositive() throws Exception {
////        String json = this.objectMapper.writeValueAsString(this.getProductDtoResponse());
//        mvc.perform(MockMvcRequestBuilders
//                .post("/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(getProductDtoRequest())
//                )
//        ).andDo(print())
//                .andExpect(result -> {
//                    String resultJson = result.getResponse().getContentAsString();
//
//                    ResponseDto<ResponseProductDto> responseDto = null;
//
//
//                });
//    }














//
//    private String getToken(){
//        ResponseDto<ResponseUserDto> userDtoResponseDto = this.userController.createEntity(this.getUserDtoRequest()).getBody();
//
//        ResponseDto<TokenResponseDto> response = this.userController.logIn(new LogInResponseDto("User" , "root"));
//
//        Assertions.assertNotNull(response.getContent() , "NOT NULL");
//
//        return response.getContent().getAccessToken();
//    }
//
//
//    private RequestUserDto getUserDtoRequest(){
//        return RequestUserDto.builder()
//                .age(15)
//                .email("qwerty@gmail.com")
//                .firstname("Mahmud")
//                .lastname("Jumyazov")
//                .enabled(true)
//                .password("root")
//                .username("User")
//                .build();
//    }
//
//    private Users getUser(){
//        return Users.builder()
//                .userId(1)
//                .age(15)
//                .email("qwerty@gmail.com")
//                .firstname("Mahmud")
//                .lastname("Jumyazov")
//                .enabled(true)
//                .password("root")
//                .username("User")
//                .build();
//    }
}
