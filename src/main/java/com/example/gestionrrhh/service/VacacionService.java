package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Vacacion;
import com.example.gestionrrhh.repository.VacacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VacacionService {
    private final VacacionRepository repo;

    public VacacionService(VacacionRepository repo) {
        this.repo = repo;
    }

    public List<Vacacion> listar() { return repo.findAll(); }
    public Vacacion guardar(Vacacion e) { return repo.save(e); }
    public Vacacion obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
