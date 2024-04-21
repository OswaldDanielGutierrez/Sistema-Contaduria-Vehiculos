package com.sistcontvehiculos.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ViajeDTO {

    private Long id;

    private String despacho;

    private String destino;

    private double gananciaViaje;

    private double viaticos;

    private LocalDate fecha;

    private Long nroConductor;

    private Long nroVehiculo;

}
