package com.example.tictactoe;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.tictactoe.controllers.GameStatusController;
import com.example.tictactoe.model.Board;
import com.example.tictactoe.model.GameStatus;
import com.example.tictactoe.model.response.StatusResponse;
import com.example.tictactoe.services.GameStatusService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GameStatusController.class)
@AutoConfigureRestDocs(outputDir = "src/main/asciidoc/target/snippets")
public class GameStatusTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStatusService service;

    private final String TOKEN = "0";

    @Test
    public void gameStatusOk() throws Exception {
        String status = "executando";
        Boolean isYourTurn = true;
        List<String> boardRow0 = Arrays.asList("O", "X", Board.EMPTY_CELL);
        List<String> boardRow1 = Arrays.asList("O", "X", Board.EMPTY_CELL);
        List<String> boardRow2 = Arrays.asList("O", Board.EMPTY_CELL, Board.EMPTY_CELL);
        List<List<String>> board = new ArrayList<List<String>>() {
            private static final long serialVersionUID = 1L;

            {
                add(boardRow0);
                add(boardRow1);
                add(boardRow2);
            }
        };
        List<Integer> winCoordinatesCell0 = Arrays.asList(0, 0);
        List<Integer> winCoordinatesCell1 = Arrays.asList(1, 0);
        List<Integer> winCoordinatesCell2 = Arrays.asList(2, 0);

        List<List<Integer>> winCoordinates = new ArrayList<List<Integer>>() {
            private static final long serialVersionUID = 1L;

            {
                add(winCoordinatesCell0);
                add(winCoordinatesCell1);
                add(winCoordinatesCell2);
            }
        };

        GameStatus mockGameStatus = new GameStatus(status, isYourTurn, board, winCoordinates);

        when(service.getGameStatus(TOKEN)).thenReturn(StatusResponse.ok(mockGameStatus));

        parameterWithName("token");
        this.mockMvc
                .perform(get("/status").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("token", TOKEN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestParameters(parameterWithName("token").description("Identificador do jogador.")),
                        relaxedResponseFields(
                                fieldWithPath("content.status").description(
                                        "Estado atual do jogo: não começou, em andamento, empate, derrota ou vitória."),
                                fieldWithPath("content.yourTurn").description(
                                        "Verdadeiro se o jogador está em sua vez, e falso caso contrário."),
                                fieldWithPath("content.board").description("Matriz 3x3 correspondendo ao tabuleiro."),
                                fieldWithPath("content.winCoordinates").description(
                                        "Coordenadas das 3 posições vencedoras, caso algum jogador tenha vencido."))));
    }

}
