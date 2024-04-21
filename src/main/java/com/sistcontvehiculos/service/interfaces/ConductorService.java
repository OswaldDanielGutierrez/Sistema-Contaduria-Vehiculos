package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.ConductorDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConductorService {


    ConductorDTO buscarConductor(Long id) throws ConductorNotFound;

    List<ConductorDTO> listarConductores();

    ConductorDTO guardarConductor(ConductorDTO conductorDTO);

    ConductorDTO actualizarConductor(ConductorDTO conductorDTO) throws ConductorNotFound;

    String eliminarConductor(Long id) throws ConductorNotFound;
}
