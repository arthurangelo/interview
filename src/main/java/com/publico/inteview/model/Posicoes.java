package com.publico.inteview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Posicoes implements Serializable {

    @JsonProperty("placa")
    private String placa;

    @JsonProperty("data_posicao")
    private String data_posicao;

    @JsonProperty("velocidade")
    private String velocidade;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("ignicao")
    private String ignicao;
}
