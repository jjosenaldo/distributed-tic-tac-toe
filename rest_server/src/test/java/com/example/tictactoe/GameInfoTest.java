package com.example.tictactoe;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import com.example.tictactoe.controllers.GameInfoController;
import com.example.tictactoe.model.GameInfo;
import com.example.tictactoe.model.response.InfoResponse;
import com.example.tictactoe.model.response.RegistrationResponse;
import com.example.tictactoe.services.GameInfoService;
import com.example.tictactoe.services.PlayersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GameInfoController.class)
@AutoConfigureRestDocs(outputDir = "src/main/asciidoc/target/snippets")
public class GameInfoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameInfoService gameInfoService;

    // @MockBean
    // private PlayersService playersService;

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }

    // @BeforeAll
    // public void registerPlayers() {
    // when(playersService.register("jose")).thenReturn(RegistrationResponse.ok("0"));
    // when(playersService.register("gil")).thenReturn(RegistrationResponse.ok("1"));

    // playersService.register("jose");
    // playersService.register("gil");
    // }

    @Test
    public void gameInfoOk() throws Exception {
        GameInfo mockGameInfo = new GameInfo("jose", "gil", "O", "X", "jose");
        String token = "0";

        when(gameInfoService.getGameInfo(token)).thenReturn(InfoResponse.ok(mockGameInfo));

        this.mockMvc
                .perform(get("/info").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("token", token))
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestParameters(parameterWithName("token").description("Identificador do jogador.")),

                        relaxedResponseFields(fieldWithPath("content.opponentName").description("Nome do oponente."),
                                fieldWithPath("content.opponentName").description("Nome do oponente."),
                                fieldWithPath("content.opponentLabel")
                                        .description("Marcador do oponente no tabuleiro."),
                                fieldWithPath("content.yourName").description("Nome deste jogador."),
                                fieldWithPath("content.yourLabel").description("Marcador deste jogador no tabuleiro."),
                                fieldWithPath("content.initialPlayerName")
                                        .description("Nome do jogador que vai jogar primeiro."))));
    }
}
