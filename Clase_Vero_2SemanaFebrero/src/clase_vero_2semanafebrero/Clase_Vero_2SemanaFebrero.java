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
public class Clase_Vero_2SemanaFebrero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EjemploObjeto objeto = new EjemploObjeto(1,"Hola");
        
        //Crear un array de objetos EjemploObjeto que va a tener una sola posición
        EjemploObjeto[] arrayObjetos = new EjemploObjeto[2];
        
        //Añadimos un objeto previamente creado al array
        arrayObjetos[0] = objeto;
        
        //Añadir objeto sin crearlo previamente
        arrayObjetos[1] = new EjemploObjeto(2,"Adiós");
        
        //Mostramos los datos por pantalla
        //System.out.println(arrayObjetos[0].getCadena());
        
        //System.out.println(objeto.getCadena());
        
        //System.out.println(arrayObjetos.length);
        
        //System.out.println(arrayObjetos[1].getCadena());
        
        //¿Cómo recorremos los arrays?
        
        for(int x=0;x<arrayObjetos.length;x++)
        {
            System.out.println("Numero del objeto: "+arrayObjetos[x].getNumero()+", Cadena del objeto: "+arrayObjetos[x].getCadena());
        }
        
    }
    
}
