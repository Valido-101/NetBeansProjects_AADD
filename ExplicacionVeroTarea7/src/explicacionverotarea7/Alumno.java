/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package explicacionverotarea7;

/**
 *
 * @author Usuario
 */
public class Alumno extends Persona implements Interfaz{

    private String codAlumno;
    
    public Alumno(String nombre, int edad, String dni, String codAlumno)
    {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.codAlumno = codAlumno;
    }
    
    @Override
    public String toString() {
        return nombre+", "+edad+", "+dni+", "+codAlumno;
    }
    
    @Override
    public void saludar()
    {
        super.saludar();
        
        System.out.println(" y soy un alumno.");
    }

    @Override
    public void estudiar() {
        System.out.println("El alumno está estudiando");
    }

    @Override
    public void jugarVideojuegos() {
        System.out.println("El alumno está jugando a Videojuegos");
    }
}
