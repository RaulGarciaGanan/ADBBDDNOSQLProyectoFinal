package Ventanas;

import Clases.Libro;
import ConexionesBD.ConexionBD;
import org.xmldb.api.base.XMLDBException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Ventanas.VentanaPrincipal.*;

public class BasedeDatos {

    ConexionBD conexion;
    private JFrame frame;
    private JPanel BasedeDatos;
    private JTextField tfNombre;
    private JTextField tfAutor;
    private JTextField tfAño;
    private JButton bGuardar;
    private JButton bModificar;
    private JButton bEliminar;
    private JButton bSalir;
    private JList lLibros;

    public BasedeDatos() {
        frame = new JFrame("Base de Datos");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setContentPane(BasedeDatos);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        lLibros.setModel(modelList(lib));
        conexion = new ConexionBD();
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal vent = new VentanaPrincipal();
                frame.setVisible(false);
            }
        });
        lLibros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = lLibros.getSelectedIndex();
                if (index != -1) {
                    bGuardar.setEnabled(false);
                    tfNombre.setText(lib.get(index).getTitulo());
                    tfAutor.setText(lib.get(index).getAutor());
                    tfAño.setText(String.valueOf(lib.get(index).getAnoPublicacion()));
                } else {
                    bGuardar.setEnabled(true);
                    tfNombre.setText("");
                    tfAutor.setText("");
                    tfAño.setText("");
                }
            }
        });
        bGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    conexion.guardarLibro(con.ultimoID()+1, tfNombre.getText(), tfAutor.getText(), Integer.parseInt(tfAño.getText()));
                    /*libTitulo.clear();
                    libAutor.clear();
                    libAnopublicacion.clear();
                    con.verTituloLibros(libTitulo);
                    con.verAutorLibros(libAutor);
                    con.verAnopublicacionLibros(libAnopublicacion);*/
                    lib.clear();
                    con.verLibros(lib);
                    tfNombre.setText("");
                    tfAutor.setText("");
                    tfAño.setText("");
                } catch (NumberFormatException | XMLDBException n) {
                    JOptionPane.showMessageDialog(null, "El año debe ser numerico");
                    throw new RuntimeException(n);
                }
                lLibros.removeAll();
                lLibros.setModel(modelList(lib));
            }
        });
        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = 0;
                String titulo = "";
                try {
                    titulo = (String) lLibros.getSelectedValue();
                    indice =  obtenerIDJlist(titulo);
                    conexion.modificarLibro(indice , tfNombre.getText(), tfAutor.getText(), Integer.parseInt(tfAño.getText()));
                    /*libTitulo.clear();
                    libAutor.clear();
                    libAnopublicacion.clear();
                    con.verTituloLibros(libTitulo);
                    con.verAutorLibros(libAutor);
                    con.verAnopublicacionLibros(libAnopublicacion);*/
                    lib.clear();
                    con.verLibros(lib);
                    tfNombre.setText("");
                    tfAutor.setText("");
                    tfAño.setText("");
                } catch (NumberFormatException | XMLDBException n) {
                    JOptionPane.showMessageDialog(null, "El año debe ser numerico");
                    throw new RuntimeException(n);
                }
                lLibros.removeAll();
                lLibros.setModel(modelList(lib));
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = 0;
                String titulo = "";
                try {
                    //indice = lLibros.getSelectedIndex();
                    titulo = (String) lLibros.getSelectedValue();
                    indice =  obtenerIDJlist(titulo);
                    conexion.borrarLibro(indice);
                    /*libTitulo.clear();
                    libAutor.clear();
                    libAnopublicacion.clear();
                    con.verTituloLibros(libTitulo);
                    con.verAutorLibros(libAutor);
                    con.verAnopublicacionLibros(libAnopublicacion);*/
                    lib.clear();
                    con.verLibros(lib);
                } catch (XMLDBException ex) {
                    throw new RuntimeException(ex);
                }
                lLibros.removeAll();
                lLibros.setModel(modelList(lib));
            }
        });
    }

    public Integer obtenerIDJlist(String titulo){
        int id = 0;
        for(int x= 0; x<lib.size();x++){
            if(titulo.equals(lib.get(x).getTitulo())){
                id = lib.get(x).getId();
            }
        }
        return id;
    }

    private DefaultListModel modelList(ArrayList<Libro> libTitulo) {
        DefaultListModel model = new DefaultListModel<ArrayList>();
        for (int j = 0; j < libTitulo.size(); j++) {
            model.add(j, libTitulo.get(j).getTitulo());
        }
        return model;
    }
}
