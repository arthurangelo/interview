package com.publico.inteview.configuration;

import com.publico.inteview.calculo.CalculaTempo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Configuracao {

    private static Logger logger = LoggerFactory.getLogger(Configuracao.class);

    @Bean
    public CalculaTempo getCalculaTempo(){
       logger.info("Criando bean: " + CalculaTempo.class);
        return  new CalculaTempo();
    }

}
