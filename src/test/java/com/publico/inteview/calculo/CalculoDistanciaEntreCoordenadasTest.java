package com.publico.inteview.calculo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CalculoDistanciaEntreCoordenadasTest {

    @Autowired
    private CalculaDistanciaEntreCoordenadas tested;

    @Test
    public void teste1(){
        Double lat1=-18.911979;
        Double long1=-48.252378;

        Double lat2=-18.916206;
        Double long2=-46.954875;

        assertEquals(136.48617968113783,tested.calculaDistanciaEntreCoordenadas(lat1,long1,lat2,long2));
    }
}
