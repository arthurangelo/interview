package com.publico.inteview.calculo;

import com.publico.inteview.model.Tempo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalculaTempo {

    private List<String> periodos;
    private long segundos;

    public CalculaTempo(){
        periodos = new ArrayList<>();
        segundos = 0;
    }

    public void appendPeriodo(String periodo){
        periodos.add(periodo);
    }

    public void zeraPeriodo(){
        periodos.clear();
        segundos = 0;
    }

    public long calculaTempo() {

       if(periodos.size() <= 1){
           return 0;
       }

       for(int i = 0; i < periodos.size(); i++ ){
           if(i+1 == periodos.size()){
               break;
           }

           LocalDateTime firstLocalDateTime = getLocalDateTimeFromString(periodos.get(i));

           LocalDateTime secondLocalDateTime = getLocalDateTimeFromString(periodos.get(i+1));

           segundos = segundos + getDuration(firstLocalDateTime,secondLocalDateTime).getSeconds();

       }

       return segundos;

    }

    public long getSegundos(){
        return this.segundos;
    }

    public List<String> getPeriodos(){
        return this.periodos;
    }


    private Duration getDuration(LocalDateTime first, LocalDateTime second){
        return  Duration.between(first,second);
    }

    private LocalDateTime getLocalDateTimeFromString(String time){
        Date dt = new Date(time);
        return dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

    public Tempo getTempo(Long segundos) {

        Long dias = 0L;
        Long horas = 0L ;
        Long minutos = 0L;

        if(segundos >= 86400 ){
            dias = new Long(segundos / 86400);
            segundos = segundos - (dias * 86400);
        }

        if (segundos >= 3600){
            horas = new Long(segundos / 3600);
            segundos = segundos - (horas * 3600);
        }

        if (segundos >= 60){
            minutos = new Long(segundos / 60);
            segundos = segundos - (minutos * 60);
        }

        return new Tempo(dias, horas, minutos,segundos);
    }

}
