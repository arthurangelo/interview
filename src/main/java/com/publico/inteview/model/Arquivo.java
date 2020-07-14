package com.publico.inteview.model;

public  enum Arquivo {

    POSICOES("posicoes"),
    BASE_POIS_DEF("base_pois_def");

    private  String arquivo = "";
    
    Arquivo(String arquivo){
        this.arquivo = arquivo;
    }
    public String getArquivo(){
        return arquivo;
    }

}
