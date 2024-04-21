package com.sistcontvehiculos.controller;


import com.sistcontvehiculos.dto.reparacion.ReparacionDTO;
import com.sistcontvehiculos.dto.reparacion.SumaReparacionPorPlacaDTO;
import com.sistcontvehiculos.exception.ReparacionNotFound;
import com.sistcontvehiculos.exception.VehiculoNotFound;
import com.sistcontvehiculos.service.interfaces.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/SCV")
public class ReparacionController {

    @Autowired
    private ReparacionService reparacionService;

    @GetMapping("/bReparacion")
    public ResponseEntity<List<ReparacionDTO>> listReparaciones(){
        return new ResponseEntity<>(reparacionService.listarReparacion(), HttpStatus.OK);
    }

    @GetMapping("/reparacionPorVehiculo")
    public ResponseEntity<List<SumaReparacionPorPlacaDTO>> listar(@RequestParam(value = "fechaReparacion") String fechaReparacion){
        return new ResponseEntity<>(reparacionService.listarSumaReparacionPorPlaca(fechaReparacion), HttpStatus.OK);
    }

    @PostMapping("/gReparacion")
    public ResponseEntity<ReparacionDTO> saveReparacion(@RequestBody ReparacionDTO reparacionDTO) throws VehiculoNotFound {
        return new ResponseEntity<>(reparacionService.guardarReparacion(reparacionDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/pReparacion")
    public ResponseEntity<ReparacionDTO> editReparacion(@RequestBody ReparacionDTO reparacionDTO) throws ReparacionNotFound{
        return new ResponseEntity<>(reparacionService.editarReparacion(reparacionDTO.getId(), reparacionDTO.getFecha()), HttpStatus.OK);
    }



}
