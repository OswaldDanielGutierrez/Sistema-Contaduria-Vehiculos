package com.sistcontvehiculos.dto.gastos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumaGastoPorNombreDTO {

    private double valorGastos;

    private String nombreConductor;
}
