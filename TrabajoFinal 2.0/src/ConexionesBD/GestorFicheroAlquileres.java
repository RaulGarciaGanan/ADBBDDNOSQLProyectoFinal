package ConexionesBD;

import Clases.Alquiler;

import java.io.*;
import java.util.ArrayList;

public class GestorFicheroAlquileres {
    public String path = "Alquileres.dat";
    public void guardarAlquileres(ArrayList<Alquiler> alquileres) throws IOException {
        ObjectOutputStream fon = new ObjectOutputStream(new FileOutputStream(path));
        fon.writeObject(alquileres);

    }
    public ArrayList<Alquiler> leerAlquileres() throws IOException, ClassNotFoundException {
        ObjectInputStream fon = new ObjectInputStream(new FileInputStream(path));
        ArrayList alquileres = (ArrayList) fon.readObject();
        for(int x=0;x<alquileres.size();x++){
            System.out.println(alquileres.get(x).toString());
        }
        fon.close();
        return alquileres;
    }
}
