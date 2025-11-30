package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Permiso;
import com.example.gestionrrhh.repository.PermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    private final PermisoRepository repo;

    public PermisoService(PermisoRepository repo) {
        this.repo = repo;
    }

    public List<Permiso> listar() {
        return repo.findAll();
    }

    public Permiso obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Permiso guardar(Permiso p) {
        return repo.save(p);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
