
package ejemploserializacion;

/**
 * @author miguelange
 */
public class EjemploSerializacion {

    private static ArchivoObjetos arch = new ArchivoObjetos("archivoObj.dat");
    private static ArrayListObjetos arr = new ArrayListObjetos();
    
    public static void main(String[] args) {
        //Solo se pueden acceder a archivos de forma secuencial
        int opc = 7;
        do {
            menu();
            try {
                opc = Utiles.leerEntero("Opcion", "Error. Introduce un número válido");
                switch (opc) {
                    case 1:
                        Clase1 aux = Clase1.leerObjeto();
                        if (arr.add(aux)) {
                            System.out.println("Objeto añadido!");
                        } else {
                            System.out.println("No se pudo añadir el objeto...");
                        }
                    break;
                    case 2:
                        arr.list();
                        break;
                    case 3:
                        arch.volcarAFichero(arr.getArray());
                        break;
                    case 4:
                        
                }
            }catch (Exception e) {
                System.out.println("ERROR de entrada");
            }
            
        } while (opc != 0);
        
    }
    
    public static void menu() {
        System.out.println("Menu");
        System.out.println("1. Añadir objetos");
        System.out.println("2. Listar objetos");
        System.out.println("3. Volcar listado a Fichero");
        System.out.println("0. Salir");
    }
    
}
