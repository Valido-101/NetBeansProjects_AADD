/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prog07_tarea;

/**
 *
 * @author Usuario
 */
public abstract class CuentaCorriente extends CuentaBancaria{
    
    private String[] entidades;
    
    @Override
    public String devolverInfoString()
    {
        String listaEntidades = "";
        
        for(String entidad: getEntidades())
        {
            listaEntidades += entidad+", ";
        }
        
        return super.devolverInfoString()+" Entidades: "+listaEntidades+", ";
    }

    public String[] getEntidades() {
        return entidades;
    }

    public void setEntidades(String[] entidades) {
        this.entidades = entidades;
    }    
}
