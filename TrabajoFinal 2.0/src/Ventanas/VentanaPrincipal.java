package Ventanas;

import Clases.*;
import ConexionesBD.*;
import org.xmldb.api.base.XMLDBException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaPrincipal {

    static ConexionBD con;

    static GestorFicheroAlquileres gAlq;
    static GestorFicheroUsuarios gUsu;
    private JFrame frame;
    private JButton bBasedeDatos;
    private JPanel ventanaPrincipal;
    private JButton bListadoAlquiler;
    private JButton bModificarUsuario;

    public static ArrayList<Usuario> usu = new ArrayList<Usuario>();
    public static ArrayList<Libro> lib = new ArrayList<>();
    public static ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
    public static ArrayList<String> libTitulo = new ArrayList<>();
    public static ArrayList<String> libAutor = new ArrayList<>();
    public static ArrayList<String> libAnopublicacion = new ArrayList<>();

    public VentanaPrincipal() {
        frame = new JFrame("Ventana Principal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setContentPane(ventanaPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);





        bBasedeDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasedeDatos bas = new BasedeDatos();
                frame.setVisible(false);
            }
        });
        bModificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuarios usu = new Usuarios();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);

            }
        });
        bListadoAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alquileres alqui = new Alquileres();
                frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

        VentanaPrincipal vent = new VentanaPrincipal();

        //Cargamos de la base de datos a diferentes arrays todos los datos del los libros
        //Mi consulta jokin es si se puede cargar la base de datos a un objeto para aprovechar recursos ya que asi solo necesitaria un array y no 3

        con = new ConexionBD();
        con.verLibros(lib);
        con.subirArchivos();
        /*con.verTituloLibros(libTitulo);
        con.verAutorLibros(libAutor);
        con.verAnopublicacionLibros(libAnopublicacion);*/


        gAlq = new GestorFicheroAlquileres();
        gUsu = new GestorFicheroUsuarios();

        alquileres = gAlq.leerAlquileres();
        //usu = gUsu.leerUsuarios();
        CrearUsuarios cUsu = new CrearUsuarios();
        cUsu.cargarUsuarios(usu);



    }
}
