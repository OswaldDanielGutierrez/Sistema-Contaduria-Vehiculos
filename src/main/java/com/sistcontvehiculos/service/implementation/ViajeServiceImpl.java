package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.ViajeDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.mapper.EntityMappers;
import com.sistcontvehiculos.persistence.entity.Viaje;
import com.sistcontvehiculos.persistence.repository.ConductorRepository;
import com.sistcontvehiculos.persistence.repository.VehiculoRepository;
import com.sistcontvehiculos.persistence.repository.ViajeRepository;
import com.sistcontvehiculos.service.interfaces.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
//@Transactional
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EntityMappers entityMappers;

    @Override
    public List<ViajeDTO> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin){
        List<Viaje> viajes = viajeRepository.findByFechaBetween(fechaInicio, fechaFin);
        return viajes.stream()
                .map(viaje -> entityMappers.viajeToViajeDTO(viaje))
                .collect(Collectors.toList());
    }

    @Override
    public List<ViajeDTO> listarViajes(){
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes.stream()
                .map(viaje -> entityMappers.viajeToViajeDTO(viaje))
                .collect(Collectors.toList());
    }

    @Override
    public ViajeDTO guardarViaje(ViajeDTO viajeDTO) throws ConductorNotFound, VehiculoNotFound {

        Viaje viaje = entityMappers.viajeDtoToViaje(viajeDTO);
        viaje.setConductor(conductorRepository.findById(viajeDTO.getNroConductor())
                .orElseThrow(()-> new ConductorNotFound("Conductor no encontrado")));
        viaje.setVehiculo(vehiculoRepository.findById(viajeDTO.getNroVehiculo())
                .orElseThrow(()-> new VehiculoNotFound("Vehiculo no encontrado")));

        viajeRepository.save(viaje);
        return entityMappers.viajeToViajeDTO(viaje);
    }

    @Override
    public List<Viaje> listarViajesPorVehiculo(@PathVariable Long vehiculoId) {
        return viajeRepository.findByVehiculoId(vehiculoId);
    }

    @Override
    public double viaticosPorCarro (@PathVariable Long vehiculoId){
        return viajeRepository.findByViaticos(vehiculoId);
    }

}
