
package examenparcial2ev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @author miguelange
 */
public class ExamenParcial2Ev {

    public static Calendar c1 = Calendar.getInstance(); // variable est�tica que devolver� una instancia de GregorianCalendar
    public static MapaPersonas tree = new MapaPersonas();
    public static FicheroGuardar save = new FicheroGuardar("dia" + Utiles.diaActual() + ".dat");    //con esto, el nombre del fichero cambia cada vez que cambia el d�a
    
    public static void main(String[] args) {
        Scanner entrada1 = new Scanner(System.in);
        int opc = -1;
        do {
                menu();
                opc = Utiles.leerEntero("Opci�n");
                switch (opc) {
                    case 1: // Hace un set al calendar, con la fecha del sistema actual
                        c1.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                        break;
                    case 2: // Cambia el dia del sistema con el set; S�lo cambia el d�a a trav�s del metodo gregorianCalendar, el resto se queda igual; cambio puramente est�tico
                        int dia = Utiles.leerEntero("Dia del mes");
                        c1.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), dia);
                        break;
                    case 3: //A�ade una persona al treeMap
                        if (tree.agregarPersona(Persona.leerPersona())) {
                            System.out.println("\nPersona a�adida!");
                        } else {
                            System.out.println("No se ha podido a�adir la persona; l�mite alcanzado o datos err�neos");
                        }
                        break;
                    case 4: // Lista las personas en orden alfab�tico
                        subMenuAZ();
                        break;
                    case 5: // Lista las personas seg�n orden de llegada (ID)
                        subMenuListar();
                        break;
                    case 6: // Buscar paciente/usuario por ID
                        if(tree.buscarID()) {
                            System.out.println();
                        } else {
                            System.out.println("Ese ID no existe en el mapa");
                        }
                        break;
                    case 7: // Eliminar paciente/usuario por ID
                        if (tree.eliminarID()) {
                            System.out.println("Persona eliminada del listado correctamente");
                        } else {
                            System.out.println("Eliminado err�neo");
                        }
                        break;
                    case 8: // Borrar todos los pacientes;; eliminar todo el mapa
                        String opcion = Utiles.leerTexto("�Estas seguro de querer eliminarlo TODO en este d�a? (s/n)");
                        if (opcion.compareToIgnoreCase("s") == 0) {
                                tree.limpiarMapa();
                                File fi = new File(save.getFichero());
                                try {
                                    if (fi.exists()) {
                                        fi.delete();    
                                    //Borrar tambi�n los ficheros de contador de la clase persona.
                                        fi.createNewFile();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error de entrada/salida");
                                }
                        } else {
                            System.out.println("Abortar misi�n");
                        }
                    case 9: // A�ade todo el mapa creado al fichero 'dia03.dat'; le paso el mapa como par�metro entre par�ntesis.
                        save.anadirColeccion(tree.getMapa());
                        save.leerFichero();
                        break;
                    case 10:    // Saca por pantalla todas las personas del fichero de bytes
                        save.leerFichero();
                        break;
                    case 0:
                        System.out.println("Gracias por su tiempo!");
                        break;
                    default:
                        System.out.println("Opci�n no v�lida :|");
                        break;
                }
        } while (opc != 0);
    }
    
    public static void menu() {
        int mes = c1.get(Calendar.MONTH) + 1;
        System.out.println("MEN� CONSULTA DRA. PEPI");
        System.out.println("D�A ELEGIDO = " + c1.get(Calendar.DAY_OF_MONTH) + " del mes " + mes);
        System.out.println("");
        System.out.println("\t1.Elegir dia actual");
        System.out.println("\t2. Elegir otro dia");
        System.out.println("\t3. A�adir paciente");
        System.out.println("\t4. Listar pacientes dado un d�a (Ordena por A-Z)");
        System.out.println("\t5. Listar pacientes dado un d�a (Ordena por ID)");
        System.out.println("\t6. Buscar paciente por id en un d�a concreto");
        System.out.println("\t7. Borrar paciente por id en un d�a concreto");
        System.out.println("\t8. Borrar todos los pacientes de un d�a concreto");
        System.out.println("\t9. Guardar fichero con los pacientes");
        System.out.println("\t10. Leer el fichero guardado");
        System.out.println("\t0. Salir");
    }
    
    public static void subMenuListar() {
        System.out.println("�Qu� d�a quiere comprobar?");
        System.out.println("\t 1. Actual");
        System.out.println("\t 2. Otro");
        System.out.println("0. Volver atr�s");
        int opc = Utiles.leerEntero("Opcion");
        
        switch (opc) {
            case 1:
                tree.listarMapa();
                break;
            case 2:
                int d = Utiles.leerEntero("D�a a mirar");
                System.out.println("Work in progress...");
                break;
        }
    }
        public static void subMenuAZ() {
        System.out.println("�Qu� d�a quiere comprobar alfab�ticamente?");
        System.out.println("\t 1. Actual");
        System.out.println("\t 2. Otro");
            System.out.println("0. Volver atr�s");
        int opc = Utiles.leerEntero("Opcion");
        
        switch (opc) {
            case 1:
                tree.listarList();
                break;
            case 2:
                int d = Utiles.leerEntero("D�a a mirar");
                System.out.println("Work in progress...");
                break;
        }
    }
    
    
    
}
