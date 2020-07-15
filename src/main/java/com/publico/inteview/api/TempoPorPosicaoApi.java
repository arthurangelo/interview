package com.publico.inteview.api;

import com.publico.inteview.exception.CalculoFacadeException;
import com.publico.inteview.facade.CalculoFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tempo-por-posicao")
public class TempoPorPosicaoApi {

    private static Logger logger = LoggerFactory.getLogger(TempoPorPosicaoApi.class);

    @Autowired
    private CalculoFacade calculoFacade;

    @GetMapping("/buscarTempoPorPosicao")
    public ResponseEntity buscar(@RequestParam(value = "carro",required = false) String carro) throws CalculoFacadeException {

        logger.info("Executado operacao - GET /tempo-por-posicao/buscarTempoPorPosicao");
        return ResponseEntity.ok().body(calculoFacade.buscarTempoPorPosicao(carro));
    }


}


