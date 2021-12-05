/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoempresaclases;

/**
 * @author usuario
 */
public class Persona {
    private String nombre, apellidos;
   DNI dni;
   Fecha fecha;

    public Persona(Dni dni, String nombre, String apellidos, Fecha fecha) {
        this.dni = dni.clone();//new Dni(dni+"");
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha.clone();//new Fecha(fecha.getdia(),...);
    }

    public static Persona LeerPersona(){
        Persona aux;
        Scanner entrada=new Scanner(System.in);
        Dni d;
        String nombre, apellidos;
        Fecha f;
        
        System.out.println("Nombre: ");
        nombre=entrada.nextLine();
        d=Dni.leerDni();
        
        f=Fecha.LeerF();
        System.out.println("Apellidos:");
        apellidos=entrada.nextLine();
      
      aux=new Persona(d, nombre, apellidos, f);
      return aux;
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

    public String getDni() {
        return dni+"";//lo convertimos en String
    }

    public String getFecha() {
        return fecha+"";
    }
    public void setFecha(Fecha fecha){
        this.fecha=fecha.clone();
    }
    
    public Persona clone(){
        Persona aux=new Persona(dni, nombre, apellidos, fecha);
        return aux;
    }
   
}
