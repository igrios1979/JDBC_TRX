package org.ignacio.rios.Repositorio;

import org.ignacio.rios.models.Categoria;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto>{

private Connection getConnection() throws SQLException {
    return ConeccionBD.getInstance();

}
    @Override
    public List<Producto> lista() { // lee en base los datos y devuelve una lista con los datos del select
    List<Producto> productos = new ArrayList<>();
    try(Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT p.*,c.nombre as cat FROM Productos p INNER JOIN categorias c ON p.categoria_id = c.id");){
        while(rs.next()){
            Producto p = CrearProducto(rs);
            productos.add(p);
        }
    }catch(SQLException e){
          e.printStackTrace();
    }
        return productos;
    }

    @Override
    public Producto porId(Long id)
    {
        Producto producto = null;

        try(PreparedStatement stmt = getConnection().prepareStatement("SELECT p.*,c.nombre as cat FROM Productos p INNER JOIN categorias c ON p.categoria_id = c.id WHERE p.id = ?"))
        {
            stmt.setLong(1,id);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    producto = CrearProducto(res);

                }


            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId()!= null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre = ?, precio=?,categoria_id=? where id=? ";
        } else {
            sql = "INSERT INTO productos(nombre,precio,categoria_id,fecha)VALUES(?,?,?,?)";
        }

        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
              stmt.setString(1,producto.getNombre());
              stmt.setLong(2,producto.getPrecio());
              if(producto.getCategoria()!=null) {
                  stmt.setLong(3, producto.getCategoria().getId());
              }else{
                  stmt.setNull(3, java.sql.Types.INTEGER);
              }
            if (producto.getId()!=null && producto.getId()>0) {
                stmt.setLong(4, producto.getId());
            }else{
                stmt.setDate(4,new Date(producto.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public void eliminar(Long id) {

    String sql = "DELETE  FROM productos where id = ?";
    try(PreparedStatement stmtdel = getConnection().prepareStatement(sql) ) {
        stmtdel.setLong(1,id);
        stmtdel.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

    private static Producto CrearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha"));

        Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("categoria_id"));
            categoria.setNombre(rs.getString("cat"));
         p.setCategoria(categoria);
        return p;
    }
}
