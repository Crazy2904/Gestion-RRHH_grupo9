package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.Asistencia;
import com.example.gestionrrhh.service.AsistenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

    private final AsistenciaService service;

    public AsistenciaController(AsistenciaService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Asistencia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Asistencia obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/registrar")
    public Asistencia registrar(@RequestBody Asistencia a) {
        return service.guardar(a);
    }

    @PutMapping("/{id}")
    public Asistencia editar(@PathVariable Long id, @RequestBody Asistencia a) {
        a.setId(id);
        return service.guardar(a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
