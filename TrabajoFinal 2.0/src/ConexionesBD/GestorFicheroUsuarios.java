package ConexionesBD;

import Clases.Usuario;

import java.io.*;
import java.util.ArrayList;

public class GestorFicheroUsuarios {
    public String path = "Usuarios.dat";

    public void guardarUsuarios(ArrayList<Usuario> usu) throws IOException {
        ObjectOutputStream fon = new ObjectOutputStream(new FileOutputStream(path));
        fon.writeObject(usu);
        fon.close();
    }
    public ArrayList<Usuario> leerUsuarios() throws IOException, ClassNotFoundException {
        ObjectInputStream fon = new ObjectInputStream(new FileInputStream(path));
        ArrayList usu = (ArrayList) fon.readObject();

        fon.close();
        return usu;
    }
}
