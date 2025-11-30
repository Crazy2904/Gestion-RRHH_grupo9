package com.example.gestionrrhh.service;

import com.example.gestionrrhh.entity.HorasExtra;
import com.example.gestionrrhh.repository.HorasExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorasExtraService {

    private final HorasExtraRepository repo;

    public HorasExtraService(HorasExtraRepository repo) {
        this.repo = repo;
    }

    public List<HorasExtra> listar() { return repo.findAll(); }

    public HorasExtra obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public HorasExtra guardar(HorasExtra h) {
        return repo.save(h);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}
