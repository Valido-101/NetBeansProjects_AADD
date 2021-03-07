/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;
import javax.xml.bind.PropertyException;

/**
 *
 * @author Francisco
 */
public class Producto implements Serializable {

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private PropertyChangeSupport propertySupport;

    private String descripcion;
    private int idProducto;
    private int stockActual;
    private int stockMinimo;
    private float pvp;

    public Producto() {
        propertySupport = new PropertyChangeSupport(this);

    }

    public Producto(String descripcion, int idProducto, int stockActual, int stockMinimo, float pvp) {

        propertySupport = new PropertyChangeSupport(this);
        this.descripcion = descripcion;
        this.idProducto = idProducto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.pvp = pvp;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int valorNuevo) {

        int valorAnterior = stockActual;
        stockActual = valorNuevo;
        if (stockActual < getStockMinimo()) {
            propertySupport.firePropertyChange("stockActual", valorAnterior,
                    stockActual);

        }

    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
