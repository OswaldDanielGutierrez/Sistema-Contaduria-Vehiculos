package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.reparacion.ReparacionDTO;
import com.sistcontvehiculos.exception.ReparacionNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service
public interface ReparacionService {

    List<ReparacionDTO> listarReparacion();

    ReparacionDTO guardarReparacion(ReparacionDTO reparacionDTO) throws VehiculoNotFound;

    ReparacionDTO editarReparacion(Long id, String fecha) throws ReparacionNotFound;

    Map<String, Object> listarSumaReparacionPorPlaca(@PathVariable String fechaReparacion) throws ReparacionNotFound;
}
