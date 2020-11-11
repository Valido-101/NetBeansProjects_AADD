/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios_tema_3;

import dam.Productos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import singleton.HibernateUtil;

/**
 *
 * @author maxus
 */
public class Ejercicio_4 {
    
    public static void main(String[] args)
    {
       //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       String consulta = "SELECT p FROM Productos p";
       
       //Obtenemos la lista de tipo Productos
       Query q = session.createQuery(consulta);
       
       List<Productos> productos = q.list();
       
       //Mostramos por pantalla los resultados
       for(int x=0 ; x<productos.size() ; x++)
       {
           System.out.println("Id: "+productos.get(x).getId()+", Nombre: "+productos.get(x).getNombreProducto()+", Precio: "+productos.get(x).getPrecio()+", Categoría: "+productos.get(x).getCategorias().getNombreCategoria());
       }
       
       session.close();
    }
}
