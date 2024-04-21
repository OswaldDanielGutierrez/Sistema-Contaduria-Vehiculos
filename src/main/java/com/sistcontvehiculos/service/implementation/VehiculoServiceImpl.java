package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.VehiculoDTO;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.mapper.EntityMappers;
import com.sistcontvehiculos.persistence.entity.Vehiculo;
import com.sistcontvehiculos.persistence.repository.VehiculoRepository;
import com.sistcontvehiculos.service.interfaces.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EntityMappers entityMappers;

    @Override
    public VehiculoDTO buscarVehiculo(Long id) throws VehiculoNotFound {
        Optional<Vehiculo> vehiculoOpcional = vehiculoRepository.findById(id);
        Vehiculo vehiculo = vehiculoOpcional.orElseThrow(()-> new VehiculoNotFound("Vehiculo no existe"));
        return entityMappers.vehiculoToVehiculoDTO(vehiculo);
    }

    @Override
    public List<VehiculoDTO> listarVehiculos(){
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos.stream()
                .map(vehiculo -> entityMappers.vehiculoToVehiculoDTO(vehiculo))
                .collect(Collectors.toList());
    }


    @Override
    public VehiculoDTO guardarVehiculo(VehiculoDTO vehiculoDTO){
        vehiculoDTO.setPlaca(vehiculoDTO.getPlaca().toUpperCase());
        vehiculoRepository.save(entityMappers.vehiculoDtoToVehiculo(vehiculoDTO));
        return vehiculoDTO;
    }

    @Override
    public String eliminarVehiculo(Long id) throws VehiculoNotFound {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()){
            String placa = vehiculo.get().getPlaca();
            vehiculoRepository.deleteById(id);
            return "Vehículo identificado con la placa: "+placa+ "ha sido eliminado correctamente";
        } else {
            throw new VehiculoNotFound("El vehículo con el id: "+id+"no existe");
        }
    }

}
