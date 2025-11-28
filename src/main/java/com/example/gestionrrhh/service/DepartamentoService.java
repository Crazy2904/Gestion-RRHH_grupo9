package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Departamento;
import com.example.gestionrrhh.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartamentoService {
    private final DepartamentoRepository repo;

    public DepartamentoService(DepartamentoRepository repo) {
        this.repo = repo;
    }

    public List<Departamento> listar() { return repo.findAll(); }
    public Departamento guardar(Departamento e) { return repo.save(e); }
    public Departamento obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
