package org.ignacio.rios;

import org.ignacio.rios.Repositorio.ProductoRepositorioImpl;
import org.ignacio.rios.Repositorio.Repositorio;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjemploJDBC_TRX {

    public static void main(String[] args) {

        try(Connection conn = ConeccionBD.getInstance()){

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println(" ------------------------=Listar------------------ " );
            repositorio.lista().forEach(System.out::println);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
