package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.Viatico;
import com.example.gestionrrhh.service.ViaticoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viatico")
public class ViaticoController {

    private final ViaticoService service;

    public ViaticoController(ViaticoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Viatico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Viatico obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/registrar")
    public Viatico registrar(@RequestBody Viatico v) {
        return service.guardar(v);
    }

    @PutMapping("/{id}")
    public Viatico editar(@PathVariable Long id, @RequestBody Viatico v) {
        v.setId(id);
        return service.guardar(v);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}