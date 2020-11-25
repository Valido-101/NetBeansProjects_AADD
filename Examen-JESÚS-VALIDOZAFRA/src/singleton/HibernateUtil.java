/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package singleton;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David
 */
public class HibernateUtil {
    
    // Hay una única sessionFactory para toda la aplicación
    // Recoge la sesión actual
    
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        
        try{
            // Crear la SessionFactory de hibernate.org.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            return configuration.buildSessionFactory();
      
         }
             
         catch(HibernateException ex){
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
            }   
            
    }            
    
    // Devuelve el valor de la variable estática: SessionFactory
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    
    }
    
}