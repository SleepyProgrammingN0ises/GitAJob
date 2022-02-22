
package ejemplomapas2;

import java.util.*;

/**
 * @author miguelange
 */
public class EjemploMapas2 {

    public static void main(String[] args) {
        MapaProducto uso = new MapaProducto();      //al crear un nuevo MapaProducto, ya dentro de este inicializamos un mapa (su constructor crea un new TreeMap/loquesea).
        int opcion = 7;
        Scanner entrada1 = new Scanner(System.in);
        do {  
            try {
                menu();
                System.out.print("Opción: ");
                opcion = entrada1.nextInt();
                
                entrada1.nextLine(); // vaciado de buffer

                switch (opcion) {
                    case 1:
                        if (uso.agregarProd()) {    // Crea un producto usando Producto.leerProd() ; lo inserta en el mapa de tipo TreeMap <Integer, Producto>
                            System.out.println("Hecho!");  
                        } else {
                            System.err.println("Códigos iguales");
                            System.out.println("Lo sentimos, no se ha podido introducir el producto.");
                        }
                        break;
                    case 2:
                        if (uso.buscarProd()) {                                                             // Se busca si existe un producto en el mapa, introduciendo sus datos y, acto seguido, comparando esos datos
                            System.out.print("Producto encontrado...");       // introducidos con las demás entradas en el mapa; si existen dos objetos iguales, devuelve true.
                            String pause = entrada1.nextLine();
                        } else {
                            System.out.println("Lo siento. El producto no se encuentra almacenado");
                        }
                        break;
                    case 3:
                        uso.modificar();
                        break;
                    case 4:
                        uso.eliminarProd();     // Parecido al de arriba, lee un producto pidiendonos los datos, y busca si existe el producto; si lo encuentra, lo elimina.
                        break;
                    case 5:
                        if (uso.listarMapa())
                            System.out.println();
                        else {
                            System.out.println("No hay nada en el mapa/lista de productos.");
                        }
                        break;
                    case 6:
                        uso.tamanioMapa();
                        break;
                    case 7:
                        uso.listarProductosTipo();
                        break;
                    case 0:
                        System.out.println("Gracias por su tiempo :p");
                        break;
                        }
            } catch (Exception e) {
                System.err.println("Error de entrada, inténtalo de nuevo...");
            }
      } while (opcion != 0);
    }
    
    public static void menu() {
        System.out.println("////////MENU-PRODUCTOS-MAPA////////");
        System.out.println("**\t1. Agregar producto");
        System.out.println("**\t2. Buscar producto comparando");
        System.out.println("**\t3. Modificar un producto");
        System.out.println("**\t4. Eliminar un producto con datos");
        System.out.println("**\t5. Listar todos los productos");
        System.out.println("**\t6. Ver tamaño del mapa");
        System.out.println("**\t7. Listar productos por tipo");
        
        System.out.println("\t0. Salir");
    }
    
}
