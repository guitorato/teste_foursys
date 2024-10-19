package com.teste.foursys.service;

import com.teste.foursys.models.CepDTO;
import org.springframework.stereotype.Service;


public interface CepService {

     CepDTO getCep(String cep);
}
