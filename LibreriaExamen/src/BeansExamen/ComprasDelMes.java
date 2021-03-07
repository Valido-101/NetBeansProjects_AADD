/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeansExamen;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class ComprasDelMes implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    private String dni;
    private int unidades;
    
    public ComprasDelMes() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public ComprasDelMes(String dni, int unidades)
    {
        this.dni = dni;
        this.unidades = unidades;
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        if(unidades>10)
        {
            propertySupport.firePropertyChange("unidades", this.unidades, unidades);
        }
        
        this.unidades = unidades;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
