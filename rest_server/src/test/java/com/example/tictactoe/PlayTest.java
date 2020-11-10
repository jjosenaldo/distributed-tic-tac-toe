package com.example.tictactoe;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tictactoe.controllers.PlayController;
import com.example.tictactoe.model.request.PlayInfo;
import com.example.tictactoe.model.response.PlayResponse;
import com.example.tictactoe.services.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PlayController.class)
@AutoConfigureRestDocs(outputDir = "src/main/asciidoc/target/snippets")
public class PlayTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService service;

    private final int ROW = 0;
    private final int COL = 0;
    private final String TOKEN = "0";
    private final PlayInfo PLAY_INFO = new PlayInfo(ROW, COL, TOKEN);

    @Test
    public void playOk() throws Exception {
        when(service.play(ROW, COL, TOKEN)).thenReturn(PlayResponse.ok());

        this.mockMvc
                .perform(post("/play").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(PLAY_INFO)))
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(fieldWithPath("row").description("Linha do tabuleiro onde será feita a jogada."),
                                fieldWithPath("column").description("Coluna do tabuleiro onde será feita a jogada."),
                                fieldWithPath("token")
                                        .description("Identificador do jogador que está fazendo a jogada."))));
    }

    @Test
    public void playInvalidToken() throws Exception {
        when(service.play(ROW, COL, TOKEN)).thenReturn(PlayResponse.tokenIsInvalid());

        this.mockMvc
                .perform(post("/play").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(PLAY_INFO)))
                .andExpect(status().isUnauthorized())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    @Test
    public void playGameHasNotStartedYet() throws Exception {
        when(service.play(ROW, COL, TOKEN)).thenReturn(PlayResponse.gameHasNotStartedYet());

        this.mockMvc
                .perform(post("/play").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(PLAY_INFO)))
                .andExpect(status().isForbidden())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    @Test
    public void playIndicesOutOfBounds() throws Exception {
        int outOfBoundsRow = 3;
        PlayInfo outOfBoundsPlayInfo = new PlayInfo(outOfBoundsRow, COL, TOKEN);
        when(service.play(outOfBoundsRow, COL, TOKEN)).thenReturn(PlayResponse.indicesAreOutOfBounds());

        this.mockMvc
                .perform(post("/play").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(outOfBoundsPlayInfo)))
                .andExpect(status().isForbidden())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }
}
