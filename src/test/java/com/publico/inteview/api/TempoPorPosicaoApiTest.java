package com.publico.inteview.api;


import com.publico.inteview.exception.CalculoFacadeException;
import com.publico.inteview.facade.CalculoFacade;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TempoPorPosicaoApiTest {

    private MockMvc mockMvc;

    @Autowired
    private Environment env;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void buscar() throws Exception {

        Integer tamanho = new Integer(env.getProperty("tamanho_lista_total"));

        mockMvc.perform( get("/tempo-por-posicao/buscarTempoPorPosicao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(tamanho)));
    }

    @Test
    public void buscarComParametro() throws Exception {

        Integer tamanho = new Integer(env.getProperty("tamanho_lista_parametro"));

        mockMvc.perform( get("/tempo-por-posicao/buscarTempoPorPosicao")
                .contentType(MediaType.APPLICATION_JSON)
                .param("carro","NOVO123"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(tamanho)));
    }

    @Test
    public void buscarSemParametro() throws Exception {

        Integer tamanho = new Integer(env.getProperty("tamanho_lista_sem_parametro"));

        mockMvc.perform( get("/tempo-por-posicao/buscarTempoPorPosicao")
                .contentType(MediaType.APPLICATION_JSON)
                .param("carro","NAOEXISTE"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(tamanho)));
    }


}


