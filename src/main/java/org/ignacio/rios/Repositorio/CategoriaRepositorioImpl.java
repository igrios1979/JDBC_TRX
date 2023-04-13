package org.ignacio.rios.Repositorio;

import org.ignacio.rios.models.Categoria;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria>{

    private Connection getConnection() throws SQLException {
        return ConeccionBD.getInstance();
    }
    @Override
    public List<Categoria> lista() {
        List<Categoria> cat = new ArrayList<>();
        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *  FROM categorias order by 1");){
            while(rs.next()){

                Categoria c = CrearCategoria(rs);
                cat.add(c);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cat;
    }

    @Override
    public Categoria porId(Long id) {
        return null;
    }

    @Override
    public void guardar(Categoria categoria) {

    }

    @Override
    public void eliminar(Long id) {

    }




    private static Categoria CrearCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));

        return c;
    }


}
