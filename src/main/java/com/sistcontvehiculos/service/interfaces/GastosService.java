package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.gastos.GastosDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.GastoNotFound;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service
public interface GastosService {
    List<GastosDTO> listarGastos();

    GastosDTO guardarGastos(GastosDTO gastosDTO) throws ConductorNotFound;

    GastosDTO editarGastos(Long id, String fecha) throws GastoNotFound;

    Double obtenerValorGastos();

    Map<String, Object> listarSumaGastoPorNombre(@PathVariable String fechaGasto) throws GastoNotFound;
}
