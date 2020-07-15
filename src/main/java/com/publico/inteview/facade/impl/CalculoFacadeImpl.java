package com.publico.inteview.facade.impl;

import com.publico.inteview.calculo.CalculaDistanciaEntreCoordenadas;

import com.publico.inteview.calculo.CalculaTempo;
import com.publico.inteview.dto.TempoPorPosicaoDTO;
import com.publico.inteview.exception.CalculoFacadeException;
import com.publico.inteview.facade.CalculoFacade;
import com.publico.inteview.model.BasePoisDef;
import com.publico.inteview.model.Posicoes;
import com.publico.inteview.service.BuscarInformacoesParaCalculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class CalculoFacadeImpl implements CalculoFacade {

    private static Logger logger = LoggerFactory.getLogger(CalculoFacadeImpl.class);

    @Autowired
    private BuscarInformacoesParaCalculo buscarInformacoesParaCalculoArquivoPosicoesImpl;

    @Autowired
    private BuscarInformacoesParaCalculo buscarInformacoesParaCalculoArquivoBasePoisDefImpl;

    @Autowired
    private CalculaTempo calculaTempo ;

    @Autowired
    private CalculaDistanciaEntreCoordenadas calculaDistanciaEntreCoordenadas;

    @Override
    public List<TempoPorPosicaoDTO> buscarTempoPorPosicao(String parametro) throws CalculoFacadeException {

        try {

            List<TempoPorPosicaoDTO> tempoPorPosicaoDTOList = new ArrayList<>();

            List<Posicoes> posicoes = buscarInformacoesParaCalculoArquivoPosicoesImpl.buscar(parametro);

            List<BasePoisDef> basePoisDefs = buscarInformacoesParaCalculoArquivoBasePoisDefImpl.buscar("");

            Map<String,List<Posicoes>> mapPosicoesPorCarro = new HashMap<>();

            List<String> listaDeCarros = posicoes.stream().map( posicao -> posicao.getPlaca()).distinct().collect(Collectors.toList());

            for(String item : listaDeCarros){
                List<Posicoes> p = posicoes.stream().filter( f -> item.equals(f.getPlaca())).collect(Collectors.toList());
                mapPosicoesPorCarro.put(item,p);
            }

            for (Map.Entry<String,List<Posicoes>> carroPosicao : mapPosicoesPorCarro.entrySet()) {
                tempoPorPosicaoDTOList.addAll( buildListTempoPorPosicaoDTO(basePoisDefs, carroPosicao) );
            }

            logger.info("Busca realizada: " +  tempoPorPosicaoDTOList.toString());

            return tempoPorPosicaoDTOList;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new CalculoFacadeException(e.getMessage(), e);
        }

    }

    private List<TempoPorPosicaoDTO> buildListTempoPorPosicaoDTO(List<BasePoisDef> basePoisDefs, Map.Entry<String, List<Posicoes>> entrada) {

        List<TempoPorPosicaoDTO> tempoPorPosicaoDTOList  = new ArrayList<>();

        for( BasePoisDef base : basePoisDefs){

            long segundos = 0;

            for(Posicoes posicao : entrada.getValue()){

                Double lat1 =Double.parseDouble(base.getLatitude()) ;
                Double long1 =Double.parseDouble(base.getLongitude()) ;

                Double lat2 =Double.parseDouble(posicao.getLatitude()) ;
                Double long2 =Double.parseDouble(posicao.getLongitude()) ;

                Double distancia = calculaDistanciaEntreCoordenadas
                        .calculaDistanciaEntreCoordenadas(lat1,long1,lat2,long2);

                if(distancia <= Double.parseDouble(base.getRaio())){
                    calculaTempo.appendPeriodo(posicao.getData_posicao());
                }
                else{
                    segundos = segundos +  calculaTempo.calculaTempo();
                    calculaTempo.zeraPeriodo();
                }

            }

            if(!ObjectUtils.isEmpty(calculaTempo.getPeriodos())){
                segundos = segundos +  calculaTempo.calculaTempo();
            }

            TempoPorPosicaoDTO tempoPorPosicaoDTO = new TempoPorPosicaoDTO(entrada.getKey(),base.getNome(),calculaTempo.getTempo(segundos));
            tempoPorPosicaoDTOList.add(tempoPorPosicaoDTO);

        }

        return tempoPorPosicaoDTOList;
    }

}
