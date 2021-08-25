package br.com.alura.forum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
// @WebMvcTest // seria utilizado se fosse testado só o controller, sem subir todo o contexto
@AutoConfigureMockMvc // como não foi habilitado o @WebMvcTest, esta anotação tem que ser definida par autilizar o mockmvc
@SpringBootTest
@ActiveProfiles("test") // utiliza o 'application-test.properties' na execução dos testes
public class AutenticacaoControllerTest
{

    @Autowired
    // simula uma requisição mvc (como se fosse um postman, que executa os comandos)
    private MockMvc mockMvc;

    @Test
    public void deveriaDevolver400CasoDadosAutenticacaoEstejamIncorretos() throws Exception
    {

        URI uri = new URI("/auth");

        // json com dados do usuário
        String json = "{ \"email:\" \"invalido@email.com\", \"senha\": \"123456\"}";

        // simula uma requisição do tipo 'POST'
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));


    }

}