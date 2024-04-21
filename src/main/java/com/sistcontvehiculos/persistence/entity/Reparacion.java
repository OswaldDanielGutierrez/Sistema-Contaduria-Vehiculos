package com.sistcontvehiculos.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Validated
@Table(name = "reparaciones")
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 140, nullable = false)
    private String descripcion;

    @PositiveOrZero
    @Column(nullable = false)
    private double valor;

    @Column(name = "fecha_reparacion")
    private String fecha;

    @ManyToOne(targetEntity = Vehiculo.class, fetch = FetchType.EAGER)
    private Vehiculo vehiculo;

}
