package com.publico.inteview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePoisDef  implements Serializable {

        @JsonProperty("nome")
        private String nome;

        @JsonProperty("raio")
        private String raio;

        @JsonProperty("latitude")
        private String latitude;

        @JsonProperty("longitude")
        private String longitude;

}
