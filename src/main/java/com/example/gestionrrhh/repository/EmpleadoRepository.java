package com.example.gestionrrhh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionrrhh.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
