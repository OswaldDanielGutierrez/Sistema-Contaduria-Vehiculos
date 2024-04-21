package com.sistcontvehiculos.controller;

import com.sistcontvehiculos.dto.ViajeDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.persistence.entity.Viaje;
import com.sistcontvehiculos.service.interfaces.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;


    @PostMapping("/gViaje")
    public ResponseEntity<ViajeDTO> saveViaje(@RequestBody ViajeDTO viajeDTO) throws ConductorNotFound, VehiculoNotFound {
        return new ResponseEntity<>(viajeService.guardarViaje(viajeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/bViaje")
    public ResponseEntity<List<ViajeDTO>> listViajes(){
        return new ResponseEntity<>(viajeService.listarViajes(), HttpStatus.OK);
    }

    @GetMapping("/lViaje")
    public ResponseEntity<List<ViajeDTO>> listViajeFecha(@RequestParam LocalDate fechaInicio,@RequestParam LocalDate fechaFin){
        return new ResponseEntity<>(viajeService.listarPorFecha(fechaInicio, fechaFin), HttpStatus.OK);
    }

    @GetMapping("/prueb/{vehiculoId}")
    public ResponseEntity<List<Viaje>> listViajesPorVehiculo(@PathVariable Long vehiculoId) {
        return new ResponseEntity<>(viajeService.listarViajesPorVehiculo(vehiculoId), HttpStatus.OK);
    }

    @GetMapping("/viaticos/{vehiculoId}")
    public ResponseEntity<Double> findViaticos(@PathVariable Long vehiculoId){
        return new ResponseEntity<>(viajeService.viaticosPorCarro(vehiculoId), HttpStatus.OK);
    }


}
