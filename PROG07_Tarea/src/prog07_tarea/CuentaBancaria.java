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
public abstract class CuentaBancaria implements Imprimible{
    
    //Atributos que solo pueden ser accedidos por clases del mismo paquete
    private Persona titular;
    private float saldo;
    private String iban;

    @Override
    public String devolverInfoString() {
        return "Titular -> Nombre: "+titular.getNombre()+", Apellidos: "+titular.getApellidos()+", DNI: "+titular.getDni()+" | Datos Cuenta -> Saldo: "+saldo+", IBAN: "+iban;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
