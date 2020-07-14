package com.publico.inteview.service;

import com.publico.inteview.exception.CalculoServiceException;

import java.util.List;

public interface BuscarInformacoesParaCalculo {

    <T> List buscar(String parametro) throws CalculoServiceException;
}
