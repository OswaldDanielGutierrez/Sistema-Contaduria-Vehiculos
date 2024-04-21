package com.sistcontvehiculos.persistence.repository;

import com.sistcontvehiculos.dto.gastos.SumaGastoPorNombreDTO;
import com.sistcontvehiculos.persistence.entity.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long> {

    @Query("SELECT SUM(g.valor) FROM Gastos  g")
    double findValorGastos();

    @Query("SELECT NEW com.sistcontvehiculos.dto.gastos.SumaGastoPorNombreDTO(SUM(g.valor), g.conductor.nombre) FROM Gastos g GROUP BY g.conductor.nombre")
    List<SumaGastoPorNombreDTO> findGastoPorConductor();

}
