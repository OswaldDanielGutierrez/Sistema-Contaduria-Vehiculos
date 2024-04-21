package com.sistcontvehiculos.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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
@Table(name = "conductor")
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nombre;

    @Column(length = 15, nullable = false, unique = true)
    private String cedula;

    private Integer edad;

    @Positive
    @Column(nullable = false)
    private double sueldo;


    @OneToMany(targetEntity = Viaje.class, fetch = FetchType.LAZY, mappedBy = "conductor")
    private List<Viaje> listaViajes;


    @OneToMany(targetEntity = Gastos.class, fetch = FetchType.LAZY, mappedBy = "conductor")
    private List<Gastos> listaGastos;


}
