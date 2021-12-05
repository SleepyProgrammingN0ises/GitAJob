/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoempresaclases;
import java.util.Scanner;

/**
 * @author miguelange
 */
public class DNI {
    private long numero;
    private char letra;
    

    private DNI(long numero, char letra) {
        this.numero = numero;
        if (DNIValido()) {
            this.letra = letra;
        }
    }
    
    private DNI (String s) { //el parametro debe tener numero y letra
        
        String aux=s.toUpperCase(); //pasamos a mayúsculas toda la cadena (los números no son afectados, claro)
        this.letra=aux.charAt(s.length()-1);
        aux= s.substring(0, s.length()-1);   //substring(firstIndex, lastIndex) nos pilla el primer index que queramos, y el último que queramos MENOS 1 ;
        this.numero=Long.parseLong(aux); //convertimos a long la parte de los números (que estaba escrito en un String);
        
        
    }
    
    
    
    private boolean DNIValido () {
       char letras[] = {'T','R',  'W', 'A',  'G',  'M',  'Y',  'F',  'P',  'D',  'X',    'B',   'N',   'J',    'Z',    'S',   'Q',    'V',   'H',    'L',   'C',   'K',   'E'};
       int pos = (int) (numero % 23);
       char resletra = letras[pos];
       
       if (this.letra==resletra) {
           return true;
       } else {
           return false;
       }
    }
    
    public static DNI leerDNI() {
        DNI aux;
        String tempo;
        
        Scanner entrada1 = new Scanner(System.in);
        do {
        System.out.print("NIF: ");
        tempo = entrada1.nextLine();
        aux= new DNI(tempo);
        if (!aux.DNIValido())
                System.out.println("No es válido, intentalo de nuevo.\n");
        } while (!aux.DNIValido());
        
        return aux;
    }

    //SIN SETTERS NI GETTERS, pues siempre queremos que el DNI que nos de el usuario sea válido (Pase por la verificacion en leerDNI() );
    
    public String toString() {
        return "" + numero + letra;
    }
    
}
