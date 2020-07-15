package com.publico.inteview.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.publico.inteview.exception.CalculoServiceException;
import com.publico.inteview.facade.impl.CalculoFacadeImpl;
import com.publico.inteview.model.Arquivo;
import com.publico.inteview.model.BasePoisDef;
import com.publico.inteview.service.BuscarInformacoesParaCalculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@Service
public class BuscarInformacoesParaCalculoArquivoBasePoisDefImpl implements BuscarInformacoesParaCalculo {

    private static Logger logger = LoggerFactory.getLogger(BuscarInformacoesParaCalculoArquivoBasePoisDefImpl.class);

    @Autowired
    private Environment environment;

    public <T> List buscar(String parametro) throws CalculoServiceException {

        try{

            logger.info("Realizando busca de Pontos de Interesse. parametro -> " + parametro );

            String pathFile = environment.getProperty("pasta_arquivos_leitura") + Arquivo.BASE_POIS_DEF.getArquivo() + environment.getProperty("formato_arquivos_leitura");

            InputStream in = getClass().getResourceAsStream(pathFile);

            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',').withoutQuoteChar();;
            CsvMapper mapper = new CsvMapper();

            MappingIterator<T>  readValues = mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE)
                            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true)
                            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                            .readerFor(BasePoisDef.class).with(bootstrapSchema)
                            .readValues(in);

            return readValues.readAll();

        }catch (Exception e){
            logger.error(e.getMessage(),e);

            throw new CalculoServiceException("Erro inesperado ao buscar informaçoes.",e);
        }

    }


}
