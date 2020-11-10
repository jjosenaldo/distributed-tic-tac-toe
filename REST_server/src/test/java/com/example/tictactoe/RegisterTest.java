package com.example.tictactoe;

import static org.mockito.Mockito.when;

import com.example.tictactoe.controllers.RegisterController;
import com.example.tictactoe.model.request.RegisterName;
import com.example.tictactoe.model.response.RegistrationResponse;
import com.example.tictactoe.services.PlayersService;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;

@WebMvcTest(RegisterController.class)
@AutoConfigureRestDocs(outputDir = "src/main/asciidoc/snippets")
public class RegisterTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayersService service;

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }

    @Test
    public void registerOk() throws Exception {
        when(service.register("jose")).thenReturn(RegistrationResponse.ok("0"));

        RegisterName name = new RegisterName("jose");

        this.mockMvc
                .perform(post("/register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(name)))
                .andExpect(status().isOk())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(fieldWithPath("name").description("Nome a ser utilizado pelo jogador.")),
                        relaxedResponseFields(fieldWithPath("content.token").description(
                                "Identificador do jogador, o qual deve ser usado em requisições futuras."))));
    }

    @Test
    public void registerNameAlreadyUsed() throws Exception {
        when(service.register("jose")).thenReturn(RegistrationResponse.nameAlreadyUsed());
        RegisterName name = new RegisterName("jose");

        this.mockMvc
                .perform(post("/register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(name)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    @Test
    public void registerNoPlaceAvailable() throws Exception {
        when(service.register("jose")).thenReturn(RegistrationResponse.noPlaceAvailable());
        RegisterName name = new RegisterName("jose");

        this.mockMvc
                .perform(post("/register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(name)))
                .andExpect(status().isServiceUnavailable())
                .andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }
}
