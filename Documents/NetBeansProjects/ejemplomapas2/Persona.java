
package ejemplomapas2;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author miguelange
 */                                                                                                                                                         // Organiza los datos según nodos que se 'balancean' automáticamente, ordenando conforme añadimos objetos                                                                                                                                 // No permite objetos duplicados
                                                                                                                       // Compara objetos mediante compareTo()
                                                                                                            
public class Persona implements Comparable {                           // Para este set es necesario implementar la interfaz Comparable (y, con esto, estamos obligados a utilizar compareTo); 
    private String nombre;                                                                                                                 //ya que sino un set no tiene manera de comparar dos objetos [ las demás colecciones sí, mediante equals() ]
    private String apellido;
    private String NIF;
    TreeSet <Persona> conjunto;    

    
    public Persona() {         //CONSTRUCTOR POR DEFECTO, hay que llamar a este para crear la coleccion en memoria
        conjunto = new TreeSet();
    }
    
    public Persona(String nom, String apel, String NIF) {
        this.nombre = nom;
        this.apellido = apel;
        this.NIF = NIF;
    }
    
    public Persona leerPersonaTreeSet() {
        String nombre = "";
        String apellido = "";
        String NIF = "";
        Scanner entrada1 = new Scanner(System.in);
        
        System.out.print("Nombre: ");
        nombre = entrada1.nextLine();
        System.out.print("Apellido: ");
        apellido=entrada1.nextLine();
        System.out.print("NIF: ");
        NIF=entrada1.nextLine();
        
        Persona aux = new Persona(nombre, apellido, NIF);
        return aux;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + ", apellido=" + apellido + ", NIF=" + NIF + '}';
    }
    
    public boolean agregarPersonaTreeSet() {    //método que devuelve un boolean
        return conjunto.add(leerPersonaTreeSet());                                                         // al poner el return, devolverá true o false; pero se seguira haciendo también la operacion de leer persona y la de añadir al listado
    }
    
    public void listarPersonaTreeSet() {
        Iterator <Persona> it = conjunto.iterator();
        Persona busq;
        while (it.hasNext()) {
            busq = it.next();
            System.out.println(busq);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNIF() {
        return NIF;
    }
    
    @Override
    public boolean equals(Object obj) {
        Persona aux = (Persona) obj;
        if (aux.getNIF() == this.NIF) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override                               //OBLIGADO sobrescribir aquí; debido a que arriba del todo implementamos la interfaz Comparable
    public int compareTo(Object obj) {
        Persona aux;
        aux = (Persona) obj;
        String cadena1 = this.nombre+this.apellido+this.NIF;
        String cadena2 = aux.getNombre()+aux.getApellido()+aux.getNIF();
        return cadena1.compareTo(cadena2);
    }
    
}
