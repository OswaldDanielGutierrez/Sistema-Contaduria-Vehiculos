package com.sistcontvehiculos.controller;

import com.sistcontvehiculos.dto.ConsignacionDTO;
import com.sistcontvehiculos.persistence.entity.Consignacion;
import com.sistcontvehiculos.persistence.repository.ConsignacionRepository;
import com.sistcontvehiculos.service.interfaces.ConsignacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
public class ConsignacionController {

    @Autowired
    private ConsignacionesService consignacionesService;

    @Autowired
    private ConsignacionRepository consignacionRepository;

    @GetMapping("/bConsignacion")
    public ResponseEntity<List<ConsignacionDTO>> listConsignaciones(){
        return new ResponseEntity<>(consignacionesService.listarConsignaciones(), HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public Consignacion buscarPorFecha(){
        return consignacionRepository.buscarFuente("AYC");
    }

    @PostMapping("/gConsignaciones")
    public ResponseEntity<ConsignacionDTO> saveConsignaciones(@RequestBody ConsignacionDTO consignacionDTO){
        return new ResponseEntity<>(consignacionesService.guardarConsignaciones(consignacionDTO), HttpStatus.CREATED);
    }

}
