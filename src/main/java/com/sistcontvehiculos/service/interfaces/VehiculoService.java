package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.VehiculoDTO;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehiculoService {
    VehiculoDTO buscarVehiculo(Long id) throws VehiculoNotFound;

    List<VehiculoDTO> listarVehiculos();

    VehiculoDTO guardarVehiculo(VehiculoDTO vehiculoDTO);

    String eliminarVehiculo(Long id) throws VehiculoNotFound;
}
