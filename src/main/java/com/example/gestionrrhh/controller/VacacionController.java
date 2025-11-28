package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.*;
import com.example.gestionrrhh.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vacaciones")
@CrossOrigin
public class VacacionController {

    private final VacacionRepository vacacionRepo;
    private final EmpleadoRepository empleadoRepo;

    public VacacionController(VacacionRepository vacacionRepo, EmpleadoRepository empleadoRepo) {
        this.vacacionRepo = vacacionRepo;
        this.empleadoRepo = empleadoRepo;
    }

    @GetMapping("/listar")
    public List<Vacacion> listar() {
        return vacacionRepo.findAll();
    }

    @PostMapping("/registrar")
    public Vacacion registrar(@RequestBody Vacacion v) {
        if (v.getEmpleado() != null && v.getEmpleado().getId() != null) {
            Empleado emp = empleadoRepo.findById(v.getEmpleado().getId()).orElse(null);
            v.setEmpleado(emp);
        }
        return vacacionRepo.save(v);
    }

    @PutMapping("/{id}")
    public Vacacion actualizar(@PathVariable Long id, @RequestBody Vacacion v) {
        v.setId(id);
        if (v.getEmpleado() != null && v.getEmpleado().getId() != null) {
            Empleado emp = empleadoRepo.findById(v.getEmpleado().getId()).orElse(null);
            v.setEmpleado(emp);
        }
        return vacacionRepo.save(v);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        vacacionRepo.deleteById(id);
    }

    @GetMapping("/{id}")
    public Vacacion obtener(@PathVariable Long id) {
        return vacacionRepo.findById(id).orElse(null);
    }
}
