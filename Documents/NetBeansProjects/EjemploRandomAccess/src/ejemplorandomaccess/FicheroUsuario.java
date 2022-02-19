
package ejemplorandomaccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author miguelange
 */
public class FicheroUsuario {
    String fichero;

    public FicheroUsuario(String fichero) { // MAXIMO DE BYTES: 133 bytes (suma de variables nombre, apellidos, codigo, salario y booleano de borrado)
        this.fichero = fichero;
    }                                                                                                                                                           
                                                                                                                                                                
    public void anadirUsuario () {                                                                                              //codigo -- 4 bytes
        Usuario usu = Usuario.leerUsuario();
        try {
            RandomAccessFile file = new RandomAccessFile(this.fichero, "rw");
            file.seek(file.length());           // nos posicionamos al FINAL del fichero (la longitud del fichero en bytes)
            //... y a partir de aqui, escribimos
            file.writeChars(rellena(usu.getNombre(), 20));
            file.writeChars(rellena(usu.getApellidos(), 40));
            file.writeInt(usu.getCodigo());
            file.writeDouble(usu.getSalario());
            file.writeBoolean(usu.isBorrado());
            
            file.close();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. No se puede crear el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR de entrada/salida");
        }
    }
    
    public void listarUsuarios() {
        
        try{
            RandomAccessFile file = new RandomAccessFile(this.fichero, "r");
            file.seek(0); //esto inicial el file en la posición que pongamos entre paréntesis [la posición debe ser un LONG]
            int contad = 0;
            
            while (file.length() > file.getFilePointer()) {         // mientras que LA POSICION ACTUAL sea menor que la LONGITUD del archivo (la longitud se mide en bytes)
                
                    System.out.println("Usuario nº" + (contad+1)  + " || " + "PUNTERO NUM " + file.getFilePointer());
                    System.out.println("________________________");
                    String nomb = leer(file, 20);
                    String ape = leer(file, 40);
                    int codig = file.readInt();
                    double salao = file.readDouble();
                    boolean borrao = file.readBoolean();
                    
                    if (!borrao) {          //solo se saca por pantalla los usuarios que no hayan sido eliminados
                        System.out.println("Nombre: " + quitarRelleno(nomb));
                        System.out.println("Apellidos: " + quitarRelleno(ape));
                        System.out.println("Código: " + codig);
                        System.out.println("Salario: " + salao);
                        contad++;           // cuando leemos el ultimo valor, ya se ha leido correctamente el usuario// sólo contamos los que no estan borrados
                    } 
                   
                    System.out.println("\t----------------");
            }
                file.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error de lectura o no existen usuarios!");
                    } catch (IOException e) {
                        System.out.println("ERROR de entrada/salida.");
                    } 
        
    }
    
    public boolean modificar() {
            RandomAccessFile file;
        Scanner entrada1 = new Scanner(System.in);
        double salao = 0;
        boolean hecho = false;
        int mod;
        
            listarUsuarios();   //listamos para ver los Punteros
            System.out.println("Fichero " + this.fichero);
            System.out.print("Introduce nº usuario a MODIFICAR: ");
            long num = entrada1.nextLong();
            
        try {
            file = new RandomAccessFile(this.fichero, "rw");
            file.seek((num-1)*133);
            
                do {
                subMenou();
                mod = entrada1.nextInt();
                file.seek((num-1)*133);
                    switch (mod) {
                        case 1:
                            entrada1.nextLine();    //vaciado de buffer
                            System.out.print("Nombre: ");
                            String nom = entrada1.nextLine();
                            file.writeChars(rellena(nom, 20));
                            
                            System.out.println("Puntero apellidos: " + file.getFilePointer());
                            break;
                        case 2:
                            file.seek(((num-1)*133) + 40);      // avanzamos 40 bytes por el nombre (20 caracteres son 40 bytes)
                            System.out.print("Apellido: " );
                            String ape = entrada1.nextLine();
                            file.writeChars(rellena(ape, 40));
                            
                            System.out.println("Puntero codigo: " + file.getFilePointer());
                            break;
                        case 3:
                            file.seek(((num-1)*133) + 120); // posición anterior +  80 bytes por apellido (40 caractere)
                            System.out.print("Codigo: " );
                            int codig = entrada1.nextInt();
                            file.writeInt(codig);
                            
                            System.out.println("Puntero salario: " + file.getFilePointer());
                            break;
                        case 4:
                            file.seek(((num-1)*133) + 124); // 4 bytes más por codigo
                            System.out.print("Salario = ");
                            salao = entrada1.nextDouble();
                            file.writeDouble(salao);        // salario son 8 bytes, pero no hace falta seguir avanzando al boolean
                            
                            System.out.println("Puntero boolean: " + file.getFilePointer());
                            break;
                        case 5:
                            file.seek((num-1)*133); 
                            System.out.print("Nombre: ");
                            String nomb = entrada1.nextLine();
                            file.writeChars(rellena(nomb, 20));
                            System.out.print("Apellido: " );
                            String apel = entrada1.nextLine();
                            file.writeChars(rellena(apel, 40));
                            System.out.print("Codigo: " );
                            int codigo = entrada1.nextInt();
                            file.writeInt(codigo);
                            System.out.print("Salario = ");
                            salao = entrada1.nextDouble();
                            file.writeDouble(salao);
                            hecho = true;
                            break;
                    }
                } while (mod != 0);

            file.close();
            hecho = true;
        } catch (InputMismatchException e) {
            System.out.println("ERROR de entrada. Introduce un numero por favor...");
            hecho = false;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. No se puede acceder al fichero.");
            hecho = false;
        } catch (IOException e) {
            System.out.println("ERROR. Error de entrada y salida al buscar por posición!");
            hecho = false;
        }
        return hecho;
    }

     public void subMenou() {
            System.out.println("");
            System.out.println("¿Qué quieres modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Codigo");
            System.out.println("4. Salario");
            System.out.println("5. Modificar todo");
            System.out.println("0. Salir de Modificar");
            System.out.print("\tOpción: ");
        }

        
    public void borrar() {
               RandomAccessFile file;
        Scanner entrada1 = new Scanner(System.in);
        
        boolean hecho = false;
        
        try {
            file = new RandomAccessFile(this.fichero, "rw");
        listarUsuarios();       //listamos para ver los Punteros
        System.out.print("Introduce nº usuario a BORRAR: ");
        long num = entrada1.nextLong();
        
        file.seek((num-1)*133);
        
            leer(file, 20);
            leer(file, 40);
            file.readInt();
            file.readDouble();
            file.readBoolean();
            
            file.seek(file.getFilePointer()-1);
            file.writeBoolean(true);
                    
            
        file.readLine();
        
                    /*  file.writeChars(nomb);
                      file.writeChars(ape);
                      file.writeInt(Integer.MAX_VALUE);
                      file.writeDouble(Double.MAX_VALUE);

                      file.writeBoolean(true);    // queda borrado tras la operación
                      */
          
          
            file.close();
            hecho = true;
        } catch (InputMismatchException e) {
            System.out.println("ERROR de entrada. Introduce un numero por favor...");
            hecho = false;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. No se puede acceder al fichero.");
            hecho = false;
        } catch (IOException e) {
            System.out.println("ERROR. Error de entrada y salida al buscar por posición!");
            hecho = false;
        }
        if(hecho) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("Lo sentimos, no se ha podido eliminar el usuario...");
        }
        }
    
    public boolean borrarApeNom() {
        RandomAccessFile file;
        Scanner entrada1 = new Scanner(System.in);
        boolean hecho = false;
        boolean borrao = false;
        
        System.out.print("Nombre: ");
        String nom = entrada1.nextLine();
        System.out.print("Apellido: ");
        String ape = entrada1.nextLine();
        
        nom = nom.toUpperCase();
        ape = ape.toUpperCase();
        
        try {
            file = new RandomAccessFile(this.fichero, "rw");
            file.seek(0);
            do {
                String nomb = leer(file, 20);
                    nomb = quitarRelleno(nomb);         //le quitamos los espacios al final, para comparar los datos en condiciones
                    String apel = leer(file, 40);
                    ape = quitarRelleno(apel);
                    
                    int codigo = file.readInt();
                    double salao = file.readDouble();
                    borrao = file.readBoolean();
                    
                    if (nom.compareToIgnoreCase(nomb) == 0 && apel.compareToIgnoreCase(ape) == 0) {
                        hecho = true;
                        System.out.println("");
                        System.out.println("Usuario encontrado!");
                        file.seek(file.getFilePointer() - 1);
                        file.writeBoolean(true);
                    }
            } while ((file.length() > file.getFilePointer()) && !hecho);
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error. No se puede abrir el fichero...");
        } catch (IOException e) {
            System.out.println("Error. Error de entrada/salida!");
        }
        
        return hecho;
    }
        
     public boolean buscarApenom() {
        RandomAccessFile file;
        Scanner entrada1 = new Scanner(System.in);
        boolean hecho = false;
        
        System.out.print("Nombre: ");
        String nom = entrada1.nextLine();
//     nom = nom.toUpperCase();        //DA IGUAL COMO SEA CON EL COMPARETOignoreCase // hay que pasarlo a mayúscula, para que coincida con lo que devuelve quitarRelleno después
        System.out.print("Apellidos: ");
        String ap = entrada1.nextLine();
//     ap = ap.toUpperCase();
        
        try{
            file = new RandomAccessFile(this.fichero, "rw");
            file.seek(0); //inicializamos para que busque desde la primera posición cada vez que listamos
            
            while (file.length() > file.getFilePointer() && !hecho) {
                    String nomb = leer(file, 20);
                    nomb = quitarRelleno(nomb);         //le quitamos los espacios al final, para comparar los datos en condiciones
                    String ape = leer(file, 40);
                    ape = quitarRelleno(ape);
                    
                    int codigo = file.readInt();
                    double salao = file.readDouble();
                    boolean borrao = file.readBoolean();
                    
                    if (nom.compareToIgnoreCase(nomb) == 0 && ap.compareToIgnoreCase(ape) == 0 && !borrao) {
                        System.out.println("Nombre: " + nomb);
                        System.out.println("Apellidos: " + ape);
                        System.out.println("Codigo: " + codigo);
                        System.out.println("Salario = " + salao);
                        hecho = true;
                    }
            }
                file.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error de lectura!");
                        hecho = false;
                    } catch (IOException e) {
                        System.out.println("ERROR de entrada/salida.");
                        hecho = false;
                    }
        
        if (!hecho) {
            System.out.println("El usuario no existe...");
        }
        
        return hecho;
    }
     
     public long buscarPos(long pos) {
         RandomAccessFile file;
         
         pos = pos-1;
         
         try {
             file = new RandomAccessFile(this.fichero, "rw");
             file.seek(pos);
             String nomb = leer(file, 20);
             String ape = leer(file, 40);
             int codig = file.readInt();
             double salario = file.readDouble();
             
             file.close();
         } catch (FileNotFoundException e) {
             System.out.println("ERROR. No se puede acceder al fichero");
         } catch (IOException e) {
             System.out.println("ERROR. Error de entrada/salida.");
         }
         return pos;
     }
     
     public void buscarPos() {
         RandomAccessFile file;
         
         listarUsuarios();      //listamos para ver los Punteros
        Scanner entrada1 = new Scanner(System.in);
        try {
        file = new RandomAccessFile(this.fichero, "rw");
        boolean encontrado = false;
        System.out.print("Nº del usuario a partir del que MOSTRAR: ");
        long num = entrada1.nextLong();
        
        
        file.seek((num-1)*133);
        do {
            String nom = leer(file, 20);
            String ap = leer(file,40);
            int codigo = file.readInt();
            double salao = file.readDouble();
            boolean borrao = file.readBoolean();
        
        
                if (!borrao) {
                    encontrado = true;
                    System.out.println("");
                    System.out.println("Nombre: " + nom);
                    System.out.println("Apellido: " + ap);
                    System.out.println("Codigo: " + codigo);
                    System.out.println("Salario = " + salao);
                }
        } while (!encontrado && file.getFilePointer() < file.length()); //mientras no se haya encontrado y no se haya llegado al final.
            file.close();
//            else {
//                System.out.println("Usuario eliminado...");
//            }

        } catch (InputMismatchException e) {
            System.out.println("ERROR de entrada. Introduce un numero por favor...");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. No se puede acceder al fichero.");
        } catch (IOException e) {
            System.out.println("ERROR. Error de entrada y salida al buscar por posición!");
        }
        
     }
     
    
    //metodos útiles
    public static String leer(RandomAccessFile dis, int cantidad) { // lee x caracteres del RandomAccessFile (hasta el numero de caracteres/bytes que le digamos)
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
