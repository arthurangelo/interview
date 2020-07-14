package com.publico.inteview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tempo {

    @JsonProperty("dias")
    private Long dias;

    @JsonProperty("horas")
    private Long horas ;

    @JsonProperty("minutos")
    private Long minutos;

    @JsonProperty("segundos")
    private Long segundos;
}
