package com.sistcontvehiculos.controller;

import com.sistcontvehiculos.dto.VehiculoDTO;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.service.interfaces.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/bVehiculo")
    public ResponseEntity<VehiculoDTO> getVehiculo(@PathVariable Long id) throws VehiculoNotFound {
        return new ResponseEntity<>(vehiculoService.buscarVehiculo(id), HttpStatus.OK);
    }

    @GetMapping("/lVehiculo")
    public ResponseEntity<List<VehiculoDTO>> listVehiculo(){
        return new ResponseEntity<>(vehiculoService.listarVehiculos(), HttpStatus.OK);
    }

    @PostMapping("/gVehiculo")
    public ResponseEntity<VehiculoDTO> saveVehiculo(@RequestBody VehiculoDTO vehiculoDTO){
        return new ResponseEntity<>(vehiculoService.guardarVehiculo(vehiculoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/eVehiculo")
    public ResponseEntity<String> deleteVehiculo(@PathVariable Long id) throws VehiculoNotFound{
        return new ResponseEntity<>(vehiculoService.eliminarVehiculo(id), HttpStatus.NO_CONTENT);
    }
}
