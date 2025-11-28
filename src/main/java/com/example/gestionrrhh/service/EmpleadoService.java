package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.Empleado;
import com.example.gestionrrhh.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repo;

    public EmpleadoService(EmpleadoRepository repo) {
        this.repo = repo;
    }

    public List<Empleado> listar() { return repo.findAll(); }
    public Empleado guardar(Empleado e) { return repo.save(e); }
    public Empleado obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
