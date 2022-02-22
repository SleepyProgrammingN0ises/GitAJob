
package ejemplomapas2;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author miguelange
 */
public class MapaProducto {
    Map <Integer, Producto> mapa;
    
    public MapaProducto() {
        mapa = new TreeMap<Integer, Producto> ();           // TREEMAP ordena de forma 'natural'; es decir, si son números de menor a mayor. Por letra, lexicográficamente
    }
    
    public boolean agregarProd() {
        Producto aux = Producto.leerProd();
        Integer codig = aux.getCodigo();
        if (!mapa.containsKey(codig)) {
            mapa.put(codig, aux);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean buscarProd() {
        Scanner entrada1 = new Scanner(System.in);
        System.out.println("Introduce el código del producto que quieres comprobar:\n");
        int codigo = entrada1.nextInt();
        Integer codig = codigo;
        if (mapa.containsKey(codig)) {
            System.out.println("El producto está en el mapa.");
            System.out.println(mapa.get(codig));
            return true;
        } else {
            return false;
        }
    }
    
    public Producto buscarPorCod(Integer clave) {
        return mapa.get(clave);
    }
    
    public boolean listarMapa() {                                                  /* todos estos métodos, se pueden hacer más cómodos de entender; si utilizamos
                                                                                                                parámetros en los métodos (listarMapa(MapaProducto aux); y ya a partir de ahi hacer la función deseada en esta clase
                                                                                                                [que se queda más escueta toda] */
       Iterator it = mapa.keySet().iterator();

       while (it.hasNext()) {
           Integer key = (Integer) it.next();           //devuelve el SIGUIENTE elemento, el cual en un mapa, es la clave que procede
           System.out.println(mapa.get(key));
           System.out.println("////////////////////////////");
       }
       if (!mapa.isEmpty()) {
           return true;
       } else {
           return false;
       }
    }
    
    public void modificar() {
        Scanner entrada1 = new Scanner(System.in);
        System.out.print("Dime el código del producto a modificar: ");
        Integer clave = entrada1.nextInt();
        
        System.out.println(buscarPorCod(clave));        //muestro el producto buscado
        
        Producto nuevo = Producto.leerProd();
        mapa.replace(clave, nuevo);
    }
    
    public void eliminarProd() {
        Scanner entrada1 = new Scanner(System.in);
        
        System.out.println("Introduce los datos del elemento a borrar");
        Producto p1 = Producto.leerProd();
        
        if (mapa.remove(p1.getCodigo(), p1) && mapa.containsKey(p1.getCodigo())) {
            System.out.println("Producto eliminado.");
        } else {
            System.err.println("No se encontró el producto, o no se pudo eliminar. Qué pena!");
        }
    }
    
    public void listarProductosTipo() {
        Scanner entrada1 = new Scanner(System.in);
        String tipo, marca;
        int cont = 0;
        
        System.out.print("Introduce el tipo del/de los productos: ");
        tipo = entrada1.nextLine();
        
        System.out.println("Introduce la marca: ");
        marca = entrada1.nextLine();
        
        // TreeSet arbol = new TreeSet(mapa.values());
        
        Iterator it = mapa.keySet().iterator();         // en un mapa es OBLIGATORIO usar keySet para asignarle un iterador;
        
        while (it.hasNext()) {
            Integer key = (Integer) it.next();      // el it.next() apunta al metodo next() del iterador; este devuelve el SIGUIENTE ELEMENTO (en este caso, la clave de cada elem. del mapa, debido al keySet();
            Producto aux = mapa.get(key);   // conseguimos con el metodo get(key) **el Valor al que apunta la clave** que va sacando el iterador; el valor sería un Producto, en este caso
            
            if (aux.getTipo().compareTo(tipo) == 0 && aux.getMarca().compareTo(marca) == 0) {
                System.out.println(aux);
                cont++;
            }
        }
        System.out.println("\n Se han encontrado " + cont + " productos...");
    }
    
    
    public void tamanioMapa() {
        Scanner entrada1 = new Scanner(System.in);
        String sino;
        
        System.out.println("El tamaño del mapa ahora mismo es de: " + mapa.size());
        System.out.print("Ver todos?  s/n: ");
        sino = entrada1.nextLine();
        if (sino.matches("s")) {
            if (listarMapa()) {
                System.out.println("El mapa está vacio!!");
            }
        } else {
        }
    }
    
}
