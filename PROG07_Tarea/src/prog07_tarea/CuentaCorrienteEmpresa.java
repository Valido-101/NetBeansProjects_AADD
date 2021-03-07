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
public class CuentaCorrienteEmpresa extends CuentaCorriente{
    
    private String tipoInteres;
    private float maximoDescubierto;
    
    public CuentaCorrienteEmpresa(String dni, String nombre, String apellidos, float saldo, String iban, String tipoInteres, float maximoDescubierto)
    {
        this.setTitular(new Persona(dni, nombre, apellidos));
        this.setSaldo(saldo);
        this.setIban(iban);
        this.setEntidades(new String[100]);
        this.tipoInteres = tipoInteres;
        this.maximoDescubierto = maximoDescubierto;
    }
    
    @Override
    public String devolverInfoString()
    {     
        return super.devolverInfoString()+"Tipo de interés: "+tipoInteres+", Máximo descubierto: "+maximoDescubierto;
    }
    
}
