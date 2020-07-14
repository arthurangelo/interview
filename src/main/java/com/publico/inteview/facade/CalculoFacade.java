package com.publico.inteview.facade;

import com.publico.inteview.exception.CalculoFacadeException;

import java.util.List;

public interface CalculoFacade {

       List buscarTempoPorPosicao(String ano) throws CalculoFacadeException;
}
