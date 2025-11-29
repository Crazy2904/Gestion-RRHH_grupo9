package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Asistencia;
import com.example.gestionrrhh.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaService {

    private final AsistenciaRepository repo;

    public AsistenciaService(AsistenciaRepository repo) {
        this.repo = repo;
    }

    public List<Asistencia> listar() { return repo.findAll(); }

    public Asistencia obtener(Long id) { return repo.findById(id).orElse(null); }

    public Asistencia guardar(Asistencia a) { return repo.save(a); }

    public void eliminar(Long id) { repo.deleteById(id); }
}
