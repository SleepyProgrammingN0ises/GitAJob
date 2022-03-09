package ejemploserializacion;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * @author miguelange
 */
public class Utiles {   // CLASE CON MÉTODOS PARA FACILITAR Y AGILIZAR LA PROGRAMACIÓN
        
    
    public static String rellena(String palabra, int tamano) {

        int cantidad = tamano - palabra.length();
        String aux = palabra;

        for (int i = 0; i < cantidad; i++) {
            aux += ' ';
        }

        return aux;
    }

    public static String quitarRelleno(String palabra) {

        int i;
        String aux;
        for (i = palabra.length() - 1; (i >= 0) && (palabra.charAt(i) == ' '); i--);
        ;

        if (i == -1)//por si introduce todo en espacios en blanco
            return "";
        else 
            aux = palabra.substring(0, i + 1);
        

        return aux;

    }
    
        public static int leerEntero(String mensaje, String error){
        Scanner entrada =new Scanner(System.in);
        boolean valid;
        int num=0;
        do {
            valid=true;
            try {
                System.out.print(mensaje+": ");
                num=entrada.nextInt();
                
            } catch (Exception e) {
                System.out.println(error);
                entrada.nextLine();
                valid=false;
            }
        } while (!valid);
          
        return num;
      }
        
        public static double leerDouble(String mensaje, String error){
        Scanner entrada =new Scanner(System.in);
        boolean valid;
        double num=0;
        do {
            valid=true;
            try {
                System.out.print(mensaje+": ");
                num=entrada.nextDouble();
                
            } catch (Exception e) {
                System.out.println(error);
                entrada.nextLine();
                valid=false;
            }
        } while (!valid);
          
        return num;
      }
        
        public static boolean leerBooleano() {
            Scanner entrada1 = new Scanner(System.in);
            boolean valid;
            boolean res = false;
            int num=0;
            do {
                valid = true;
                System.out.println("1=Sí, 0=No");
                try {
                    num = entrada1.nextInt();
                    if (num == 0) {
                        valid = true;
                        res = false;
                    } else if (num==1) {
                        valid = true;
                        res = true;
                    } else {
                        System.out.println("Solo se permite 0 / 1");
                        valid=false;
                    }
                } catch (Exception e) {
                    System.out.println("ERROR. Debes introducir un número");
                }
            } while (!valid);
            
            return res;
 }
    
     public static String leer(DataInputStream fich, int longitud) {
        String nombre = "";
        try {

            char c;
            int contador = 0;
            do {
                c = fich.readChar();
                nombre = nombre + c;
                contador++;
            } while (contador != longitud);

        } catch (FileNotFoundException fnfe) {
            System.out.println("El fichero no se ha encontrado para leer");
        } catch (IOException ioe) {
            System.out.println("Error no podemos escribir en el fichero");
        }

        return nombre;

    }
      
      
    public static String leerTexto(String mensaje){
        Scanner entrada=new Scanner(System.in);
        System.out.print(mensaje+": ");
        String aux=entrada.nextLine();
        return aux;
    }
    
    public static int diaActual() {
        Date dia = new Date();
        String v[] = dia.toString().split(" ");
        int d = Integer.parseInt(v[2]);
        return d;
    }
}
