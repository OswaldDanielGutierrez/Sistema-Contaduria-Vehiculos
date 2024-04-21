package com.sistcontvehiculos.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String despacho;

    @Column(length = 40, nullable = false)
    private String destino;


    @Column(length = 40, name = "ganancia_viaje")
    private double gananciaViaje;

    @PositiveOrZero
    private double viaticos;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;

    @JsonIgnore
    @ManyToOne(targetEntity = Conductor.class)
    private Conductor conductor;

    @JsonIgnore
    @ManyToOne(targetEntity = Vehiculo.class)
    private Vehiculo vehiculo;
}
