package com.sistcontvehiculos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsignacionDTO {

    private Long id;

    private LocalDate fecha;

    private String referencia;

    private double valor;

    private String fuente;
}
