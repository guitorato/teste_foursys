package com.teste.foursys.service.impl;

import com.teste.foursys.entitys.LogEntity;
import com.teste.foursys.enums.StatusResponseEnum;
import com.teste.foursys.mappers.LogMapper;
import com.teste.foursys.models.LogDTO;
import com.teste.foursys.models.LogResponseDTO;
import com.teste.foursys.repository.LogRepository;
import com.teste.foursys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LogRepository logRepository;

    @Override
    public LogResponseDTO getLog(long cep, String typeStatus, LocalDateTime dateAtualzIni, LocalDateTime dateAtualzFin) {
        LogResponseDTO response = new LogResponseDTO();
        try {

            List<LogEntity> entityList = findByLog(String.valueOf(cep), typeStatus, dateAtualzIni, dateAtualzFin);

            if (entityList.isEmpty()) {
                response.setCode(StatusResponseEnum.NOT_FOUND.getCode());
                response.setMessage(StatusResponseEnum.NOT_FOUND.getMessage());

            } else {
                List<LogDTO> dtoList = logMapper.toDTOList(entityList);
                response.setLogDTOList(dtoList);
                response.setCode(StatusResponseEnum.SUCCESS_RESPONSE.getCode());
                response.setMessage(StatusResponseEnum.SUCCESS_RESPONSE.getMessage());
            }
            return response;


        } catch (Exception e) {
            response.setCode(StatusResponseEnum.BAD_REQUEST.getCode());
            response.setMessage(StatusResponseEnum.BAD_REQUEST.getMessage());
            return response;

        }
    }

    private List<LogEntity> findByLog(String cep, String typeStatus, LocalDateTime dateAtualzIni, LocalDateTime dateAtualzFin) {
        if (dateAtualzIni == null && dateAtualzFin == null && typeStatus == null) {
            return logRepository.findByRequest(cep);
        } else if (dateAtualzIni == null && dateAtualzFin == null) {
            return logRepository.findByRequestAndTypeStatus(cep, Integer.valueOf(typeStatus));
        } else if (typeStatus == null) {
            return logRepository.findByRequestAndDateAtualzBetween(cep, dateAtualzIni, dateAtualzFin);
        }
        return logRepository.findByRequestAndTypeStatusAndDateAtualzBetween(cep, Integer.valueOf(typeStatus), dateAtualzIni, dateAtualzFin);
    }

    @Override
    public void postLog(LogDTO dto) {
        try {
            LogEntity entity = logMapper.toEntity(dto);
            logRepository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
