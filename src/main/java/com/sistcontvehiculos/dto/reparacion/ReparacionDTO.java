package com.sistcontvehiculos.dto.reparacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(value = {"nroVehiculo"})
public class ReparacionDTO {

    private Long id;

    private String descripcion;

    private double valor;

    private Long nroVehiculo;

    private String fecha;

    private String placa;


}
