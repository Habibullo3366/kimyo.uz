package com.company.kimyouz.integration.card;

import com.company.kimyouz.controller.UserController;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import com.company.kimyouz.repository.CardRepository;
import com.company.kimyouz.service.mapper.CardMapper;
import com.company.kimyouz.service.validation.CardValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCardController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private  CardRepository cardRepository;
    @Autowired
    private  UserController userController;
    
    @MockBean
    private CardMapper cardMapper;

    @MockBean
    private CardValidation cardValidation;


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Test
    void connectionToDatabase() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();

    }

    @Test
    void shouldTestCreateCardPositive() throws Exception {
        String json = this.objectMapper.writeValueAsString(this.getCardRequest());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/card")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "This Token!")
                .content(json)

        ).andDo(print());


    }


    private ResponseCardDto getCardDtoRequest() {
        return ResponseCardDto.builder()
                .cardId(1)
                .userId(1)
                .cardName("Humo")
                .cardCode("0000")
                .cardFullName("Fazliddin TOkhirov")
                .build();
    }


    private Card getCardRequest() {
        return Card.builder()
                .userId(1)
                .cardName("Humo")
                .cardCode("0000")
                .cardFullName("Fazliddin TOkhirov")
                .build();
    }

}