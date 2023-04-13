package org.ignacio.rios;

import org.ignacio.rios.Repositorio.ProductoRepositorioImpl;
import org.ignacio.rios.Repositorio.Repositorio;
import org.ignacio.rios.models.Categoria;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EjemploJDBC_TRX {

    public static void main(String[] args) {

        try(Connection conn = ConeccionBD.getInstance()){

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println(" ------------------------=Listar------------------ " );
       //     repositorio.lista().forEach(System.out::println);

            System.out.println("=========================|INSERTANDO|=========================");
            Producto p = new Producto();
            // p.setId(1L);
            p.setNombre("Espuma de afeitar");
            p.setPrecio(12);
            p.setFechaRegistro(new Date());
            Categoria cat = new Categoria();
            cat.setId(4L);
            p.setCategoria(cat);
          //  repositorio.guardar(p);
            System.out.println("Guardado OK " );
           // repositorio.lista().forEach(System.out::println);

            System.out.println("=========================|UPDATE|=========================");
            Producto pr = new Producto();
            pr.setId(40L);
            pr.setNombre("Teclado Gammer");
            pr.setPrecio(5623);
            Categoria categoria = new Categoria();
            categoria.setId(1L);
            pr.setCategoria(categoria);
            repositorio.guardar(pr);
            repositorio.guardar(p);
            System.out.println("Guardado OK " );
            repositorio.lista().forEach(System.out::println);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
