package com.sistcontvehiculos.dto.reparacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumaReparacionPorPlacaDTO {

    private Double valorReparacion;
    private String vehiculo;

}
