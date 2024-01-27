package com.company.kimyouz.integration.card;

import com.company.kimyouz.controller.UserController;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import com.company.kimyouz.repository.CardRepository;
import com.company.kimyouz.service.mapper.CardMapper;
import com.company.kimyouz.service.validation.CardValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.company.kimyouz.integration.mock.MockContent.getRequestCard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Testcontainers
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_USER"}, username = "User")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCardController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardValidation cardValidation;


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
    public void deleteAll() {
        this.cardRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "test create positive")
    public void shouldTestCardCreatePositive() throws Exception {

        String json = this.objectMapper.writeValueAsString(getRequestCard());

        this.mockMvc.perform(
                        post("/card")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andDo(print())
                .andExpect(result -> {

                });


    }


}