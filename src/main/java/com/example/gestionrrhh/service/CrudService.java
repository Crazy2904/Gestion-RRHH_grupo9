package com.example.gestionrrhh.service;
import java.util.List;

public interface CrudService<T> {
    List<T> listar();
    T obtenerPorId(Long id);
    T registrar(T entidad);
    T editar(Long id, T entidad);
    void eliminar(Long id);
}
