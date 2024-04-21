package com.sistcontvehiculos.persistence.repository;

import com.sistcontvehiculos.persistence.entity.Vehiculo;
import com.sistcontvehiculos.persistence.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    List<Viaje> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT v FROM Viaje v WHERE v.vehiculo.id = :vehiculoId")
    List<Viaje> findByVehiculoId(@Param("vehiculoId") Long vehiculoId);

    @Query("SELECT sum(v.viaticos) FROM Viaje v WHERE v.vehiculo.id = :vehiculoId")
    double findByViaticos(@Param("vehiculoId") Long vehiculoId);
}
