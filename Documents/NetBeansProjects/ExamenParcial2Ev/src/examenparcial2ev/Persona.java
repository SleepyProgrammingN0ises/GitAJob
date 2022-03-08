
package examenparcial2ev;

import java.io.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author miguelange
 */

                                                                    // implementamos la interfaz Comparable, ya que es obligatorio para sobrescribir compareTo(), y en el TreeMap para comparar Personas
public class Persona implements Comparable {    
    private String dni;
    private String apellidos;
    private String nombre;
    private String direccion;
    private static final String FICHDIA= "contadordia"+ Utiles.diaActual() + ".dat";
    
    private int id;
    public static final int TAMDNI=18, TAMAPEL=80, TAMNOM=40, TAMDIREC=510;   
    /*tamaños para los punteros de
        DNI = 18 (9 caracteres)
        APELLIDOS = 80 (40 caracteres)
        NOMBRE = 40 (20 caract.)
        DIRECCION = 510 (255 caract.)*/
    
    public Persona(String dni, String ap, String nom, String direc) {
        int cont = leerContadorPersonas();
        if (cont != -1 || cont >= 30) {
            cont++;
            id = cont;
            if (!escribirContador(cont)) {
                System.out.println("Error al escribir el contador de ID's");
                System.exit(1);
            }
        } else {
            System.exit(1);
        }
        this.dni = dni;
        this.apellidos = ap;
        this.nombre = nom;
        this.direccion = direc;
    }
    
    public Persona(int id, String dni, String ap, String nom, String direc) {
        this.id = id;
        this.dni = dni;
        this.apellidos = ap;
        this.nombre = nom;
        this.direccion = direc;
    }
    
      public static Persona leerPersona() {
            Scanner entrada1 = new Scanner(System.in);
            
          System.out.print("DNI del paciente: ");
            String dni = entrada1.nextLine();
          System.out.print("Nombre:  ");
            String nom = entrada1.nextLine();
          System.out.print("Apellido: ");
            String apellido = entrada1.nextLine();
          System.out.print("Su dirección: ");
            String direccion = entrada1.nextLine();
          Persona aux = new Persona(dni, nom, apellido, direccion);
          
          if (aux.getID() >= 30) {  // con esto comprobamos que no se introducen más de 30 personas
              System.out.println("Límite de usuarios alcanzado...");
              aux = null;
          }
        return aux;
        }
    
        private static int leerContadorPersonas(){ // Lee el número del fichero, si no existe este último lo crea y le introduce un 0; 
                                                                                                 // en otro caso, lee el contador
                                                                                                     // cuando termina de hacerlo todo, devuelve el número leído
        int contador=-1;

        File fichero=new File(FICHDIA);
        if(!fichero.exists()){  // si el fichero *no existe*
            try{
                fichero.createNewFile();//lo crea vacio
                FileOutputStream fos=new FileOutputStream(fichero);
                DataOutputStream dos=new DataOutputStream(fos);
                dos.writeInt(0);

                dos.close();
                fos.close();
            }catch(FileNotFoundException fnfe){
                System.out.println("No se ha encontrado el fichero(escribir)");
            }catch(IOException ioe){
                System.out.println("Error en la escritura del fichero "+fichero);
            }
        }

        try{
            FileInputStream fis =new FileInputStream(fichero);
            DataInputStream dis = new DataInputStream(fis);
            contador=dis.readInt();
            
            dis.close();
            fis.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("No se ha encontrado el fichero(leer)");
        }catch(IOException ioe){
            System.out.println("Error en la lectura del fichero "+fichero);
        }

        return contador;
    }
        
       public static boolean escribirContador(int cont) {

        boolean escrito=false;
        File fichero=new File(FICHDIA);
        
            try{
                if(!fichero.exists()){          // Aquí compruebo si el fichero existe con antelación; si no existe lo creamos nuevo, y si existe no hacemos nada
                    fichero.createNewFile();
                }
                FileOutputStream fos=new FileOutputStream(fichero); // abrimos el flujo del fichero
                DataOutputStream dos=new DataOutputStream(fos); // cargamos el dataOutputStream
                dos.writeInt(cont); // escribo el contador en el fichero

                dos.close();
                fos.close();
                escrito=true;
            }catch(FileNotFoundException fnfe){
                System.out.println("Fallo al acceder al fichero para escribir el contador");
            }catch(IOException ioe){
                System.out.println("Error al intentar escribir en el fichero --> "+fichero);
            }
        
        return escrito;
    }

    public int getID() {
        return id;
    }
       
    public String getDni() {
        return dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
       
       

    @Override
    public int hashCode() {
        String cad1 = this.apellidos + this.dni;
        String cad2 = this.apellidos + this.dni;
        int hash = cad1.hashCode() + cad2.hashCode();   // esto suma los dos hashcode's (el resultante de la cadena1 más el de la cadena2)
        return hash;    //... que después devolvemos
    }

    @Override
    public boolean equals(Object obj) {
        Persona aux = (Persona) obj;
        if (aux.id == this.id) {
        return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int compareTo (Object o) {
        Persona p = (Persona) o;
        String cadena1 = this.nombre+this.apellidos;
        String cadena2 = p.nombre+this.apellidos;
        return cadena1.compareTo(cadena2);
    }

    @Override
    public String toString() {
        return "Persona " + id + "{" + "dni=" + dni + ", apellidos=" + apellidos + ", nombre=" + nombre + ", direccion=" + direccion + " }";
    }
    
    
    
    
        
        
}
