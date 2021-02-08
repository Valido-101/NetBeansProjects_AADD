/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clase_vero_2semanafebrero;

/**
 *
 * @author Usuario
 */
public class EjemploObjeto {
    
    //Atributos
    private int numero;
    private String cadena;
    
    
    //Métodos
    
    //Constructor vacío
    public EjemploObjeto()
    {
    
    }
    
    //Constructor parametrizado
    public EjemploObjeto(int numero, String cadena)
    {
        this.numero = numero;
        this.cadena = cadena;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
    
}
