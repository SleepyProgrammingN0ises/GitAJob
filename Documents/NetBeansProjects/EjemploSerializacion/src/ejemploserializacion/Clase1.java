
package ejemploserializacion;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author miguelange
 */
public class Clase1 implements Serializable {      // serializable permite guardar directamente los objetos creados con esta clase en ficheros
    int ai;
    double ad;
    boolean ab;
    String as;

    public Clase1(int ai, double ad, boolean ab, String as) {
        this.ai = ai;
        this.ad = ad;
        this.ab = ab;
        this.as = as;
    }
    
    public static Clase1 leerObjeto() {
        Scanner entrada1 = new Scanner(System.in);
        
        Clase1 aux;
        int ent = Utiles.leerEntero("Introduce un entero", "debe ser entero");
        double dob = Utiles.leerDouble("Introduce un doble", "debe ser doble");
        String str = Utiles.leerTexto("Introduce un texto");
        boolean bl = Utiles.leerBooleano();
        
        aux = new Clase1(ent, dob, false, str);
        
        return aux;
    }

    public int getAi() {
        return ai;
    }

    public double getAd() {
        return ad;
    }

    public boolean isAb() {
        return ab;
    }

    public String getAs() {
        return as;
    }

    @Override
    public String toString() {
        return "Clase1{" + " ai=" + ai + ", ad=" + ad + ", ab=" + ab + ", as=" + as + " }";
    }
    
    
}
