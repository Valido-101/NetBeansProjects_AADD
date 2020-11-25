/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tema_3;

import dam.Usuario;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import singleton.HibernateUtil;

/**
 *
 * @author Jesús
 */
public class ModificarContrasena {
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        
        String usuario;
        String pass_antigua;
        String pass_nueva="";
        Usuario usuario_bbdd;
        
       //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       System.out.println("Introduzca el usuario que desee cambiar:");
       usuario=teclado.nextLine();
       
        System.out.println("Introduzca la contraseña de este usuario:");
        pass_antigua=teclado.nextLine();  
        
        if(usuario=="")
        {
            System.out.println("No se ha introducido ningún usuario.");
        }
        else
        {
            if(pass_antigua=="")
            {
                System.out.println("No se ha introducido ninguna contraseña.");
            }
            else
            {
                System.out.println("Introduzca la nueva contraseña:");
                pass_nueva=teclado.nextLine();
                
                Query contrasennia = session.createQuery("SELECT u from Usuario u where u.usuario=:usuario");
        
                contrasennia.setParameter("usuario",usuario);

                usuario_bbdd = (Usuario)(contrasennia.uniqueResult());
                
                if(usuario_bbdd==null)
                {
                    System.out.println("El usuario no existe.");
                }
                else
                {
                    if(pass_antigua.equals(usuario_bbdd.getClave()))
                    {
                        Transaction transaccion = session.beginTransaction();

                        usuario_bbdd.setClave(pass_nueva);

                        session.update(usuario_bbdd);

                        transaccion.commit();
                        
                        System.out.println("Usuario actualizado con éxito");
                    }
                    else
                    {
                        System.out.println("La contraseña introducida no coincide con la del usuario.");
                    }
                }
                
            }
        }
        
        session.close();
        sessionfactory.close();
        teclado.close();
    }
}
