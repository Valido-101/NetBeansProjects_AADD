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
        //Creamos el teclado 
       Scanner teclado = new Scanner(System.in);
       
       //Nombres de los jugadores
       String jugador1, jugador2;
       
       //Objetos de la clase jugador
       Jugadores j1,j2;
       
       //Resultado de comprobar si los nombres se han introducido correctamente
       boolean j1_correcto, j2_correcto;
       
       //Se introducen por teclado los nombres de ambos jugadores
       
       System.out.println("Introduzca el nombre del primer jugador:");
       jugador1=teclado.nextLine();
        
       System.out.println("Introduzca el nombre del segundo jugador:");
       jugador2=teclado.nextLine();
       
       //Obtenemos la posición del espacio dentro del string introducido por teclado
       
       int espacio_j1 = jugador1.indexOf((char)32)+1;
       int espacio_j2 = jugador2.indexOf((char)32)+1;
       
       //Comprobamos si la primera letra del nombre del jugador 1 es mayúscula y si contiene un espacio
       if(jugador1.substring(0, 1).equals(jugador1.substring(0,1).toUpperCase())&&jugador1.contains(" "))
       {
           //Separamos la cadena y nos quedamos con lo que queda después del espacio
           String s1 = jugador1.substring(espacio_j1);
           
           //Si lo que queda después del espacio empieza con mayúscula, la comprobación es correcta
           if(s1.substring(0,1).equals(s1.substring(0,1).toUpperCase()))
           {
               j1_correcto=true;
           }
           //De lo contrario no lo es
           else
           {
               j1_correcto=false;
           }
       }
       else
       {
           j1_correcto=false;
       }
       
       //Comprobamos si la primera letra del nombre del jugador 2 es mayúscula y si contiene un espacio
       if(jugador2.substring(0, 1).equals(jugador2.substring(0,1).toUpperCase())&&jugador2.contains(" "))
       {
           //Separamos la cadena y nos quedamos con lo que queda después del espacio
           String s2 = jugador2.substring(espacio_j2);
           
           //Si lo que queda después del espacio empieza con mayúscula, la comprobación es correcta
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
       
       //Si ambas comprobaciones son correctas procedemos a intercambiar los equipos
       if(j1_correcto==true&&j2_correcto==true)
       {           
            //Obtenemos SessionFactory
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

            //Obtenemos la Session
            Session session = sessionfactory.openSession();
            
            //Creamos la transacción
            Transaction transaccion = session.beginTransaction();

            //Obtenemos los objetos que representan al jugador 1 y al jugador 2            
            Query consulta_j1 = session.createQuery("SELECT j FROM Jugadores j WHERE j.nombre=:jugador1");
            consulta_j1.setParameter("jugador1",jugador1);
            Jugadores jugador_1 = (Jugadores)consulta_j1.uniqueResult();
            
            Query consulta_j2 = session.createQuery("SELECT j FROM Jugadores j WHERE j.nombre=:jugador2");
            consulta_j2.setParameter("jugador2",jugador2);
            Jugadores jugador_2 = (Jugadores)consulta_j2.uniqueResult();
            
            //Obtenemos los equipos de ambos jugadores
            Equipos equipo_j1 = jugador_1.getEquipos();
            Equipos equipo_j2 = jugador_2.getEquipos();         
            
            //Seteamos los equipos de cada jugador con el equipo del otro
            jugador_1.setEquipos(equipo_j2);
            jugador_2.setEquipos(equipo_j1);
            
            //Actualizamos la base de datos con los jugadores y sus nuevos equipos
            session.update(jugador_1);
            session.update(jugador_2);
            
            //Hacemos un commit para que se guarden los datos
            transaccion.commit();
            
            //Informamos al usuario de lo que ha ocurrido
            System.out.println("El equipo del jugador "+jugador_1.getNombre()+" era "+equipo_j1.getNombre()+". Ahora es el equipo "+jugador_1.getEquipos().getNombre()+".");
            System.out.println("El equipo del jugador "+jugador_2.getNombre()+" era "+equipo_j2.getNombre()+". Ahora es el equipo "+jugador_2.getEquipos().getNombre()+".");
            
            //Cerramos los recursos
            session.close();
            sessionfactory.close();
        }
        else
        {
            //Si las comprobaciones no han sido correctas se informa al usuario
            System.out.println("Error al escribir el nombre de los jugadores.");
        }
    }
}
