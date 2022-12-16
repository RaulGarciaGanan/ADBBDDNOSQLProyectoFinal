package Ventanas;

import Clases.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static Ventanas.VentanaPrincipal.*;

public class Alquileres {
    private JFrame frame;
    private JPanel Alquileres;
    private JButton alquilarButton;
    private JButton bEliminar;
    private JButton bSalir;
    private JComboBox cbUsu;
    private JComboBox cbLibro;
    private JList lAlquileres;

    public Alquileres() {
        frame = new JFrame("Alquileres");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setContentPane(Alquileres);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        rellenarCBUsu(cbUsu, usu);
        rellenarCBLib(cbLibro, lib);


        lAlquileres.setModel(modelList(alquileres));
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal vent = new VentanaPrincipal();
                frame.setVisible(false);
            }
        });
        alquilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alquiler alq = new Alquiler(alquileres.size() + 1, cbUsu.getSelectedIndex(),cbLibro.getSelectedIndex());
                alquileres.add(alq);
                try {
                    gAlq.guardarAlquileres(alquileres);
                    gAlq.leerAlquileres();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                lAlquileres.removeAll();
                lAlquileres.setModel(modelList(alquileres));
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alquileres.remove(lAlquileres.getSelectedIndex());
                try {
                    gAlq.guardarAlquileres(alquileres);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                lAlquileres.removeAll();
                lAlquileres.setModel(modelList(alquileres));
            }
        });
        lAlquileres.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = lAlquileres.getSelectedIndex();
                if (index != -1) {
                    bEliminar.setEnabled(true);
                } else {
                    bEliminar.setEnabled(false);
                }
            }
        });
    }

    private void rellenarCBLib(JComboBox cbLibro, ArrayList<Libro> lib) {
        for (int x = 0; lib.size() > x; x++) {
            cbLibro.addItem(lib.get(x).getTitulo());
        }
    }

    private void rellenarCBUsu(JComboBox cbUsu, ArrayList<Usuario> usu) {
        for (int x = 0; usu.size() > x; x++) {
            cbUsu.addItem(usu.get(x).getNombre());
        }
    }

    private DefaultListModel modelList(ArrayList<Alquiler> alquileres) {
        DefaultListModel model = new DefaultListModel<ArrayList>();
        for (int j = 0; j < alquileres.size(); j++) {
            model.add(j, buscarTitutlo(alquileres.get(j).getIdLibro()) + " ---- " + buscarNombre(alquileres.get(j).getIdUsu()));
        }
        return model;
    }

    private Object buscarNombre(int idUsu) {
        return usu.get(idUsu).getNombre();
    }

    private Object buscarTitutlo(int idLibro) {
        return lib.get(idLibro).getTitulo();
    }


}
