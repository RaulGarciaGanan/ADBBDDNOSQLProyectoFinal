package Ventanas;

import Clases.Usuario;
import ConexionesBD.CrearUsuarios;
import ConexionesBD.GestorFicheroUsuarios;
import ConexionesBD.LeerUsuarios;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import static Ventanas.VentanaPrincipal.*;

public class Usuarios {
    private JFrame frame;
    private JPanel Usuarios;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfEdad;
    private JButton bResgistrar;
    private JButton bCancelar;
    private JButton bEliminar;
    private JButton bModificar;
    private JList lUsuarios;

    public Usuarios() throws IOException, ClassNotFoundException {
        frame = new JFrame("Usuarios");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setContentPane(Usuarios);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GestorFicheroUsuarios gUsu = new GestorFicheroUsuarios();

        CrearUsuarios cUsu = new CrearUsuarios();
        LeerUsuarios lUsu = new LeerUsuarios();

        lUsuarios.setModel(modelList(usu));


        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal vent = new VentanaPrincipal();
                frame.setVisible(false);
            }
        });
        bResgistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                Usuario u = new Usuario(usu.size()+1,tfNombre.getText(),tfApellidos.getText(),Integer.parseInt(tfEdad.getText()));
                usu.add(u);

                    cUsu.guardarUsuarios(usu);
                    //lUsu.leerUsuarios(usu);
                } catch (IOException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"La edad tiene que ser numerico");
                    ex.printStackTrace();
                }

                tfNombre.setText("");
                tfApellidos.setText("");
                tfEdad.setText("");
                lUsuarios.setModel(modelList(usu));
            }
        });
        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = lUsuarios.getSelectedIndex();
                usu.get(pos).setNombre(tfNombre.getText());
                usu.get(pos).setApellido(tfApellidos.getText());
                usu.get(pos).setEdad(Integer.parseInt(tfEdad.getText()));

                try {
                    gUsu.guardarUsuarios(usu);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }



            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usu.remove(lUsuarios.getSelectedIndex());
                try {
                    gUsu.guardarUsuarios(usu);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                lUsuarios.setModel(modelList(usu));

            }
        });
        lUsuarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = lUsuarios.getSelectedIndex();
                if (index != -1){
                    tfNombre.setText(usu.get(index).getNombre().toString());
                    tfApellidos.setText(usu.get(index).getApellido().toString());
                    tfEdad.setText(Integer.toString(usu.get(index).getEdad()));
                } else{
                    tfNombre.setText("");
                    tfApellidos.setText("");
                    tfEdad.setText("");
                }

            }
        });
    }
    private DefaultListModel modelList(ArrayList<Usuario> usu){
        DefaultListModel model = new DefaultListModel<ArrayList>();
        for (int j = 0; j < usu.size(); j++) {
            model.add(j,usu.get(j).getNombre() );
        }
        return model;
    }
}
