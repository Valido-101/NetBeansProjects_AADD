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
public class GestorObjetos {
    
    //Atributos
    private EjemploObjeto[] arrayObjetos;
    
    //Métodos
    public GestorObjetos(EjemploObjeto[] arrayObjetos)
    {
        this.arrayObjetos = arrayObjetos;
    }

    public EjemploObjeto[] getArrayObjetos() {
        return arrayObjetos;
    }

    public void setArrayObjetos(EjemploObjeto[] arrayObjetos) {
        this.arrayObjetos = arrayObjetos;
    }
    
}
