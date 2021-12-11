/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaeventos;
import java.util.Scanner;
/**
 * @author Miguelange
 */
public class Evento extends Fecha {
    private String desc = "";
    private int horaini;
    private int minutini;
    private int horafin;
    private int minutfin;
    private static int NUMEVENTO = 0;
    
    public Evento(Fecha f, String desc, int horaini, int minutini, int horafin, int minutfin) { //aqui recogemos hora inicio/minuto inicio/hora fin/minuto fin ... ya que luego utilizaremos este constructor en el método LeerEvento;
        super(f.getDia(),f.getMes(),f.getAnio());
        this.desc = desc;
        this.horaini = horaini;
        this.minutini = minutini;
        this.horafin = horafin;
        this.minutfin = minutfin;
    }
    
    public static Evento leerEvent() {
        NUMEVENTO++;
        boolean valid = false;  //variable booleana 'semáforo' para los try ... catch
        String hinicio = "";
        String hfin = "";   //creamos aquí el string para evitar el error por no haberlo declarado (en el caso de que declaremos variables dentro del try, no podremos seguir utilizandolas una vez salimos de este)
        Scanner entrada1 = new Scanner(System.in);
        Fecha fe = new Fecha(Fecha.LeerFecha());
        
        System.out.print("Descripción para evento " + NUMEVENTO +": ");
        String desc = entrada1.nextLine();
        
        System.out.print("Hora Inicio [Formato HH:MM]: ");
        
        do {
            try {
                valid = true;
                hinicio = entrada1.nextLine();
                
            } catch (Exception e) {
                System.err.println("Error de lectura, prueba de nuevo");
                valid = false;
            } 
        } while (!valid);
        String cadena[] = hinicio.split(":");  // método split = coge un string y lo divide en partes, separándolas a través de una expresión (regex; en este caso ":" dos puntos) , una vez dividido, lo introduce en un vector [dado al declarar uno] 
        int horaini = Integer.parseInt(cadena[0]);  //parseamos, convirtiendo a int los dos valores dados HH y MM (horas y minutos)
        int minutini = Integer.parseInt(cadena[1]);
        
        System.out.print("Hora fin del evento [HH:MM]: ");
        
        do {
            try {
                valid = true;
                hfin = entrada1.nextLine();
                
            } catch (Exception e) {
                System.err.println("Error en lectura, prueba de nuevo");
            }
        } while (!valid);
        String cadena2[] = hfin.split(":");
        int horafin = Integer.parseInt(cadena2[0]);
        int minutfin = Integer.parseInt(cadena2[1]);
        
        Evento ev = new Evento(fe, desc, horaini, minutini, horafin, minutfin);
        if (validarH(hinicio, hfin)) {
            System.out.println("Evento 1 creado");
            return ev;
        } else {
            System.out.println("Error al crear el evento");
            return null;
        }
        
    }
    
    public static boolean validarH(String hini, String hfin) {
        String cadena[] = hini.split(":");
        if (Integer.parseInt(cadena[0]) >= 0 && Integer.parseInt(cadena[0]) <= 23) {
            if (Integer.parseInt(cadena[1]) >= 0 && Integer.parseInt(cadena[1]) <= 59) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
