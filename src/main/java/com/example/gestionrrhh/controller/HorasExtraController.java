package com.example.gestionrrhh.controller;

import com.example.gestionrrhh.entity.HorasExtra;
import com.example.gestionrrhh.service.HorasExtraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horasextra")
public class HorasExtraController {

    private final HorasExtraService service;

    public HorasExtraController(HorasExtraService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<HorasExtra> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public HorasExtra obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/registrar")
    public HorasExtra registrar(@RequestBody HorasExtra h) {
        return service.guardar(h);
    }

    @PutMapping("/{id}")
    public HorasExtra editar(@PathVariable Long id, @RequestBody HorasExtra h) {
        h.setId(id);
        return service.guardar(h);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
