package com.example.gestionrrhh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empleado empleado;

    private String fecha;   // yyyy-MM-dd
    private String horaEntrada;
    private String horaSalida;
}
