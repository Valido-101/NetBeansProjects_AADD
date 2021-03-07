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
public class CuentaAhorro extends CuentaBancaria{
    
    private String tipoInteres;
    
    public CuentaAhorro(String dni, String nombre, String apellidos, float saldo, String iban, String tipoInteres)
    {
        this.setTitular(new Persona(dni, nombre, apellidos));
        this.setSaldo(saldo);
        this.setIban(iban);
        this.tipoInteres = tipoInteres;
    }
    
    @Override
    public String devolverInfoString()
    {
        return super.devolverInfoString()+" Tipo de interés: "+tipoInteres;
    }

    public String getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(String tipoInteres) {
        this.tipoInteres = tipoInteres;
    }
}
