/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplorandomaccess;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Miguelañez-PC
 */
public class Usuario {
    private String nombre;
    private String apellidos;
    private int codigo;
    private double salario;
    private boolean borrado;
    
// PUNTEROS y DESDE CUALES SE PUEDEN OBTENER LOS ATRIBUTOS
    public final static long TAMANOPUNT = 133;
    
    public final static long PUNTNOMBRE = 0;
    public final static long PUNTAPELLIDO = 40;
    public final static long PUNTCODIGO = 120;
    public final static long PUNTSALARIO = 124;
    public final static long PUNTBOOLEAN = 132;
    
    
    public Usuario(String nombre, String apellidos, int codigo, double salario, boolean borrado) {      // MAXIMO DE BYTES: 133 bytes (suma de variables nombre, apellidos, codigo, salario y booleano de borrado
        this.nombre = nombre;       //rellenar 40-apellidos, 20- nombre
        this.apellidos = apellidos;     
        this.codigo = codigo;
        this.salario = salario;
        this.borrado = borrado;
    }


    public static Usuario leerUsuario() {
        Scanner entrada1 = new Scanner(System.in);
        boolean valid = false;
        String nombre = "", apellidos = "";
        int codig = 0;
        double salao = 0;
        
        do {
            try {
        System.out.print("Nombre: ");
        nombre = entrada1.nextLine();
        System.out.print("Apellidos: ");
        apellidos = entrada1.nextLine();
        
        System.out.print("Codigo: ");
        codig = entrada1.nextInt();
        System.out.print("Salario: ");
        salao = entrada1.nextDouble();
        entrada1.nextLine();    //vaciado de buffer
        valid = true;
            } catch (InputMismatchException im) {
                System.out.println("ERROR. Solo números en el campo numérico...");
            }
        } while(!valid);
        
        Usuario aux = new Usuario(nombre, apellidos, codig, salao, false);          //lo creamos sin estar b0rrado por defecto
        return aux;
        
    }
    
    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public long getLongitud () {
        return TAMANOPUNT;
    }
    
    

    
    
    
    @Override
    public String toString() {
            return "Usuario{" + "nombre=" + nombre + ", apellidos=" + apellidos + "\ncodigo=" + codigo + ", salario=" + salario + '}';
    }
    
    
}
