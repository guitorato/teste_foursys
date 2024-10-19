package com.teste.foursys.service.impl;

import com.teste.foursys.Utils.CepValidator;
import com.teste.foursys.models.CepDTO;
import com.teste.foursys.service.CepService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CepServiceImpl implements CepService {

    private String url = "https://viacep.com.br/ws/%s/json/";
    private final RestTemplate restTemplate;

    public CepServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public CepDTO getCep(String cep) {
        CepDTO cepDTO = new CepDTO();

        if(!CepValidator.isValidCep(cep)){
            cepDTO.setCode(400);
            cepDTO.setMessage("CEP inválido: " + cep);
            return cepDTO;
        }

        String fullUrl = String.format(url, cep);
        cepDTO = restTemplate.getForObject(fullUrl, CepDTO.class);

        if(Objects.nonNull(cepDTO) && cepDTO.getErro().booleanValue()){
            cepDTO.setCode(404);
            cepDTO.setMessage("CEP não encontrado: " + cep);
        }
        return cepDTO;
    }
}
