package com.publico.inteview.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.publico.inteview.exception.CalculoServiceException;
import com.publico.inteview.model.Arquivo;
import com.publico.inteview.model.Posicoes;
import com.publico.inteview.service.BuscarInformacoesParaCalculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarInformacoesParaCalculoArquivoPosicoesImpl implements BuscarInformacoesParaCalculo {

    @Autowired
    private Environment environment;

    public <T> List buscar(String parametro) throws CalculoServiceException {

        try{

            String pathFile = environment.getProperty("pasta_arquivos_leitura") + Arquivo.POSICOES.getArquivo() + environment.getProperty("formato_arquivos_leitura");

            File file = new File(getClass().getClassLoader().getResource(pathFile).getFile());

            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',').withoutQuoteChar();;
            CsvMapper mapper = new CsvMapper();

            MappingIterator<T>  readValues = mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE)
                            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true)
                            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                            .readerFor(Posicoes.class).with(bootstrapSchema)
                            .readValues(file);

            List posicoes =  readValues.readAll();

            if(StringUtils.isEmpty(parametro)){
                return posicoes;
            }

            return (List) posicoes.stream().filter(carro -> parametro.equals( ((Posicoes) carro).getPlaca()) ).collect(Collectors.toList());

        }catch (Exception e){
            throw new CalculoServiceException("Erro inesperado ao buscar informaçoes.",e);
        }




    }


}
