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
public class CuentaCorrientePersonal extends CuentaCorriente{
    
    private float comision;
    
    public CuentaCorrientePersonal(String dni, String nombre, String apellidos, float saldo, String iban, float comision)
    {
        this.setTitular(new Persona(dni, nombre, apellidos));
        this.setSaldo(saldo);
        this.setIban(iban);
        this.setEntidades(new String[100]);
        this.comision = comision;
    }
    
    @Override
    public String devolverInfoString()
    {        
        return super.devolverInfoString()+"Comisión: "+comision;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
}
