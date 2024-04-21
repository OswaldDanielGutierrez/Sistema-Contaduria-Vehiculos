package com.sistcontvehiculos.controller;

import com.sistcontvehiculos.dto.ConductorDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.service.interfaces.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/bConductor/{id}")
    public ResponseEntity<ConductorDTO> getConductor(@PathVariable long id) throws ConductorNotFound {
        return new ResponseEntity<>(conductorService.buscarConductor(id), HttpStatus.OK);
    }

    @GetMapping("lConductor")
    public ResponseEntity<List<ConductorDTO>> listConductor(){
        return new ResponseEntity<>(conductorService.listarConductores(), HttpStatus.OK);
    }

    @PostMapping("/gConductor")
    public ResponseEntity<ConductorDTO> saveConductor(@RequestBody ConductorDTO conductorDTO){
        return new ResponseEntity<>(conductorService.guardarConductor(conductorDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/uConductor")
    public ResponseEntity<ConductorDTO> updateConductor(@RequestBody ConductorDTO conductorDTO) throws ConductorNotFound {
        return new ResponseEntity<>(conductorService.actualizarConductor(conductorDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/dConductor")
    public ResponseEntity<String> deleteConductor(@PathVariable Long id) throws ConductorNotFound{
        return new ResponseEntity<>(conductorService.eliminarConductor(id), HttpStatus.NO_CONTENT);
    }

}
