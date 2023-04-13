import org.ignacio.rios.Repositorio.CategoriaRepositorioImpl;
import org.ignacio.rios.Repositorio.ProductoRepositorioImpl;
import org.ignacio.rios.models.Categoria;
import org.ignacio.rios.models.Producto;
import org.ignacio.rios.util.ConeccionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EjemploJDBC_TRX_2 {

    public static void main(String[] args) {

        try (Connection conn = ConeccionBD.getInstance()) {
            CategoriaRepositorioImpl categoriaRepositorio = new CategoriaRepositorioImpl();
            ProductoRepositorioImpl productoRepositorio = new ProductoRepositorioImpl();

            List<Categoria> categorias = categoriaRepositorio.lista();
            JComboBox<Categoria> categoriaComboBox = new JComboBox<>(categorias.toArray(new Categoria[0]));
            categoriaComboBox.setSelectedIndex(-1);

            JTable productosTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio", "Categoría"}, 0));

            JScrollPane scrollPane = new JScrollPane(productosTable);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            JButton listarProductosButton = new JButton("Listar productos");
            listarProductosButton.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) productosTable.getModel();
                model.setRowCount(0);
                List<Producto> productos = productoRepositorio.lista();
                productos.forEach(producto -> model.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getCategoria().getNombre()}));
            });

            JButton insertarProductoButton = new JButton("Insertar producto");
            insertarProductoButton.addActionListener(e -> {
                JTextField nombreTextField = new JTextField();
                JTextField precioTextField = new JTextField();
                JComboBox<Categoria> categoriaComboBox1 = new JComboBox<>(categorias.toArray(new Categoria[0]));

                Object[] inputFields = {
                        "Nombre:", nombreTextField,
                        "Precio:", precioTextField,
                        "Categoría:", categoriaComboBox1
                };

                int result = JOptionPane.showConfirmDialog(null, inputFields, "Insertar producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Producto producto = new Producto();
                    producto.setNombre(nombreTextField.getText());
                    producto.setPrecio(Integer.parseInt(precioTextField.getText()));
                  //  producto.setFechaRegistro(new Date());
                    producto.setCategoria((Categoria) categoriaComboBox1.getSelectedItem());
                    productoRepositorio.guardar(producto);
                }
            });

            JPanel panel = new JPanel();
            panel.add(categoriaComboBox);
            panel.add(listarProductosButton);
            panel.add(insertarProductoButton);

            JFrame frame = new JFrame("Ejemplo JDBC TRX");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(scrollPane);
            frame.add(panel, "South");
            frame.pack();
            frame.setVisible(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
