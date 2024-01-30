package com.company.kimyouz.integration.card;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import com.company.kimyouz.repository.CardRepository;
import com.company.kimyouz.service.mapper.CardMapper;
import com.company.kimyouz.service.validation.CardValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
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

import static com.company.kimyouz.integration.mock.MockContent.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@AutoConfigureMockMvc
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

    @MockBean
    private CardValidation mockCardValidation;


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Test
    @DisplayName(value = "connection to db")
    void connectionToDatabase() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }


    @AfterEach
    @BeforeEach
    void deleteAll() {
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
                .andExpect(status().isOk())
                .andExpect(result -> {


                    var fullContent = getFullContent(
                            result.getResponse().getContentAsString(),
                            objectMapper,
                            ResponseCardDto.class
                    );

                    assertThat(fullContent.isSuccess()).isTrue();
                    assertThat(fullContent.getMessage()).isEqualTo("OK");
                    assertThat(fullContent.getCode()).isEqualTo(0);
                    assertThat(fullContent.getContent()).isNotNull();

                });

    }

    @Test
    public void shouldTestCardCreateException() throws Exception {

        when(this.mockCardValidation.cardValid(any()))
                .thenThrow(RuntimeException.class);

        String json = this.objectMapper.writeValueAsString(getRequestCard());

        this.mockMvc.perform(
                        post("/card")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> {

                    var fullContent = getFullContent(
                            result.getResponse().getContentAsString(),
                            objectMapper,
                            ResponseCardDto.class
                    );

                    assertThat(fullContent.isSuccess()).isFalse();


                });


    }

    @Test
    public void shouldTestCardCreateIsValid() throws Exception {

        String json = this.objectMapper.writeValueAsString(getRequestCardWithUserId());
        this.mockMvc.perform(
                        post("/card")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> {

                    var fullContent = getFullContent(
                            result.getResponse().getContentAsString(),
                            objectMapper,
                            ResponseCardDto.class
                    );



                });

    }

    @Test
    public void shouldTestGetCardPositive() throws Exception {

        Card savedCard = this.cardRepository.save(getCardEntity());

        this.mockMvc.perform(
                        get("/card").param("id", savedCard.getCardId().toString())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {

                    var fullContent = getFullContent(
                            result.getResponse().getContentAsString(),
                            this.objectMapper,
                            ResponseCardDto.class
                    );

                    assertThat(fullContent.isSuccess()).isTrue();

                });

    }

    @Test
    public void shouldTestGetCardNegative() throws Exception {

        this.mockMvc.perform(
                        get("/card").param("id", "-1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {

                    var fullContent = getFullContent(
                            result.getResponse().getContentAsString(),
                            this.objectMapper,
                            ResponseCardDto.class
                    );
                    

                });

    }


}