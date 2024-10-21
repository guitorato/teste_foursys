package com.teste.foursys.controller;

import com.teste.foursys.models.CepDTO;
import com.teste.foursys.models.ErrorResponseDTO;
import com.teste.foursys.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/buscar-cep/{cep}")
    public ResponseEntity<?> buscarCep(@PathVariable String cep) {

        CepDTO dto = cepService.getCep(cep);
        if (dto.getCode() != 200) {
            return ResponseEntity.status(dto.getCode()).body(new ErrorResponseDTO(dto.getMessage()));
        }
        return ResponseEntity.ok(dto);
    }
}
