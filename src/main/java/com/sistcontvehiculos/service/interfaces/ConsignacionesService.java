package com.sistcontvehiculos.service.interfaces;

import com.sistcontvehiculos.dto.ConsignacionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsignacionesService {
    List<ConsignacionDTO> listarConsignaciones();


    ConsignacionDTO guardarConsignaciones(ConsignacionDTO consignacionDTO);
}
