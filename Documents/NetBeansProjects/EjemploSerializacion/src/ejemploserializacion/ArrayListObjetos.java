
package ejemploserializacion;

import java.util.ArrayList;

/**
 * @author miguelange
 */
public class ArrayListObjetos {
    private ArrayList <Clase1> array;

    public ArrayListObjetos() {
        this.array = new ArrayList <Clase1>();
    }
    
    public boolean add (Clase1 obj) {
        return array.add(obj);
    }
    
    public void list() {
        if (array.size()!=0) {
            System.out.println("LISTA DE OBJETOS (" + array.size() + ")");
            for (int i=0; i<array.size();i++) {
            System.out.println(array.get(i));
            }
        } else if (array.isEmpty()) {
            System.out.println("El ArrayList esta vacío.");
        }
    }
    
    public ArrayList <Clase1> getArray() {
        return this.array;
    }
    
    public Clase1 get(int ind) {
        return array.get(ind);
    }
    
    public void vaciar() {
        if (!array.isEmpty()) {
            array.clear();
        }
    }
    
    public int size() {
        return array.size();
    }
}


