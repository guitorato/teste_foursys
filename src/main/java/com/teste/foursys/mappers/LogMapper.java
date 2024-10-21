package com.teste.foursys.mappers;

import com.teste.foursys.entitys.LogEntity;
import com.teste.foursys.models.LogDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    List<LogDTO> toDTOList(List<LogEntity> entity );
    LogEntity toEntity(LogDTO dto);
}
