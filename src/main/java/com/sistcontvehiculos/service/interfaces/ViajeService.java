package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.ViajeDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.persistence.entity.Viaje;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public interface ViajeService {
    List<ViajeDTO> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin);

    List<ViajeDTO> listarViajes();

    ViajeDTO guardarViaje(ViajeDTO viajeDTO) throws ConductorNotFound, VehiculoNotFound;

    List<Viaje> listarViajesPorVehiculo(@PathVariable Long vehiculoId);

    double viaticosPorCarro(@PathVariable Long vehiculoId);
}
