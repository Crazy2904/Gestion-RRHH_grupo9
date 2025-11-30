package com.example.gestionrrhh.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "HorasExtra")
@Data
public class HorasExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private Double horas;

    private String motivo;

    private Boolean aprobado = false;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
