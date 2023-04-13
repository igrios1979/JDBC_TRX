package org.ignacio.rios;

import org.ignacio.rios.Repositorio.ProductoRepositorioImpl;
import org.ignacio.rios.Repositorio.Repositorio;
import org.ignacio.rios.models.Categoria;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


public class EjemploJDBC3 {

    public static void main(String[] args) {

        try(Connection conn = ConeccionBD.getInstance()){ // hace un autoclose la virtual machine
            Repositorio<Producto> repositorio  = new ProductoRepositorioImpl();
            System.out.println("=========================|LISTAR||=========================");
            repositorio.lista().forEach(System.out::println);
            System.out.println("=========================|PORID||=========================");
            System.out.println(repositorio.porId(1L));
            System.out.println("=========================|UPDATE|=========================");
            Producto p = new Producto();
                    p.setId(1L);
                    p.setNombre("Bicicleta");
                    p.setPrecio(5621);
            Categoria categoria = new Categoria();
            categoria.setId(7L);
            p.setCategoria(categoria);


            repositorio.guardar(p);
            System.out.println("Guardado OK " );
            repositorio.lista().forEach(System.out::println);

        }catch(SQLException e){
                    e.printStackTrace();

        }




    }



}

