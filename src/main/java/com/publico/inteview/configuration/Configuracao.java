package com.publico.inteview.configuration;

import com.publico.inteview.calculo.CalculaTempo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Configuracao {


    @Bean
    public CalculaTempo getCalculaTempo(){
       return  new CalculaTempo();
    }

}
