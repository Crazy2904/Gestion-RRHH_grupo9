package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.*;
import com.example.gestionrrhh.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/evaluaciones")
@CrossOrigin
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepo;
    private final EmpleadoRepository empleadoRepo;

    public EvaluacionController(EvaluacionRepository evaluacionRepo, EmpleadoRepository empleadoRepo) {
        this.evaluacionRepo = evaluacionRepo;
        this.empleadoRepo = empleadoRepo;
    }

    @GetMapping("/listar")
    public List<Evaluacion> listar() {
        return evaluacionRepo.findAll();
    }

    @PostMapping("/registrar")
    public Evaluacion registrar(@RequestBody Evaluacion ev) {
        if (ev.getEmpleado() != null && ev.getEmpleado().getId() != null) {
            Empleado emp = empleadoRepo.findById(ev.getEmpleado().getId()).orElse(null);
            ev.setEmpleado(emp);
        }
        return evaluacionRepo.save(ev);
    }

    @PutMapping("/{id}")
    public Evaluacion actualizar(@PathVariable Long id, @RequestBody Evaluacion ev) {
        ev.setId(id);
        if (ev.getEmpleado() != null && ev.getEmpleado().getId() != null) {
            Empleado emp = empleadoRepo.findById(ev.getEmpleado().getId()).orElse(null);
            ev.setEmpleado(emp);
        }
        return evaluacionRepo.save(ev);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        evaluacionRepo.deleteById(id);
    }

    @GetMapping("/{id}")
    public Evaluacion obtener(@PathVariable Long id) {
        return evaluacionRepo.findById(id).orElse(null);
    }
}
