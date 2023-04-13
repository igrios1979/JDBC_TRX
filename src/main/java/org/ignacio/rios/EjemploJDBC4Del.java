package org.ignacio.rios;

import org.ignacio.rios.Repositorio.ProductoRepositorioImpl;
import org.ignacio.rios.Repositorio.Repositorio;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;

import java.sql.Connection;
import java.sql.SQLException;


public class EjemploJDBC4Del {

    public static void main(String[] args) {

        try(Connection conn = ConeccionBD.getInstance()){ // hace un autoclose la virtual machine
            Repositorio<Producto> repositorio  = new ProductoRepositorioImpl();
            System.out.println("=========================|LISTAR||=========================");
            repositorio.lista().forEach(System.out::println);
            System.out.println("=========================|PORID||=========================");
            System.out.println(repositorio.porId(1L));
            System.out.println("=========================|ELIMINAR|=========================");


            repositorio.eliminar(19L);
            System.out.println("Eliminado........... OK " );
            repositorio.lista().forEach(System.out::println);

        }catch(SQLException e){
                    e.printStackTrace();

        }




    }



}

