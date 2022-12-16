package Clases;

import java.io.Serializable;

public class Alquiler implements Serializable {
    private int id;
    private int idUsu;
    private int idLibro;

    public Alquiler(int id, int idUsu, int idLibro) {
        this.id = id;
        this.idUsu = idUsu;
        this.idLibro = idLibro;
    }

    public int getId() {
        return id;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public int getIdLibro() {
        return idLibro;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "id=" + id +
                ", idUsu=" + idUsu +
                ", idLibro=" + idLibro +
                '}';
    }
}
