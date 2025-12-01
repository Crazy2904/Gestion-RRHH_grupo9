package com.example.gestionrrhh.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "viatico")
@Data
public class Viatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private Double monto;

    private String descripcion;

    private Boolean aprobado = false;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}