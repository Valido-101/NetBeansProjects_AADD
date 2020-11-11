/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios_tema_3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import singleton.HibernateUtil;
import dam.*;
import org.hibernate.Transaction;

/**
 *
 * @author maxus
 */
public class Ejercicio_2_y_3 
{
    public static void main(String[] args)
    {
       //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       //Obtenemos el objeto de tipo Productos
       Productos producto = (Productos)session.get(Productos.class, 1);
       
       System.out.println("Producto seleccionado: "+producto.getNombreProducto());
       
       System.out.println("El precio anterior era "+producto.getPrecio());
       
       //Si existe lo tratamos
       if(producto!=null)
       {
           producto.setPrecio(3.5F);
           
           Transaction transaccion = session.beginTransaction();
           
           session.update(producto);
           
           transaccion.commit();
           
           System.out.println("El nuevo precio es "+producto.getPrecio());
           
           System.out.println("Actualización realizada");
       }
       else
       {
           System.out.println("Producto no encontrado.");
       }
    }
}
