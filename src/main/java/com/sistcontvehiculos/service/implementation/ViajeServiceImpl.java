package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.ViajeDTO;
import com.sistcontvehiculos.dto.ViaticosDTO;
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
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public List<ViajeDTO> listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Viaje> viajes = viajeRepository.findByFechaBetween(fechaInicio, fechaFin);
        return viajes.stream()
                .map(viaje -> entityMappers.viajeToViajeDTO(viaje))
                .collect(Collectors.toList());
    }

    @Override
    public List<ViajeDTO> listarViajes() {
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes.stream()
                .map(viaje -> entityMappers.viajeToViajeDTO(viaje))
                .collect(Collectors.toList());
    }

    @Override
    public ViajeDTO guardarViaje(ViajeDTO viajeDTO) throws ConductorNotFound, VehiculoNotFound {

        Viaje viaje = entityMappers.viajeDtoToViaje(viajeDTO);
        viaje.setConductor(conductorRepository.findById(viajeDTO.getNroConductor())
                .orElseThrow(() -> new ConductorNotFound("Conductor no encontrado")));
        viaje.setVehiculo(vehiculoRepository.findById(viajeDTO.getNroVehiculo())
                .orElseThrow(() -> new VehiculoNotFound("Vehiculo no encontrado")));

        viajeRepository.save(viaje);
        return entityMappers.viajeToViajeDTO(viaje);
    }

    @Override
    public Map<String, Object> listarViajesPorVehiculo(@PathVariable Long vehiculoId) {
        List<Viaje> listaViajes = viajeRepository.findViajesVehiculo(vehiculoId);
        String placa = "";
        for (Viaje viaje : listaViajes) {
            placa = viaje.getVehiculo().getPlaca();
        }
        Map<String, Object> resultado = new LinkedHashMap<>();
        resultado.put("Lista de viajes del vehículo con placa: ", placa);
        resultado.put("", listaViajes);
        return resultado;
    }


    @Override
    public List<ViaticosDTO> listarViaticosPorVehiculo(String nombreMes){
        Month mes = Month.valueOf(nombreMes.toUpperCase());
        LocalDate inicioMes = LocalDate.of(2024, mes, 1);
        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
        return viajeRepository.findViaticosVehiculo(inicioMes, finMes);
    }

}
