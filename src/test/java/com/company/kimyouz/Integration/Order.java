package com.company.kimyouz.Integration;


import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.repository.OrdersRepository;
import com.company.kimyouz.service.mapper.OrdersMapper;
import com.company.kimyouz.service.validation.OrdersValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static com.company.kimyouz.Mock.MockContent.getFullContent;
import static com.company.kimyouz.Mock.MockContent.getRequestOrders;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Testcontainers
@AutoConfigureMockMvc
@WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_USER"}, username = "User")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Order {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrdersRepository ordersRepository;

    @MockBean
    private OrdersMapper ordersMapper;

    @MockBean
    private OrdersValidation ordersValidation;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Test
    void connectionToDatabase() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }
    @AfterEach
    @BeforeEach
    public void deleteAll(){
         this.ordersRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "test create positive")
    public void shouldTestOrderCreate() throws Exception {
        String json = this.objectMapper.writeValueAsString(getRequestOrders());
        this.mockMvc.perform(
                post("/orders")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andDo(print())
                .andExpect(result -> {

                    getFullContent(result.getResponse().getContentAsString(),objectMapper, ResponseOrdersDto.class);
                });
    }








 /*   @Test
    void createPositive() throws Exception {
        String json = this.objectMapper.writeValueAsString(this.getOrderRequest());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/orders")
                .content(json)
       //                 .header("Authorization", "This Token!")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print());

    }

    private ResponseOrdersDto getOrderDtoRequest() {
        return ResponseOrdersDto.builder()
                .orderId(1)
                .totalPrice(1.0)
                .build();
    }

    private Orders getOrderRequest() {
        return Orders.builder()
                .totalPrice(1.0)
                .build();
    }*/

}
