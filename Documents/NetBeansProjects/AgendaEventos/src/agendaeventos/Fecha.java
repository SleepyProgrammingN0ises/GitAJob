
package agendaeventos;
import java.util.Scanner;
import java.util.Date;
import java.util.Scanner;
/**
 * @author Miguelange
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    
    public Fecha(int day, int month, int year)  {
        this.dia = day;
        this.mes = month;
        this.anio = year;
    }
    
    public Fecha(String s) {
        String cadena[] = s.split ("-"); //del string que recibimos, va a separar los valores que esten antes y despues de los ' - ' guiones, y cada separación la guarda en una posición del vector de String.
        this.dia=Integer.parseInt(cadena[0]);
        this.mes=Integer.parseInt(cadena[1]);
        this.anio=Integer.parseInt(cadena[2]);
        
    }
    
    public Fecha (Fecha f) {
        this.dia = f.dia;
        this.mes = f.mes;
        this.anio = f.anio;
    }
    
    public static Fecha LeerFecha(){
        boolean valid;
        String fechaAux = "";
        Scanner entrada3 = new Scanner(System.in);
        do {
            try {
            valid = true;
            System.out.print("Fecha para el evento [formato DD-MM-AAAA]: ");
            fechaAux = entrada3.nextLine(); 
            } catch (Exception e) {
            System.err.println("Error en la fecha; prueba de nuevo");
            valid = false;
            }
            } while (!valid);
        Fecha aux = new Fecha(fechaAux); 
        return aux;
    }
    
    public boolean validFecha() {
        int diasMeses[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (Fecha.bisiesto(anio)) {
            diasMeses[1] = 29;
        }

        Date fe=new Date();     //mediante la liberia java.util.Date, sacamos la fecha actual del sistema creando un objeto nuevo
        String datexto = fe + "";   //pasamos a un string 'datexto' el objeto Date, dándole forma de String con las comillas
        String cadena[] = datexto.split(" ");   //pasamos a un vector de Strings el datexto (mediante el método split(), que coje y separa el string en secciones, ((detectando los espacios en blanco entre ellas, o depende el separador que le pongamos))  [Thu:Dec:02 ...etc etc] Sin los dos puntos, claro.
        int anoAct = Integer.parseInt(cadena[5]); //aqui 'PARSEAMOS' , es decir, convertimos a tipo INT, la cadena que hemos creado
        
        if ((mes > 0 && mes< 13) && (anio >= anoAct)) {
            if (dia<=diasMeses[mes-1]) {
            return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    
    public static boolean bisiesto(int a) {
         if (a % 4 == 0) {     //comprobar si es bisiesto el año, y si lo es, sumar un día en febrero
            if (a % 100 == 0) {
                if (a % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    
    
@Override
    public String toString() {      //con esto, al pedir con 'System.out.print()' el objeto, este será automaticamente convertido a un String [devolviendo lo que pongamos en el return de este método]
        return dia + "/" + mes + "/" + anio;
    }
@Override
    public Fecha clone() {          //creamos un nuevo objeto/instancia y le metemos los valores del objeto con el que hemos llamado a este método :D
        Fecha aux = new Fecha(this.dia, this.mes, this.anio);
        return aux;
    }
}
