/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoempresaclases;

import java.util.Scanner;

/** @author Jose Lozano*/
public class Jefe extends Persona{
    double sueldo;

    public Jefe(double sueldo, Dni dni, String nombre, String apellidos, Fecha fecha) {
        super(dni, nombre, apellidos, fecha);
        this.sueldo = sueldo;
    }
    public static Jefe LeerJefe(){
        Jefe aux;
        Scanner entrada=new Scanner(System.in);
        Dni d;
        String nombre, apellidos;
        Fecha f;
        double sueldo;
        
        System.out.println("Nombre: ");
        nombre=entrada.nextLine();
        d=DNI.leerDNI();
        
        f=Fecha.leerFecha();
        System.out.println("Apellidos:");
        apellidos=entrada.nextLine();

        sueldo=entrada.nextDouble();
        
      aux=new Jefe(sueldo, d, nombre, apellidos, f);
      return aux;
    }

    @Override
    public String toString() {
        return "Nombre del jefe--> "+super.getNombre()+" con apellido--> "+super.getApellidos()+" dni--> "+super.getDni()+" y con fecha de nacimiento--> "+super.getFecha()+" sueldo--> "+sueldo;
    }
}

