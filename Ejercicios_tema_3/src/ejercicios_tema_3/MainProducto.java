/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios_tema_3;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import singleton.HibernateUtil;
import dam.*;
import org.hibernate.Transaction;

/**
 *
 * @author maxus
 */
public class MainProducto {
    
    public static void main(String[] args)
    {
        //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       //Insertar producto
       
       //Consulta HQL -> obtener la categoría 1 (Frutas)
       String consulta = "SELECT c FROM Categorias c WHERE c.id = 1";
       
       String nombreProducto = "Melocoton";
       
       float precio = 1.6F;
       
       Query query = session.createQuery(consulta);
       
       Categorias categoria = (Categorias) query.uniqueResult();
       
       //Comprobamos si la categoría existe:
       if(categoria != null)
       {
           //Creamos un objeto Productos para que persista y usamos el cosntructor de la clase
           Productos producto = new Productos(categoria, nombreProducto, precio);
           
           Transaction transaccion = session.beginTransaction();
           
           session.save(producto);
           
           transaccion.commit();
       }
       else
       {
           System.out.println("La categoría seleccionada no existe.");
       }
       
       session.close();
    }
    
}
