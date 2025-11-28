package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.Departamento;
import com.example.gestionrrhh.repository.DepartamentoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin
public class DepartamentoController {

    private final DepartamentoRepository departamentoRepo;

    public DepartamentoController(DepartamentoRepository departamentoRepo) {
        this.departamentoRepo = departamentoRepo;
    }

    @GetMapping("/listar")
    public List<Departamento> listar() {
        return departamentoRepo.findAll();
    }

    @PostMapping("/registrar")
    public Departamento registrar(@RequestBody Departamento d) {
        return departamentoRepo.save(d);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @RequestBody Departamento d) {
        d.setId(id);
        return departamentoRepo.save(d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        departamentoRepo.deleteById(id);
    }

    @GetMapping("/{id}")
    public Departamento obtener(@PathVariable Long id) {
        return departamentoRepo.findById(id).orElse(null);
    }
}
