
package ejemplorandomaccess;        // CLASE CON MÉTODOS 'UTILES' , se puede importar para ahorrar tiempo

import java.io.RandomAccessFile;
import java.util.Scanner;

public abstract class Utiles {
    
    public static boolean iguales (String c1, String c2) { //compara si dos cadenas son iguales, sin mirar Mayúsculas
            if (c1.compareToIgnoreCase(c2) == 0) {
                return true;
            } else {
                return false;
            }
        }
    
    public static String leer(RandomAccessFile dis, int cantidad) {
        // lee x caracteres del RandomAccessFile (hasta el numero de caracteres/bytes que le demos en cantidad)
    String nombre = "";
    char c;
    int contador = 0;
    
     try {   
        do {
            c = dis.readChar(); 
            nombre = nombre + c;
            contador++;
        } while (contador != cantidad);
     } catch (Exception e) {
         System.out.println("error");
     }
    
    return nombre;
}
    
    public static int leerOpc() {   // lee una opción numérica; ideal para SWITCH !!!!
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
    
    public static String nombreFich() {         // lee el nombre de un fichero. Si el nombre está vacio no dejará introducirlo y volverá a ejecutarse
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
    
    public static String rellena(String palabra, int tamano) {

        int quedan = tamano - palabra.length();
        String aux = palabra;

        for (int i = 0; i < quedan; i++) {
            aux += ' ';
        }

        return aux;
    }
    
    public static String quitarRelleno (String palabRellena) {
        
        int i;
        String aux;
        for (i = palabRellena.length() - 1; (i >= 0) && (palabRellena.charAt(i) == ' '); i--);          // va contando la posición desde el final

        if (i == -1) {    //por si introduce todo en espacios en blanco
            return "";
        } else {
            aux = palabRellena.substring(0, i + 1);
            aux.toUpperCase();
        }

        return aux;

    }
    
    
}
