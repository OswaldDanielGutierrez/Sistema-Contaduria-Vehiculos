package com.sistcontvehiculos.persistence.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Validated
@Table(name = "gastos")
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 140, nullable = false)
    private String descripcion;

    @PositiveOrZero
    @Column(nullable = false)
    private double valor;

    @Column(name = "fecha_gasto")
    private String fecha;

    @ManyToOne(targetEntity = Conductor.class, fetch = FetchType.LAZY)
    private Conductor conductor;
}

