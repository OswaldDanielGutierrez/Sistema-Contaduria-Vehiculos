package com.sistcontvehiculos.dto.gastos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistcontvehiculos.persistence.entity.Conductor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GastosDTO {

    private Long id;

    private String descripcion;

    private double valor;

    private Long nroConductor;

    private String fecha;

}
