package org.ignacio.rios.models;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class Producto {

    private Long id;
    private Integer precio;
    private Date fechaRegistro;
    private String nombre;
    private Categoria categoria;
    private String sku;

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", precio=" + precio +
                ", fechaRegistro=" + fechaRegistro +
                ", nombre='" + nombre + '\'' +
                ",categoria= " + categoria.getNombre() +
                '}';
    }


}
