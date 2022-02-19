
package ejemplorandomaccess;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Miguelañez-PC
 */
public class EjemploRandomAccess {

    private static ArrayList <Long> punteros = new ArrayList <Long> ();
    
    public static void main(String[] args) {
        String fichero;
        int opc = -5;
        fichero = nombreFich();
        
        do {
        FicheroUsuario fich = new FicheroUsuario(fichero);
        
                menu();
                opc = Utiles.leerOpc();
                switch (opc) {
                    case 0:
                        System.out.println("Gracias por su tiempo :p");
                        break;
                    case 1:
                        fich.anadirUsuario();
                        break;
                    case 2: 
                        fich.listarUsuarios();
                        break;
                    case 3:
                        fich.buscarApenom();
                        break;
                    case 4:
                        fich.buscarPos();
                        break;
                    case 5:
                        if (fich.modificar()) {
                            System.out.println("Modificado.");
                        } else {
                            System.out.println("No se ha podido modificar correctamente el fichero");
                        }
                        break;
                    case 6:
                        fich.borrarApeNom();
                        break;
                    case 7:
                        fich.borrar();
                        break;
                }
            } while (opc != 0);
    }
    
    public static void menu() {
        System.out.println("-- Gestión de Archivos mediante acceso aleatorio --");
        System.out.println("\t1. Añadir Usuario");
        System.out.println("\t2. Listar Usuarios");
        System.out.println("\t3. Buscar por Nombre y Apellido");
        System.out.println("\t4. Buscar por posición");
        System.out.println("\t5. Modificar");
        System.out.println("\t6. Borrar por Nombre y Apellido");
        System.out.println("\t7. Borraro por posición");
    }
    
    public static int leerOpc() {
        int opc = -1;
        Scanner entrada1 = new Scanner(System.in);
        try {
            System.out.print("Opción: ");
            opc = entrada1.nextInt();
            
        } catch (Exception e) {
            System.out.println("ERROR al introducir la opción. Intentalo de nuevo");
        }
        return opc;
    }
    
    public static String nombreFich() {
        Scanner entrada1 = new Scanner(System.in);
        System.out.print("Fichero a editar (si no existe se creará): ");
        String fich = entrada1.nextLine();
        
        if (fich.compareTo("") != 0) {
            return fich;
        } else {
            System.out.println("Nombre no válido. Intentalo de nuevo");
            return nombreFich();
        }
    }
    
}
