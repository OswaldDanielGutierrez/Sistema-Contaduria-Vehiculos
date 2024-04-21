package com.sistcontvehiculos.dto;

import lombok.Data;

@Data
public class ConductorDTO {

    private Long id;

    private String nombre;

    private String cedula;

    private Integer edad;

    private double sueldo;

}
