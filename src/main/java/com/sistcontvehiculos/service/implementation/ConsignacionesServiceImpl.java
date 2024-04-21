package com.sistcontvehiculos.service.implementation;

import com.sistcontvehiculos.dto.ConsignacionDTO;
import com.sistcontvehiculos.mapper.EntityMappers;
import com.sistcontvehiculos.persistence.entity.Consignacion;
import com.sistcontvehiculos.persistence.repository.ConsignacionRepository;
import com.sistcontvehiculos.service.interfaces.ConsignacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsignacionesServiceImpl implements ConsignacionesService {

    @Autowired
    private ConsignacionRepository consignacionRepository;

    @Autowired
    private EntityMappers entityMappers;

    @Override
    public List<ConsignacionDTO> listarConsignaciones(){
        List<Consignacion> consignaciones = consignacionRepository.findAll();
        return consignaciones.stream()
                .map(consignacion -> entityMappers.consignacionesToConsignacionesDTO(consignacion))
                .collect(Collectors.toList());
    }



    @Override
    public ConsignacionDTO guardarConsignaciones(ConsignacionDTO consignacionDTO){
        consignacionRepository.save(entityMappers.consignacionesDTOToConsignaciones(consignacionDTO));
        return consignacionDTO;
    }

}
