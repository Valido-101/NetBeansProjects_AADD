/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import singleton.HibernateUtil;
import dam.*;
import java.util.Scanner;
import org.hibernate.Query;

/**
 *
 * @author Jesús
 */
public class Ejercicio8 {
    public static void main(String[] args)
    {
       Scanner teclado = new Scanner(System.in);
       
       String jugador1, jugador2;
       
       Jugadores j1,j2;
       
       boolean j1_correcto, j2_correcto;
       
       System.out.println("Introduzca el nombre del primer jugador:");
       jugador1=teclado.nextLine();
        
       System.out.println("Introduzca el nombre del segundo jugador:");
       jugador2=teclado.nextLine();
       
       int espacio_j1 = jugador1.indexOf((char)32)+1;
       int espacio_j2 = jugador2.indexOf((char)32)+1;
       
       if(jugador1.substring(0, 1).equals(jugador1.substring(0,1).toUpperCase())&&jugador1.contains(" "))
       {
           String s1 = jugador1.substring(espacio_j1);
           
           String s2 = s1.substring(0,1);
           
           if(s1.substring(0,1).equals(s1.substring(0,1).toUpperCase()))
           {
               j1_correcto=true;
           }
           else
           {
               j1_correcto=false;
           }
       }
       else
       {
           j1_correcto=false;
       }
       
       if(jugador2.substring(0, 1).equals(jugador2.substring(0,1).toUpperCase())&&jugador2.contains(" "))
       {
           String s2 = jugador2.substring(espacio_j2);
           
           if(s2.substring(0, 1).equals(s2.substring(0,1).toUpperCase()))
           {
               j2_correcto=true;
           }
           else
           {
               j2_correcto=false;
           }
       }
       else
       {
           j2_correcto=false;
       }
       
       if(j1_correcto==true&&j2_correcto==true)
       {           
            //Obtenemos SessionFactory
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

            //Obtenemos la Session
            Session session = sessionfactory.openSession();
            
            Transaction transaccion = session.beginTransaction();

            //Obtenemos el objeto de tipo Productos            
            Query consulta_j1 = session.createQuery("SELECT j FROM Jugadores j WHERE j.nombre=:jugador1");
            consulta_j1.setParameter("jugador1",jugador1);
            Jugadores jugador_1 = (Jugadores)consulta_j1.uniqueResult();
            Query consulta_j2 = session.createQuery("SELECT j FROM Jugadores j WHERE j.nombre=:jugador2");
            consulta_j2.setParameter("jugador2",jugador2);
            Jugadores jugador_2 = (Jugadores)consulta_j2.uniqueResult();
            
            Equipos equipo_j1 = jugador_1.getEquipos();
            Equipos equipo_j2 = jugador_2.getEquipos();         
            
            jugador_1.setEquipos(equipo_j2);
            jugador_2.setEquipos(equipo_j1);
            
            session.update(jugador_1);
            session.update(jugador_2);
            
            transaccion.commit();
            
            System.out.println("El equipo del jugador "+jugador_1.getNombre()+" era "+equipo_j1.getNombre()+". Ahora es el equipo "+jugador_1.getEquipos().getNombre()+".");
            System.out.println("El equipo del jugador "+jugador_2.getNombre()+" era "+equipo_j2.getNombre()+". Ahora es el equipo "+jugador_2.getEquipos().getNombre()+".");
            
            session.close();
            sessionfactory.close();
        }
        else
        {
            System.out.println("Error al escribir el nombre de los jugadores.");
        }
    }
}
