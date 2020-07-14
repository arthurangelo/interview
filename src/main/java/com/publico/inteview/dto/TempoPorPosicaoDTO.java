package com.publico.inteview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.publico.inteview.model.Tempo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempoPorPosicaoDTO {

    @JsonProperty("placa")
    private String placa;
    @JsonProperty("posicao")
    private String posicao;
    @JsonProperty("tempo")
    private Tempo tempo;


}
