package com.sistcontvehiculos.persistence.repository;

import com.sistcontvehiculos.dto.reparacion.SumaReparacionPorPlacaDTO;
import com.sistcontvehiculos.persistence.entity.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long> {

    @Query("SELECT NEW com.sistcontvehiculos.dto.reparacion.SumaReparacionPorPlacaDTO(SUM(r.valor), r.vehiculo.placa) FROM Reparacion r WHERE r.fecha = :fechaReparacion GROUP BY r.vehiculo.placa")
    List<SumaReparacionPorPlacaDTO> findReparacionPorPlaca(@Param("fechaReparacion") String fechaReparacion);

}
