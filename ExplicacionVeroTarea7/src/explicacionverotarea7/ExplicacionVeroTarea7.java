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
public class ExplicacionVeroTarea7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Objetos necesarios
        Persona persona;
        Profesor profesor = new Profesor("José", 34, "123456789X", "codigoprof");
        Alumno alumno = new Alumno("Bernardo", 19, "123456789Z", "codigoalumn");
        
        boolean esProfesor = false;
        
        //-------------------------------------------------------------------------
        
        System.out.println(profesor.toString());
        
        System.out.println(alumno.toString());
        
        profesor.saludar();
        
        alumno.saludar();
        
        
        alumno.jugarVideojuegos();
        
        alumno.estudiar();
        
        //Polimorfismo: Ligadura dinámica
        //Las clases pueden ser polimórficas, es decir, se puede usar una clase como si fuese otra distinta (siempre que haya una relación de herencia entre ellas)
        //Esto permite que, en este caso, por ejemplo, persona pueda usar el método saludar y dependiendo de su instancia sea 
        //el método de profesor o alumno.
        if (esProfesor) 
        {
            persona = new Profesor("Pedro", 34, "234567891A", "codigoprof");
        }
        else
        {
            persona = new Alumno("Benito", 19, "234567891A", "codigoalumn");
        }
        
        persona.saludar();
        
        ((Alumno)persona).estudiar();
    }
    
}
