package com.sistcontvehiculos.controller;


import com.sistcontvehiculos.dto.gastos.GastosDTO;
import com.sistcontvehiculos.dto.gastos.SumaGastoPorNombreDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.GastoNotFound;
import com.sistcontvehiculos.service.interfaces.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
public class GastosController {

    @Autowired
    private GastosService gastosService;


    @GetMapping("/bGastos")
    public ResponseEntity<List<GastosDTO>> listGastos(){
        return new ResponseEntity<>(gastosService.listarGastos(), HttpStatus.OK);
    }

    @GetMapping("/valorGastos")
    public ResponseEntity<Double> getValorGastos(){
        return new ResponseEntity<>(gastosService.obtenerValorGastos(), HttpStatus.OK);
    }

    @GetMapping("/listarGastosPorConductor")
    public ResponseEntity<Map<String, Object>> listGastosPorConductor(@RequestParam(value = "fechaGasto") String fechaGasto) throws GastoNotFound {
        return new ResponseEntity<>(gastosService.listarSumaGastoPorNombre(fechaGasto), HttpStatus.OK);
    }

    @PostMapping("/gGastos")
    public ResponseEntity<GastosDTO> saveGastos(@RequestBody GastosDTO gastosDTO) throws ConductorNotFound {
        return new ResponseEntity<>(gastosService.guardarGastos(gastosDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/pGastos")
    public ResponseEntity<GastosDTO> editGastos(@RequestBody GastosDTO gastosDTO) throws GastoNotFound{
        return new ResponseEntity<>(gastosService.editarGastos(gastosDTO.getId(), gastosDTO.getFecha()), HttpStatus.OK);
    }



}
