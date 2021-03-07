
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MisBeans;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Ventas implements Serializable{
    
    private int numeroventa;
    private int idproducto;
    private Date fechaventa;
    private int cantidad;

    public Ventas(){}
    
    public Ventas(int numeroventa, int idproducto, Date fechaventa, int cantidad)
    {
        this.cantidad=cantidad;
        this.fechaventa=fechaventa;
        this.idproducto=idproducto;
        this.numeroventa=numeroventa;
    }
    
    public int getNumeroventa() {
        return numeroventa;
    }

    public void setNumeroventa(int numeroventa) {
        this.numeroventa = numeroventa;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}