package com.teste.foursys.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String ddd;
    private String siafi;
    private Boolean erro = false;
    private String message;
    private int code = 200;

    public CepDTO(String message, int code, Boolean erro) {
        this.message = message;
        this.code = code;
        this.erro = erro;
    }

}

