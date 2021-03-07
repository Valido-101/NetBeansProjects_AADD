/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MisBeans;

import java.beans.*;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Francisco
 */
public class Pedido implements Serializable, PropertyChangeListener {
    
    private int numeroPedido;
    private int idProducto;
    private Date fecha;
    private int cantidad;
    private boolean pedir;
    
    
   
    
    public Pedido() {
        
    }
     public Pedido(int numeroPedido,int idProducto,Date fecha,int cantidad) {
         
        this.numeroPedido=numeroPedido;
        this.idProducto=idProducto;
        this.fecha=fecha;
        this.cantidad=cantidad;
        pedir= false;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isPedir() {
        return pedir;
    }

    public void setPedir(boolean pedir) {
        this.pedir = pedir;
    }
    
    
    
    

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Stock anterior "+ evt.getOldValue());
        System.out.println("Stock actual "+ evt.getNewValue());
        setPedir(true);
    }
    
}
