package com.sistcontvehiculos.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Validated
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 7, nullable = false, unique = true)
    private String placa;

    @OneToMany(targetEntity = Reparacion.class, fetch = FetchType.LAZY, mappedBy = "vehiculo")
    private List<Reparacion> listaReparaciones;

    @OneToMany(targetEntity = Viaje.class, fetch = FetchType.LAZY, mappedBy = "vehiculo")
    private List<Viaje> listaViajes;

}
