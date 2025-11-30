package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.Permiso;
import com.example.gestionrrhh.service.PermisoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permisos")
@CrossOrigin("*")
public class PermisoController {

    private final PermisoService service;

    public PermisoController(PermisoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Permiso> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Permiso obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/registrar")
    public Permiso registrar(@RequestBody Permiso p) {
        return service.guardar(p);
    }

    @PutMapping("/{id}")
    public Permiso editar(@PathVariable Long id, @RequestBody Permiso p) {
        p.setId(id);
        return service.guardar(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
