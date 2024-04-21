package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.ConductorDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.mapper.EntityMappers;
import com.sistcontvehiculos.persistence.entity.Conductor;
import com.sistcontvehiculos.persistence.repository.ConductorRepository;
import com.sistcontvehiculos.service.interfaces.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private EntityMappers entityMappers;

    @Override
    public ConductorDTO buscarConductor(Long id) throws ConductorNotFound {
        Optional<Conductor> conductorOpcional = conductorRepository.findById(id);
        Conductor conductor = conductorOpcional
                .orElseThrow( () -> new ConductorNotFound("Conductor no encontrado"));
        return entityMappers.conductorToConductoDTO(conductor);
    }

    @Override
    public List<ConductorDTO> listarConductores(){
        List<Conductor> conductores = conductorRepository.findAll();
        List<ConductorDTO> conductoresDTOS = conductores.stream()
                .map(conductor -> entityMappers.conductorToConductoDTO(conductor))
                .collect(Collectors.toList());
        return conductoresDTOS;
    }

    @Override
    public ConductorDTO guardarConductor(ConductorDTO conductorDTO){
        conductorRepository.save(entityMappers.conductorDtoToConductor(conductorDTO));
        return conductorDTO;
    }

    @Override
    public ConductorDTO actualizarConductor(ConductorDTO conductorDTO) throws ConductorNotFound {
        Conductor conductor = entityMappers.conductorDtoToConductor(conductorDTO);
        conductorRepository.save(conductor);
        return entityMappers.conductorToConductoDTO(conductor);
    }

    @Override
    public String eliminarConductor(Long id) throws ConductorNotFound{
        Optional<Conductor> conductor = conductorRepository.findById(id);
        if (conductor.isPresent()){
            String nombre = conductor.get().getNombre();
            String cedula = conductor.get().getCedula();
            return "el conductor con el "+ nombre + "y cedula " + cedula +" ha sido eliminado correctamente";
        } else {
            throw new ConductorNotFound("El conductor no existe");
        }
    }

}
