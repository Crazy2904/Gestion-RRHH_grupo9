package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Evaluacion;
import com.example.gestionrrhh.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvaluacionService {
    private final EvaluacionRepository repo;

    public EvaluacionService(EvaluacionRepository repo) {
        this.repo = repo;
    }

    public List<Evaluacion> listar() { return repo.findAll(); }
    public Evaluacion guardar(Evaluacion e) { return repo.save(e); }
    public Evaluacion obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
