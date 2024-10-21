package com.teste.foursys.repository;

import com.teste.foursys.entitys.LogEntity;
import com.teste.foursys.service.impl.LogServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

    List<LogEntity> findByRequest (String request);
    List<LogEntity> findByRequestAndTypeStatus (String request, Integer status);

    List<LogEntity> findByRequestAndTypeStatusAndDateAtualzBetween
            (String request, Integer status, LocalDateTime from, LocalDateTime to);

    List<LogEntity> findByRequestAndDateAtualzBetween
            (String request, LocalDateTime from, LocalDateTime to);
}
