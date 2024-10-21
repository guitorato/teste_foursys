package com.teste.foursys.service;

import com.teste.foursys.models.LogDTO;
import com.teste.foursys.models.LogResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface LogService {

    LogResponseDTO getLog(long cep , String typeStatus , LocalDateTime dateAtualzIni , LocalDateTime dateAtualzFin);

    void postLog(LogDTO dto);
}
