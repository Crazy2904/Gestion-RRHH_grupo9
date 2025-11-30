package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Viatico;
import com.example.gestionrrhh.repository.ViaticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaticoService {

    private final ViaticoRepository repo;

    public ViaticoService(ViaticoRepository repo) {
        this.repo = repo;
    }

    public List<Viatico> listar() { return repo.findAll(); }

    public Viatico obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Viatico guardar(Viatico v) {
        return repo.save(v);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}