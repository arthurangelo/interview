package com.publico.inteview.facade.impl;

import com.publico.inteview.calculo.CalculaDistanciaEntreCoordenadas;
import com.publico.inteview.calculo.CalculaTempo;
import com.publico.inteview.dto.TempoPorPosicaoDTO;
import com.publico.inteview.exception.CalculoFacadeException;
import com.publico.inteview.facade.CalculoFacade;
import com.publico.inteview.model.BasePoisDef;
import com.publico.inteview.model.Posicoes;
import com.publico.inteview.service.BuscarInformacoesParaCalculo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CalculoFacadeImplTest {

    @Autowired
    private CalculoFacade tested;

    @Test
    public void buscarTempoPorPosicao() throws CalculoFacadeException {


            List retorno =tested.buscarTempoPorPosicao("");

            assertEquals(75,retorno.size() );

    }

    @Test
    public void buscarTempoPorPosicaoComParametro() throws CalculoFacadeException {


        List retorno =tested.buscarTempoPorPosicao("NOVO123");

        assertEquals(25,retorno.size() );

    }

    @Test
    public void buscarTempoPorPosicaoComParametroNaoExiste() throws CalculoFacadeException {


        List retorno =tested.buscarTempoPorPosicao("NAOEXISTO");

        assertEquals(0,retorno.size() );

    }
}
