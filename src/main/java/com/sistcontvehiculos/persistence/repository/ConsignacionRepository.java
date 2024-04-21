package com.sistcontvehiculos.persistence.repository;

import com.sistcontvehiculos.persistence.entity.Consignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ConsignacionRepository extends JpaRepository<Consignacion, Long> {

    @Query("select c from Consignacion c where c.fuente = ?1")
    Consignacion buscarFuente(String fuente);


}
