package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.*;
import com.example.gestionrrhh.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin
public class EmpleadoController {

    private final EmpleadoRepository empleadoRepo;
    private final DepartamentoRepository departamentoRepo;

    public EmpleadoController(EmpleadoRepository empleadoRepo, DepartamentoRepository departamentoRepo) {
        this.empleadoRepo = empleadoRepo;
        this.departamentoRepo = departamentoRepo;
    }

    @GetMapping("/listar")
    public List<Empleado> listar() {
        return empleadoRepo.findAll();
    }

    @PostMapping("/registrar")
    public Empleado registrar(@RequestBody Empleado e) {
        if (e.getDepartamento() != null && e.getDepartamento().getId() != null) {
            Departamento dep = departamentoRepo.findById(e.getDepartamento().getId()).orElse(null);
            e.setDepartamento(dep);
        }
        return empleadoRepo.save(e);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado e) {
        e.setId(id);
        if (e.getDepartamento() != null && e.getDepartamento().getId() != null) {
            Departamento dep = departamentoRepo.findById(e.getDepartamento().getId()).orElse(null);
            e.setDepartamento(dep);
        }
        return empleadoRepo.save(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoRepo.deleteById(id);
    }

    @GetMapping("/{id}")
    public Empleado obtener(@PathVariable Long id) {
        return empleadoRepo.findById(id).orElse(null);
    }
}
