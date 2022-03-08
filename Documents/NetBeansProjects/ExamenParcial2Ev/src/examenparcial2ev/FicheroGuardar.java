package examenparcial2ev;

import java.io.*;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author miguelange
 */
public class FicheroGuardar {   // Esta clase lee con un iterador el Mapa de Personas/Pacientes, e introduce todo lo que lee en el archivo de bytes
    String fichero;
    
    public FicheroGuardar(String fichero) {
        this.fichero = fichero;
    }
 
    public void anadirColeccion(TreeMap<Integer,Persona> mapa) {
        
        try {
            DataOutputStream dos=new DataOutputStream(new FileOutputStream(this.fichero));  // directamente metemos el flujo del FileOutput al DataOutput
            TreeSet arbol = new TreeSet(mapa.values());
            Iterator it = arbol.iterator();
            while (it.hasNext()) {
                Persona pers = (Persona) it.next();         // a través del iterador va metiendo el siguiente
                dos.writeChars(Utiles.rellena(pers.getDni(), Persona.TAMDNI));  // introduce en el fichero los caracteres que va leyendo con el método Rellena
                dos.writeChars(Utiles.rellena(pers.getNombre(), Persona.TAMNOM));
                dos.writeChars(Utiles.rellena(pers.getApellidos(), Persona.TAMAPEL));
                dos.writeChars(Utiles.rellena(pers.getDireccion(), Persona.TAMDIREC));
                dos.writeInt(pers.getID());
            }
            dos.close();
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("El fichero no se ha encontrado para escribir");
        } catch (IOException ioe) {
            System.out.println("Error no podemos escribir en el fichero");
        }
    }
     
    public TreeMap<Integer,Persona> leerFichero() { //lee el fichero de bytes y devuelve un mapa con los datos
        TreeMap<Integer,Persona> mapa= new TreeMap();
       
        
        try {
            File fich=new File(fichero);
            if(!fich.exists()){
                fich.createNewFile();
            }
            DataInputStream dis=new DataInputStream(new FileInputStream(this.fichero)); // FileInput, para leer los datos del fichero; también se coloca directamente el FileInput en el DataInput

            while(dis.available()!=0){
                String dni=Utiles.leer(dis, Persona.TAMDNI);    // lee el DNI introducido, con los bytes que hemos metido
                String nombre=Utiles.leer(dis, Persona.TAMNOM);
                String apell=Utiles.leer(dis, Persona.TAMAPEL);
                String direccion=Utiles.leer(dis, Persona.TAMDIREC);
                int id=dis.readInt();

                // modificamos lo que hemos metido en las variables por los String's sin el Relleno (metido anteriormente al escribir con anadirColeccion())
                dni = Utiles.quitarRelleno(dni);    
                nombre = Utiles.quitarRelleno(nombre);
                apell = Utiles.quitarRelleno(apell);
                direccion = Utiles.quitarRelleno(direccion);

                Persona aux=new Persona(id,dni,nombre,apell,direccion);
                System.out.println(aux);
                System.out.println("/////////////////////////////////////");
                mapa.put(id, aux);
            }

            dis.close();

        } catch (FileNotFoundException nf) {
            System.out.println("El fichero no se ha encontrado para leer");
        } catch (IOException io) {
            System.out.println("Error, no podemos escribir en el fichero");
        }
        
        return mapa;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }
    
    public String getFichero() {
        return this.fichero;
    }
    
    
    
}
