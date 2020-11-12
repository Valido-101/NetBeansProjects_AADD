/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios_tema_3;

import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import singleton.HibernateUtil;
import dam.Productos;

/**
 *
 * @author maxus
 */
public class Ejercicio_5 {
    
    public static void main(String[] args)
    {
      
       Scanner teclado = new Scanner(System.in);
       
       String nombreProducto;
       
       System.out.println("Introduzca el nombre de un producto:");
       
       nombreProducto = teclado.nextLine();
        
       //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       String consulta = "SELECT p.precio FROM Productos p WHERE p.nombreProducto=:nombreProducto";
       
       //Obtenemos la lista de tipo Productos
       Query q = session.createQuery(consulta);
       q.setParameter("nombreProducto",nombreProducto);
       
       float precio = (float)q.uniqueResult();
       
       //Mostramos por pantalla los resultados
       
       System.out.println("Precio del producto: "+precio);
       
       session.close();
       teclado.close();
    }
    
}
