package com.publico.inteview.calculo;

import org.springframework.stereotype.Service;

@Service
public class CalculoDistanciaEntreCoordenadas implements CalculaDistanciaEntreCoordenadas{

    private final static Integer R = 6371;

    public Double calculaDistanciaEntreCoordenadas( Double lat1 , Double lon1, Double lat2 , Double lon2 ) {


        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distancia = R * c;

        return distancia;

    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
