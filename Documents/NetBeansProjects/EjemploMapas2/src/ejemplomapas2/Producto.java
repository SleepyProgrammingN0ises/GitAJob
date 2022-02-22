
package ejemplomapas2;

import java.util.Scanner;

/**
 * @author miguelange
 */
public class Producto implements Comparable {
    private String nombre;
    private String marca;
    private int codigo;
    
    private String tipo;
    private double precio;
    
    public Producto(String nom, String marc, String tipo, double presio, int cod) {
        this.nombre = nom;
        this.marca = marc;
        this.tipo = tipo;
        this.precio = presio;
        this.codigo = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCodigo(){
        return codigo;
    }
    
    public void setCodigo(int cod) {
        this.codigo=cod;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
    
    public static Producto leerProd() {
        Scanner entrada1 = new Scanner(System.in);
        
        System.out.print("Codigo: ");
        int cod = entrada1.nextInt();
        entrada1.nextLine();    //vaciado de buffer
        System.out.print("Modelo producto: ");
        String nom = entrada1.nextLine();
        System.out.print("Marca:");
        String marc = entrada1.nextLine();
        System.out.print("Tipo de producto: ");
        String tipo = entrada1.nextLine();
        System.out.print("Precio: ");
        double presio = entrada1.nextDouble();
        
        Producto aux = new Producto(nom, marc, tipo, presio, cod);
        return aux;
    }
    
    public static Integer leerCod() {
        Scanner entrada1 = new Scanner(System.in);
        Integer codaux = 9;
        boolean valido = false;
        
        do {
            try {
                  valido = true;
                  System.out.println("Introduce el código a buscar: ");
                  codaux = entrada1.nextInt();
            } catch (Exception e) {
                valido = false;
                System.err.println("Error de entrada del código");
            }
        }  while (!valido);
        return codaux;
        
    }
    
    @Override
    public int compareTo(Object obj) {
        Producto aux =(Producto) obj;
        if (this.getCodigo()==aux.getCodigo())
            return 0;                                                               //devuelve 0 si coinciden sólo los códigos de cada objeto Producto
        else
            return aux.toString().compareTo(obj.toString());    //devolverá otro número (menor a 0 si es menor lexicográficamente; mayor si es mayor...)
    }
    
    @Override
    public boolean equals(Object obj) {
        Producto aux = (Producto) obj;
        String cad1 = this.codigo + this.marca + this.tipo;     // int cod1 = this.codigo;
        String cad2 = aux.codigo + aux.marca + aux.tipo;      // int cod2 = aux.codigo;
        
        if (cad1.compareTo(cad2) == 0) {                                          // if (cod1 == cod2) {
            return true;                                                                                //  return true; 
        } else {                                                                                                // } else {
            return false;                                                                                        // return false
        }                                                                                                             // }
    }
    
    @Override
    public int hashCode() {
      return codigo;            // para que sea igual que en el equals, usamos el código para recoger un 'hashCode'
    }
    
    @Override
    public String toString() {
        return "Producto " + this.codigo + "= " + "\nModelo: " + this.nombre + ";\tMarca: " + this.marca + "\n Tipo: " + this.tipo + ";\tPrecio: " + this.precio; 
    }
    
}

