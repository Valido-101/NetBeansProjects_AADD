/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeansExamen;

import java.beans.*;
import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class ClienteDelMes implements PropertyChangeListener {
    
    String dni;
    GregorianCalendar fechaLogro;
    int unidadesTotales;
    boolean esClienteDelMes;
    
    public ClienteDelMes(String dni) {
        this.dni = dni;
        esClienteDelMes = false;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public GregorianCalendar getFechaLogro() {
        return fechaLogro;
    }

    public void setFechaLogro(GregorianCalendar fechaLogro) {
        this.fechaLogro = fechaLogro;
    }

    public int getUnidadesTotales() {
        return unidadesTotales;
    }

    public void setUnidadesTotales(int unidadesTotales) {
        this.unidadesTotales = unidadesTotales;
    }

    public boolean isEsClienteDelMes() {
        return esClienteDelMes;
    }

    public void setEsClienteDelMes(boolean esClienteDelMes) {
        this.esClienteDelMes = esClienteDelMes;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        unidadesTotales = (int)evt.getNewValue();
        fechaLogro = new GregorianCalendar();
        esClienteDelMes = true;
    }
  
}
