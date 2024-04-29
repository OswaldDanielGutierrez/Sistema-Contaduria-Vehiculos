package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.gastos.GastosDTO;
import com.sistcontvehiculos.dto.gastos.SumaGastoPorNombreDTO;
import com.sistcontvehiculos.exception.ConductorNotFound;
import com.sistcontvehiculos.exception.GastoNotFound;
import com.sistcontvehiculos.mapper.EntityMappers;
import com.sistcontvehiculos.persistence.entity.Gastos;
import com.sistcontvehiculos.persistence.repository.ConductorRepository;
import com.sistcontvehiculos.persistence.repository.GastosRepository;
import com.sistcontvehiculos.service.interfaces.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class GastosServiceImpl implements GastosService {

    @Autowired
    private GastosRepository gastosRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private EntityMappers entityMappers;

    @Override
    public List<GastosDTO> listarGastos(){
        List<Gastos> gastos = gastosRepository.findAll();
        return gastos.stream().map(gasto -> entityMappers.gastosToGastosDTO(gasto)).collect(Collectors.toList());
    }


    @Override
    public GastosDTO guardarGastos(GastosDTO gastosDTO) throws ConductorNotFound{
        Gastos gastos = entityMappers.gastosDtoToGastos(gastosDTO);

        gastos.setConductor(conductorRepository.findById(gastosDTO.getNroConductor())
                .orElseThrow(()-> new ConductorNotFound("Conductor no encontrado")));

        gastosRepository.save(gastos);
        return entityMappers.gastosToGastosDTO(gastos);
    }

    @Override
    public GastosDTO editarGastos(Long id, String fecha) throws GastoNotFound {
        Gastos gastos = gastosRepository.findById(id)
                .orElseThrow(()-> new GastoNotFound("Gasto no encontrado"));

        gastos.setFecha(fecha);
        return entityMappers.gastosToGastosDTO(gastos);
    }

    @Override
    public Double obtenerValorGastos(){
        return gastosRepository.findValorGastos();
    }

    @Override
    public Map<String, Object> listarSumaGastoPorNombre(@PathVariable String fechaGasto) throws GastoNotFound {
        List<SumaGastoPorNombreDTO> gastos = gastosRepository.findGastoPorConductor(fechaGasto);
        if (gastos.isEmpty()){
            throw new GastoNotFound("No hay una lista de gastos para esta fecha: "+fechaGasto);
        }

        double sumaGastos = 0;
        for (SumaGastoPorNombreDTO elemento: gastos){
            sumaGastos += elemento.getValorGastos();
        }
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Gastos: ", gastos);
        respuesta.put("Valor total: ", sumaGastos);

        return respuesta;
    }


}
