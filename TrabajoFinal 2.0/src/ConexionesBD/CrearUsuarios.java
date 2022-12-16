package ConexionesBD;

import Clases.Usuario;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CrearUsuarios {
    public String path = "Usuarios.dat";

    public void cargarUsuarios(ArrayList<Usuario> usu) throws IOException {
        ObjectOutputStream fon = new ObjectOutputStream(new FileOutputStream(path));
        String nombres[] = {"Raul", "Maria"};
        String apellidos[] = {"Garcia", "Perez"};
        int edad[] = {25, 20};
        for (int x = 0; x < nombres.length; x++) {
            Usuario usuario = new Usuario(x + 1, nombres[x], apellidos[x], edad[x]);
            usu.add(usuario);
            fon.writeObject(usu.get(x));
        }
    }

    public void guardarUsuarios(ArrayList<Usuario> usu) throws IOException {
        ObjectOutputStream fon = new ObjectOutputStream(new FileOutputStream(path));
        for (int x = 0; x < usu.size(); x++) {
            fon.writeObject(usu.get(x));
        }
    }


}
