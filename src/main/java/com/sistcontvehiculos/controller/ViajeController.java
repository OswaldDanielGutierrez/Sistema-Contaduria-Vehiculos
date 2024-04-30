package com.sistcontvehiculos.controller;

import com.sistcontvehiculos.dto.ViajeDTO;
import com.sistcontvehiculos.dto.ViaticosDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.service.interfaces.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
@CrossOrigin(value = "http://localhost:4200")
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

    @GetMapping("/buscarViajesFecha")
    public ResponseEntity<List<ViajeDTO>> listViajeFecha(@RequestParam LocalDate fechaInicio,@RequestParam LocalDate fechaFin){
        return new ResponseEntity<>(viajeService.listarPorFecha(fechaInicio, fechaFin), HttpStatus.OK);
    }

    @GetMapping("/listarViajesVehiculo/{vehiculoId}")
    public ResponseEntity<Map<String, Object>> listViajesPorVehiculo(@PathVariable Long vehiculoId) {
        return new ResponseEntity<>(viajeService.listarViajesPorVehiculo(vehiculoId), HttpStatus.OK);
    }

    @GetMapping("/listarViaticos")
    public ResponseEntity<List<ViaticosDTO>> listViaticosVehiculo(@RequestParam String nombreMes){
        return new ResponseEntity<>(viajeService.listarViaticosPorVehiculo(nombreMes), HttpStatus.OK);
    }

}
