package com.teste.foursys.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.foursys.Utils.CepValidator;
import com.teste.foursys.enums.StatusResponseEnum;
import com.teste.foursys.models.CepDTO;
import com.teste.foursys.models.LogDTO;
import com.teste.foursys.service.CepService;
import com.teste.foursys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CepServiceImpl implements CepService {

    public static final String CONSULT_CEP = "CONSULT-CEP";
    private String url = "https://viacep.com.br/ws/%s/json/";
    private final RestTemplate restTemplate;

    public CepServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private LogService logService;


    @Override
    public CepDTO getCep(String cep) {
        CepDTO cepDTO = new CepDTO();

        if (!CepValidator.isValidCep(cep)) {
            cepDTO.setCode(StatusResponseEnum.BAD_REQUEST.getCode());
            cepDTO.setMessage("CEP inválido: " + cep);
            this.postLogConsultCep(cep, cepDTO);
            return cepDTO;
        }

        String fullUrl = String.format(url, cep);
        cepDTO = restTemplate.getForObject(fullUrl, CepDTO.class);

        if (Objects.nonNull(cepDTO) && cepDTO.getErro().booleanValue()) {
            cepDTO.setCode(StatusResponseEnum.NOT_FOUND.getCode());
            cepDTO.setMessage("CEP não encontrado: " + cep);
        }
        this.postLogConsultCep(cep, cepDTO);
        return cepDTO;
    }

    private void postLogConsultCep(String cep, CepDTO cepDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LogDTO logDTO = new LogDTO();
            logDTO.setRequest(cep);
            logDTO.setResponse(objectMapper.writeValueAsString(cepDTO));
            logDTO.setTypeStatus(cepDTO.getCode());
            logDTO.setUserAtualz(CONSULT_CEP);
            logDTO.setDateAtualz(LocalDateTime.now());
            logService.postLog(logDTO);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    ;
}
