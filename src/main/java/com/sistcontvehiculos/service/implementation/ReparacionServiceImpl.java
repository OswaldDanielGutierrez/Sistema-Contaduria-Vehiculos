package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.reparacion.ReparacionDTO;
import com.sistcontvehiculos.dto.reparacion.SumaReparacionPorPlacaDTO;
import com.sistcontvehiculos.exception.ReparacionNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.mapper.EntityMappers;
import com.sistcontvehiculos.persistence.entity.Reparacion;
import com.sistcontvehiculos.persistence.repository.ReparacionRepository;
import com.sistcontvehiculos.persistence.repository.VehiculoRepository;
import com.sistcontvehiculos.service.interfaces.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReparacionServiceImpl implements ReparacionService {

    @Autowired
    private ReparacionRepository reparacionRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EntityMappers entityMappers;


    @Override
    public List<ReparacionDTO> listarReparacion(){
        List<Reparacion> reparaciones = reparacionRepository.findAll();

        return reparaciones.stream()
                .map(reparacion -> {
                            ReparacionDTO reparacionDTO = entityMappers.reparacionToReparacionDTO(reparacion);
                            reparacionDTO.setPlaca(reparacion.getVehiculo().getPlaca());
                            return reparacionDTO;
                        })
                .collect(Collectors.toList());
    }

    @Override
    public ReparacionDTO guardarReparacion(ReparacionDTO reparacionDTO) throws VehiculoNotFound {
        Reparacion reparacion = entityMappers.reparacionDtoToReparacion(reparacionDTO);

        reparacion.setVehiculo(vehiculoRepository.findById(reparacionDTO.getNroVehiculo())
                .orElseThrow(()-> new VehiculoNotFound("Vehiculo no encontrado")));
        reparacionRepository.save(reparacion);
        return entityMappers.reparacionToReparacionDTO(reparacion);
    }

    @Override
    public ReparacionDTO editarReparacion(Long id, String fecha) throws ReparacionNotFound {
        Reparacion reparacion = reparacionRepository.findById(id)
                .orElseThrow(()-> new ReparacionNotFound("Reparacion no encontrada"));

        reparacion.setFecha(fecha);
        return entityMappers.reparacionToReparacionDTO(reparacion);
    }

    @Override
    public Map<String, Object> listarSumaReparacionPorPlaca(@PathVariable String fechaReparacion) throws ReparacionNotFound {
        List<SumaReparacionPorPlacaDTO> reparaciones = reparacionRepository.findReparacionPorPlaca(fechaReparacion);
        if (reparaciones.isEmpty()){
            throw new ReparacionNotFound("No hay una lista de reparaciones para esta fecha: "+fechaReparacion);
        }

        double valorTotal = 0;
        for (SumaReparacionPorPlacaDTO reparacion: reparaciones){
            valorTotal += reparacion.getValorReparacion();
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("reparaciones", reparaciones);
        respuesta.put("valor total: ", valorTotal);

        return respuesta;

    }

}
