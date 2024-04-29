package com.sistcontvehiculos.persistence.repository;

import com.sistcontvehiculos.dto.ViaticosDTO;
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

    @Query("SELECT v FROM Viaje v WHERE v.vehiculo.id = :vehiculoId ")
    List<Viaje> findViajesVehiculo(@Param("vehiculoId") Long vehiculoId);


//    @Query("SELECT v.placa, viaje.viaticos FROM Viaje viaje JOIN Vehiculo v ON Viaje.vehiculo.id = v.id WHERE viaje.fecha BETWEEN ?1 AND ?2")
    @Query("SELECT NEW com.sistcontvehiculos.dto.ViaticosDTO(v.vehiculo.placa, v.viaticos) FROM Viaje v JOIN v.vehiculo WHERE v.fecha BETWEEN :startDate AND :endDate")
    List<ViaticosDTO> findViaticosVehiculo(@Param("startDate") LocalDate inicioMes, @Param("endDate")LocalDate finMes);



}
