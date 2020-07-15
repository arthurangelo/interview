package com.publico.inteview.calculo;

import com.publico.inteview.model.Tempo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculaTempoTest {

    private CalculaTempo tested;

    @BeforeEach
    public void setup(){
        tested = new CalculaTempo();
    }

    @Test
    public void appendPeriodo1(){
        tested.appendPeriodo("Sun Dec 16 2018 23:45:47 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(0,tested.calculaTempo());
    }

    @Test
    public void appendPeriodo2(){
        tested.appendPeriodo("Sun Dec 16 2018 23:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 16 2018 24:45:47 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(3600,tested.calculaTempo());
    }

    @Test
    public void zeraPeriodo(){
        tested.appendPeriodo("Sun Dec 16 2018 23:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 16 2018 24:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.zeraPeriodo();
        assertEquals(0,tested.calculaTempo());
        assertTrue(0 == tested.getPeriodos().size());
    }


    @Test
    public void getTempo(){
        tested.appendPeriodo("Sun Dec 16 2018 21:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 16 2018 22:45:47 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getDias());
        assertEquals(1,tested.getTempo(tested.calculaTempo()).getHoras());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getMinutos());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getSegundos());
    }

    @Test
    public void getTempo2(){
        tested.appendPeriodo("Sun Dec 16 2018 21:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 16 2018 21:45:50 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getDias());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getHoras());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getMinutos());
        assertEquals(3,tested.getTempo(tested.calculaTempo()).getSegundos());
    }

    @Test
    public void getTempo3(){
        tested.appendPeriodo("Sun Dec 16 2018 21:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 16 2018 21:50:47 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getDias());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getHoras());
        assertEquals(5,tested.getTempo(tested.calculaTempo()).getMinutos());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getSegundos());
    }

    @Test
    public void getTempo4(){
        tested.appendPeriodo("Sun Dec 16 2018 21:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 17 2018 21:45:47 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(1,tested.getTempo(tested.calculaTempo()).getDias());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getHoras());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getMinutos());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getSegundos());
    }

    @Test
    public void getTempo5(){
        tested.appendPeriodo("Sun Dec 16 2018 21:45:47 GMT-0200 (Hora oficial do Brasil)");
        tested.appendPeriodo("Sun Dec 17 2018 22:12:30 GMT-0200 (Hora oficial do Brasil)");
        assertEquals(1,tested.getTempo(tested.calculaTempo()).getDias());
        assertEquals(0,tested.getTempo(tested.calculaTempo()).getHoras());
        assertEquals(26,tested.getTempo(tested.calculaTempo()).getMinutos());
        assertEquals(43,tested.getTempo(tested.calculaTempo()).getSegundos());
    }

}
