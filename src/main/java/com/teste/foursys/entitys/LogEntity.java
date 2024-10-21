package com.teste.foursys.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOG_INFO")
public class LogEntity {


    @Id
    @Column (name = "id_log")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_info_seq")
    @SequenceGenerator(name = "log_info_seq", sequenceName = "log_info_seq", allocationSize = 1)
    private Long id;

    @Column (name = "user_atualz")
    private String userAtualz;

    @Column (name = "tp_status")
    private Integer typeStatus;

    @Column (name = "dt_atualz")
    private LocalDateTime dateAtualz;

    @Column (name = "ds_response")
    private String response;

    @Column (name = "ds_request")
    private String request;
}
