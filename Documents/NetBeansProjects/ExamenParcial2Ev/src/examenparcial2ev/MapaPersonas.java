
package examenparcial2ev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author miguelange
 */
public class MapaPersonas {
    private TreeMap <Integer, Persona> mapa;
    
    public MapaPersonas() {
        mapa = new TreeMap <Integer, Persona>();
    }
    
    public boolean agregarPersona(Persona aux) {
        boolean hecho = false;
        if (aux != null) {
        mapa.put(aux.getID(), aux);
        hecho = true;
        } else {
            System.out.println("ERROR. No se admiten más personas en este día");
            hecho = false;
        }
        
        return hecho;
    }
    
    public void listarMapa() {
        Iterator it = mapa.keySet().iterator();
        
        while (it.hasNext()) {
            Integer id = (Integer) it.next();   // pasa al siguiente ID
            Persona aux = mapa.get(id); // recoge la persona según el ID leido
            System.out.println(aux); // muestra por pantalla (usando toString()) la persona recogida del mapa
        }
    }
    
   public boolean buscarID() {
       boolean encontrado = false;
       
       int IDs = Utiles.leerEntero("Introduce el ID");
       Iterator it = mapa.keySet().iterator();
       
       while (it.hasNext() && !encontrado) {
           Integer id = (Integer) it.next();
           if (id.compareTo(IDs) == 0) {
               System.out.println(mapa.get(id));
               System.out.println("//////////////////////////////////////////");
               encontrado = true;
           }
       }
       
       return encontrado;
   }
   
    public boolean eliminarID() {
        
        int cod = Utiles.leerEntero("Inserta un ID a BORRAR");
        if (mapa.isEmpty()) {
            System.out.println("El mapa está vacío; inserta personas e intentalo de nuevo");
            return false;
        }
        if (mapa.containsKey(cod)) {
            mapa.remove(cod);
            return true;
        } else {
            return false;
        }

    }
    
    public boolean limpiarMapa() {
        boolean hecho = false;
        
        if (!mapa.isEmpty()) {
            mapa.clear();
            System.out.println("Eliminado correctamente.");
            hecho = true;
        } else {
            System.out.println("El mapa está vacío ya; inserta algunas personas e intentalo de nuevo...");
            hecho = false;
        }
        return hecho;
    }
    
    public void listarList() {
        ArrayList array = new ArrayList(mapa.values());
        Iterator it = array.iterator();
        int i = 0;
        
        while (it.hasNext()) {
            Persona aux = (Persona) it.next();
            System.out.println(aux); // muestra por pantalla (usando toString()) la persona recogida del mapa
        }
        
    }

    public TreeMap<Integer, Persona> getMapa() {
        return mapa;
    }
    
    
    
    
    
    
}
