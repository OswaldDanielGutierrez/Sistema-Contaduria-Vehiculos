package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.gastos.GastosDTO;
import com.sistcontvehiculos.dto.gastos.SumaGastoPorNombreDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.GastoNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GastosService {
    List<GastosDTO> listarGastos();

    GastosDTO guardarGastos(GastosDTO gastosDTO) throws ConductorNotFound;

    GastosDTO editarGastos(Long id, String fecha) throws GastoNotFound;

    Double obtenerValorGastos();

    List<SumaGastoPorNombreDTO> listarSumaGastoPorNombre();
}
