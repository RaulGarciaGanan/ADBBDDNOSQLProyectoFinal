package ConexionesBD;

import Clases.Libro;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import java.io.File;
import java.util.ArrayList;

import static Ventanas.VentanaPrincipal.*;

public class ConexionBD {
    public String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
    public Collection col = null;
    public String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db"; //URI colección
    public String usu = "admin"; //Usuario
    public String usuPwd = "12345Abcde"; //Clave

    public void verLibros(ArrayList<Libro> libros) throws XMLDBException {
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result1 = servicio.query("for $attr in /LIBROS/EMP_ROW/@id return string($attr)");
            ResourceSet result2 = servicio.query("for $em in /LIBROS/EMP_ROW/TITULO/node() return $em");
            ResourceSet result3 = servicio.query("for $em in /LIBROS/EMP_ROW/AUTOR/node() return $em");
            ResourceSet result4 = servicio.query("for $em in /LIBROS/EMP_ROW/ANOPUBLICACION/node() return $em");
            System.out.println("Se han obtenido " + result1.getSize() + " elementos.");
            // recorrer los datos del recurso.
            ResourceIterator i1, i2, i3, i4;
            i1 = result1.getIterator();
            i2 = result2.getIterator();
            i3 = result3.getIterator();
            i4 = result4.getIterator();
            if (!i1.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i1.hasMoreResources()) {
                Resource r1 = i1.nextResource();
                Resource r2 = i2.nextResource();
                Resource r3 = i3.nextResource();
                Resource r4 = i4.nextResource();
                System.out.println("ID: " + r1.getContent().toString());
                System.out.println("Titulo: " +r2.getContent().toString());
                System.out.println("Autor: " +r3.getContent().toString());
                System.out.println("AñoPublicacion: " +r4.getContent().toString());
                Libro lib = new Libro(Integer.parseInt(r1.getContent().toString()), r2.getContent().toString(), r3.getContent().toString(), Integer.parseInt(r4.getContent().toString()));
                libros.add(lib);
            }
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public void verTituloLibros(ArrayList<String> libTitulo) throws XMLDBException {
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /LIBROS/EMP_ROW/TITULO/node() return $em");
            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println(r.getContent().toString());
                libTitulo.add(r.getContent().toString());

            }
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public void verAutorLibros(ArrayList<String> libAutor) throws XMLDBException {
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /LIBROS/EMP_ROW/AUTOR/node() return $em");
            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println(r.getContent().toString());
                libAutor.add(r.getContent().toString());

            }
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public void verAnopublicacionLibros(ArrayList<String> libAnopublicacion) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null;
        String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345Abcde"; //Clave
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /LIBROS/EMP_ROW/ANOPUBLICACION/node() return $em");
            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println(r.getContent().toString());
                libAnopublicacion.add(r.getContent().toString());

            }
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public void guardarLibro(int id, String Titulo, String Autor, int Ano) throws XMLDBException {
        int x = 0;
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            String sQuery = "update insert\n" +
                    "\t<EMP_ROW id=" + "\"" + id + "\"" + ">\n" +
                    "\t<TITULO>" + Titulo + "</TITULO>\n" +
                    "\t<AUTOR>" + Autor + "</AUTOR>\n" +
                    "\t<ANOPUBLICACION>" + Ano + "</ANOPUBLICACION>\n" +
                    "\t</EMP_ROW>\n" +
                    "into /LIBROS";

            // recorrer los datos del recurso.
            servicio.query(sQuery);
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    /**/
    public void modificarLibro(int id, String Titulo, String Autor, int Ano) throws XMLDBException {
        int x = 0;
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            String sQuery = "update replace /LIBROS/EMP_ROW[@id=\"" + id + "\"] " +
                    "  with <EMP_ROW id=" + "\"" + id + "\"" + ">\n" +
                    "<TITULO>" + Titulo + "</TITULO>\n" +
                    "<AUTOR>" + Autor + "</AUTOR>\n" +
                    "<ANOPUBLICACION>" + Ano + "</ANOPUBLICACION></EMP_ROW>";

            // recorrer los datos del recurso.
            servicio.query(sQuery);
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public void borrarLibro(int id) throws XMLDBException {
        int x = 0;
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            String sQuery = "for $em in /LIBROS/EMP_ROW[@id=\"" + id + "\"] return update delete $em";
            //for $em in /LIBROS/EMP_ROW/ID=1 return update delete $em

            // recorrer los datos del recurso.
            servicio.query(sQuery);
            col.close(); //cerramos
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public Integer ultimoID() throws XMLDBException {
        int id = 0;
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $attr in max(/LIBROS/EMP_ROW/@id) return string($attr)");
            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                //System.out.println(r.getContent().toString());
                id = Integer.parseInt(r.getContent().toString());

            }

            col.close(); //cerramos

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        return id;
    }

    public void subirArchivos() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName(driver); //Cargar del driver
        Database database = (Database) cl.newInstance(); //Instancia de la BD
        DatabaseManager.registerDatabase(database); //Registro del driver
        col = DatabaseManager.getCollection(URI, usu, usuPwd);
        if (col != null) {
            try {

                XMLResource res = null;

                res = (XMLResource) col.createResource("libros.xml", "XMLResource");
                File file = new File("libros.xml");
                res.setContent(file);
                col.storeResource(res);


                /*res = (XMLResource) col.createResource("Grupos.xml","XMLResource");
                file = new File("Grupos.xml");
                res.setContent(file);
                col.storeResource(res);*/

                for (String x : col.listResources()) {
                    System.out.println(x);
                }

                System.out.println("Se han subido los archivos satisfactoriamente");

                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar subir los datos");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }

}
