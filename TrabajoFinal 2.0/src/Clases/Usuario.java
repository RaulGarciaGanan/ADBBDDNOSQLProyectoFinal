package Clases;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String Nombre;
    private String Apellido;
    private int Edad;

    public Usuario(int id, String nombre, String apellido, int edad) {
        this.id = id;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Edad=" + Edad +
                '}';
    }
}
