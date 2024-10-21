package com.teste.foursys.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private Long id;
    private String userAtualz;
    private int typeStatus;
    private LocalDateTime dateAtualz;
    private String response;
    private String request;


}
