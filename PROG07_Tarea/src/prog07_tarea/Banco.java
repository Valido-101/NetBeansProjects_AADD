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
public class Banco {
    
    private CuentaBancaria[] cuentas;
    
    public Banco()
    {
        cuentas = new CuentaBancaria[100];
    }
    
    public boolean abrirCuenta(CuentaBancaria nuevaCuenta)
    {
        int indice = 0;
        
        for(CuentaBancaria cuenta: cuentas)
        {
            if(cuenta!=null)
            {
                indice++;
            }
        }
        
        if(indice<cuentas.length)
        {
            cuentas[indice] = nuevaCuenta;
            
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String[] listadoCuentas()
    {
        String[] listadoCuentas;
        int tamannio = 0;
        
        for(CuentaBancaria cuenta: cuentas)
        {
            if(cuenta!=null)
            {
                tamannio++;
            }
        }
        
        listadoCuentas = new String[tamannio];
        
        for(int x=0;x<listadoCuentas.length;x++)
        {
            listadoCuentas[x] = cuentas[x].devolverInfoString();
        }
        
        return listadoCuentas;
    }
    
    public String informacionCuenta(String iban)
    {
        for(CuentaBancaria cuenta: cuentas)
        {
           if(cuenta.getIban().equals(iban))
           {
               return cuenta.devolverInfoString();
           } 
        }
        
        return null;
    }
    
    public boolean ingresoCuenta(String iban, float cantidad)
    {
        for(CuentaBancaria cuenta: cuentas)
        {
           if(cuenta.getIban().equals(iban))
           {
               cuenta.setSaldo(cuenta.getSaldo() + cantidad);
               
               return true;
           } 
        }
        
        return false;
    }
    
    public boolean retiradaCuenta(String iban, float cantidad)
    {
        for(CuentaBancaria cuenta: cuentas)
        {
            if(cuenta.getIban().equals(iban))
            {
                if (cuenta.getSaldo() > cantidad) 
                {
                    cuenta.setSaldo(cuenta.getSaldo() - cantidad);
                    
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        
        return false;
    }
    
    public float obtenerSaldo(String iban)
    {
        for(CuentaBancaria cuenta: cuentas)
        {
            if(cuenta.getIban().equals(iban))
            {
                return cuenta.getSaldo();
            }
        }
        
        return -1;
    }
}
