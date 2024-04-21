package com.sistcontvehiculos.mapper;

import com.sistcontvehiculos.dto.*;
import com.sistcontvehiculos.dto.gastos.GastosDTO;
import com.sistcontvehiculos.dto.reparacion.ReparacionDTO;
import com.sistcontvehiculos.persistence.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EntityMappers {

    public ConductorDTO conductorToConductoDTO(Conductor conductor){
        ConductorDTO conductorDTO = new ConductorDTO();
        BeanUtils.copyProperties(conductor, conductorDTO);
        return conductorDTO;
    }

    public Conductor conductorDtoToConductor(ConductorDTO conductorDTO){
        Conductor conductor = new Conductor();
        BeanUtils.copyProperties(conductorDTO, conductor);
        return conductor;
    }

    public ViajeDTO viajeToViajeDTO (Viaje viaje){
        ViajeDTO viajeDTO = new ViajeDTO();
        BeanUtils.copyProperties(viaje, viajeDTO);
        return viajeDTO;
    }

    public Viaje viajeDtoToViaje (ViajeDTO viajeDTO){
        Viaje viaje = new Viaje();
        BeanUtils.copyProperties(viajeDTO, viaje);
        return viaje;
    }

    public VehiculoDTO vehiculoToVehiculoDTO(Vehiculo vehiculo){
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        BeanUtils.copyProperties(vehiculo, vehiculoDTO);
        return vehiculoDTO;
    }

    public Vehiculo vehiculoDtoToVehiculo(VehiculoDTO vehiculoDTO){
        Vehiculo vehiculo = new Vehiculo();
        BeanUtils.copyProperties(vehiculoDTO, vehiculo);
        return vehiculo;
    }

    public ConsignacionDTO consignacionesToConsignacionesDTO(Consignacion consignacion){
        ConsignacionDTO consignacionDTO = new ConsignacionDTO();
        BeanUtils.copyProperties(consignacion, consignacionDTO);
        return consignacionDTO;
    }

    public Consignacion consignacionesDTOToConsignaciones(ConsignacionDTO consignacionDTO){
        Consignacion consignacion = new Consignacion();
        BeanUtils.copyProperties(consignacionDTO, consignacion);
        return consignacion;
    }

    public GastosDTO gastosToGastosDTO(Gastos gastos){
        GastosDTO gastosDTO = new GastosDTO();
        BeanUtils.copyProperties(gastos, gastosDTO);
        return gastosDTO;
    }

    public Gastos gastosDtoToGastos(GastosDTO gastosDTO){
        Gastos gastos = new Gastos();
        BeanUtils.copyProperties(gastosDTO, gastos);
        return gastos;
    }

    public ReparacionDTO reparacionToReparacionDTO (Reparacion reparacion){
        ReparacionDTO reparacionDTO = new ReparacionDTO();
        reparacionDTO.setId(reparacion.getId());
        reparacionDTO.setDescripcion(reparacion.getDescripcion());
        reparacionDTO.setValor(reparacion.getValor());
        reparacionDTO.setFecha(reparacion.getFecha());
        reparacionDTO.setPlaca(reparacion.getVehiculo().getPlaca());
        return reparacionDTO;
    }

    public Reparacion reparacionDtoToReparacion(ReparacionDTO reparacionDTO){
        Reparacion reparacion = new Reparacion();
        reparacion.setId(reparacionDTO.getId());
        reparacion.setDescripcion(reparacionDTO.getDescripcion());
        reparacion.setValor(reparacionDTO.getValor());
        reparacion.setFecha(reparacionDTO.getFecha());
        return reparacion;
    }






























}
