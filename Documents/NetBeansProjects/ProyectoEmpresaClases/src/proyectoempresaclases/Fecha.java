
package proyectoempresaclases;
import java.util.Date;
/**
 * @author miguelange
 */
public class Fecha {
    private int dia;
    private int mes;
    private int ano;
    
    public Fecha (int day, int month, int year) {
        dia = day;
        mes = month;
        ano = year;
    }
    
    public Fecha (String fe) {
        String cadena[] = fe.split();
    }
    
    public static boolean bisiesto(int nAno) {
        if (nAno % 4 == 0) {
            if (nAno % 100 == 0) {
                if (nAno % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
    
    public boolean validarF() {
        int diasMeses[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (diasMeses[(mes-1)] == 28 && Fecha.bisiesto(ano)) {
            diasMeses[1] = 29;
        }
        
       
        
        if (dia <= diasMeses[(mes-1)] && dia > 0) {
            if (mes <= 12 && mes > 0) {
                if (ano <= 2023 && ano > 1900) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Fecha clone() {
        Fecha aux = new Fecha
    }
    
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}
