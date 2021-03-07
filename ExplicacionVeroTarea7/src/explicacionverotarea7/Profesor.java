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
public class Profesor extends Persona{

    private String codProfesor;
    
    public Profesor(String nombre, int edad, String dni, String codProfesor)
    {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.codProfesor = codProfesor;
    }
    
    @Override
    public String toString() {
        return nombre+", "+edad+", "+dni+", "+codProfesor;
    }
    
    @Override
    public void saludar()
    {
        super.saludar();
        
        System.out.println(" y soy un profesor.");
    }
}
