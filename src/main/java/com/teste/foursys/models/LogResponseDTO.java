package com.teste.foursys.models;

import lombok.Data;

import java.util.List;

@Data
public class LogResponseDTO {

    private int code;
    private String message;
    private List<LogDTO> logDTOList;
}
