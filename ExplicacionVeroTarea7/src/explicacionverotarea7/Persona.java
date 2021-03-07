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
public abstract class Persona {
    
    //No se puede acceder a estos atributos desde otro paquete, pero sí desde otras clases y subclases del mismo paquete
    protected String nombre;
    protected int edad;
    protected String dni;
    
    @Override
    public abstract String toString();
    
    public void saludar()
    {
        System.out.print("Hola, me llamo "+nombre);
    }
    
}
