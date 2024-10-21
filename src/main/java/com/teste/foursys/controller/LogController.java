package com.teste.foursys.controller;

import com.teste.foursys.models.LogResponseDTO;
import com.teste.foursys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log_info/{cep}")
    public ResponseEntity<LogResponseDTO> buscarLog(@PathVariable String cep,
                                                    @RequestParam(required = false) String typeStatus,
                                                    @RequestParam(required = false) LocalDateTime dateAtualizini,
                                                    @RequestParam(required = false) LocalDateTime dateAtualizFin){
        LogResponseDTO log = logService.getLog(Long.valueOf(cep), typeStatus, dateAtualizini, dateAtualizFin);
        return ResponseEntity.status(log.getCode()).body(log);
    }
    }
