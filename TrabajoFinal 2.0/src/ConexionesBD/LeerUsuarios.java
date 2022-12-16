package ConexionesBD;

import Clases.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerUsuarios {
    public String path = "Usuarios.dat";


    public LeerUsuarios() throws IOException {
    }

    public void leerUsuarios(ArrayList<Usuario> usu) throws IOException, ClassNotFoundException {
        ObjectInputStream fon = new ObjectInputStream(new FileInputStream(path));
        Usuario u = (Usuario) fon.readObject();
        List<String> nombre = new ArrayList<>();
        usu.add(u);
        try {
            while (u != null) {

                //System.out.println(u.getNombre());
                nombre.add(u.getNombre());
                u = (Usuario) fon.readObject();
                usu.add(u);

            }
        } catch (EOFException e) {
        }
        fon.close();
    }

    public void leerUsuariosSinGuardar() throws IOException, ClassNotFoundException {
        ObjectInputStream fon = new ObjectInputStream(new FileInputStream(path));
        Usuario u = (Usuario) fon.readObject();
        try {
            while (u != null) {

                System.out.println(u.getId() + " " + u.getNombre() + " " + u.getApellido() + " " + u.getEdad());
                u = (Usuario) fon.readObject();
            }
        } catch (EOFException e) {
        }
        fon.close();
    }
}
