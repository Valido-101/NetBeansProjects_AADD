/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tema_3;

import dam.Empleado;
import dam.Usuario;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import singleton.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class EliminarUsuario {
    public static void main(String[] args)
    {
        Scanner teclado=new Scanner(System.in);
        
        String nombre_usuario;
        
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        
        Session session = sessionfactory.openSession();
        
        System.out.println("Introduzca el usuario que desee eliminar:");
       nombre_usuario=teclado.nextLine();
       
       if(nombre_usuario=="")
       {
           
           System.out.println("No se ha insertado ningún usuario.");
      
       }
       else
       {
           Query q = session.createQuery("Select u from Usuario u where u.usuario=:nombre_usuario");
       
            q.setParameter("nombre_usuario",nombre_usuario);

            Usuario usuario = (Usuario)q.uniqueResult();

            if(usuario==null)
            {
                System.out.println("El usuario no existe.");
            }
            else
            {
                Empleado empleado = usuario.getEmpleado();

                Transaction transaccion = session.beginTransaction();

                session.delete(usuario);
                session.delete(empleado);

                transaccion.commit();
                
                System.out.println("Usuario y empleado borrados con éxito.");
            }
       }
       
       session.close();
       sessionfactory.close();
       teclado.close();
    }
}
