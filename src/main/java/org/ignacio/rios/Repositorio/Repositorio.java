package org.ignacio.rios.Repositorio;

import java.util.List;

public interface Repositorio<T> {
    List<T> lista();
    T porId(Long id);
    void guardar(T t);
    void eliminar(Long id);

}
