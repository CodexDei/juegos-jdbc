package org.codexdei.repositorio;

import java.util.List;

public interface Repositorio<T> {

    List<T> mostrarTabla();
    void guardar(T t);
}
