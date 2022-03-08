
package ejemploserializacion;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author miguelange
 */
public class ArchivoObjetos {
    private File fichero;
    
    public ArchivoObjetos(String fi) {
        fichero = new File(fi); //aqui le asignamos al File el String que da la direccion/nombre del archivo
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error de entrada/salida!");
                System.exit(2);
            } catch (Exception e) {
                System.out.println("ERROR GRAVE, no se ha podido leer el fichero");
                System.exit(1);
            }
        }
    }
    
    public void volcarAFichero(ArrayList <Clase1> a) {
        Clase1 aux;
        try {
            FileOutputStream fos = new FileOutputStream(this.fichero);
            ObjectOutputStream oos = new ObjectOutputStream(fos);        // si no nos hiciera falta asignar el FileOutput a una variable, pues lo podriamos crear nuevo en memoria con new, dentro del ObjectOutputStream(--aqui--)
            
            for (int i=0;i<a.size();i++) {
                aux = a.get(i);
                oos.writeObject(aux);
            }
            
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error. No se encuentra el archivo a escribir");
        } catch (IOException e) {
            System.out.println("Error de salida de datos!");
        }
    }
    
    public void leerDesdeFichero(ArrayListObjetos a) {
        a = new ArrayListObjetos();
        Clase1 aux;
        try {
        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);
        a.vaciar();
        while(ois.available() != 0) {
            aux = (Clase1) ois.readObject();    //lee el objeto del fichero
            a.add(aux); // añade al ArrayList
        }
        } catch (FileNotFoundException e) {
            System.out.println("Error. No se encuentra el fichero para leer");
        } catch (IOException e2) {
            System.out.println("Error de entrada!");
        } catch (Exception e3) {
            System.out.println("ERROR al castear el objeto para leerlo");
        }
    }
}
