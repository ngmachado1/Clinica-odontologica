package com.clinicaOdontologica.clinicaOdontologica.DAO;

import java.util.List;

public interface IDao<T> {
    public T buscar(int id);
    public List<T> buscarTodos();
    public T crear(T t);
    public void eliminar(int id);
    public T actualizar(T t);

}
