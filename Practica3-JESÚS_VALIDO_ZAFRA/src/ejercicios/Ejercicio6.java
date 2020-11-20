/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios;

/**
 *
 * @author Jesús
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import singleton.HibernateUtil;
import java.util.Scanner;
import dam.*;
import java.util.List;
import org.hibernate.Query;

public class Ejercicio6 {
    public static void main(String[] args)
    {
        //Creamos un teclado nuevo
        Scanner teclado=new Scanner(System.in);
        //Variable en la que almacenaremos el nombre del equipo que queramos
        String nombre_equipo;
        
        System.out.println("Introduzca un nombre de equipo:");
        nombre_equipo=teclado.nextLine();
        
       //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       //Comprobamos que se haya introducido algo por teclado y no sea cadena vacía
       
       if(nombre_equipo.equals(""))
       {
            System.out.println("No se ha introducido ningún equipo.");
       }
       else
       {
            
            //Sentencia SQL que usaremos para instanciar la query
            String select1 = "SELECT j.nombre, j.posicion FROM Jugadores j WHERE j.equipos.nombre=:nombre_equipo";
            
            //Consulta a ejecutar
            Query consulta_jugadores = session.createQuery(select1);
            //Seteamos el parámetro de la consulta
            consulta_jugadores.setParameter("nombre_equipo", nombre_equipo);

            //Guardamos el resultado de la consulta en una lista de jugadores
            List<Object[]> jugadores = (List<Object[]>)consulta_jugadores.list();
            
            //Almacenamos el número de registros que ha devuelto la consulta (número de jugadores del equipo)
            int num_jugadores = jugadores.size();
            
            //Si se ha encontrado el equipo (el resultado ha devuelto algo) se procede a sacar la información por pantalla
            if(!jugadores.isEmpty())
            {
                
                System.out.println("Jugadores del equipo "+nombre_equipo+":\n----------------------------------------");

                //Recorremos los registros almacenados en la lista con los valores recogidos por la consulta y los mostramos
                for(Object[] o: jugadores)
                {
                    System.out.println("Nombre: "+o[0]+", Posición: "+o[1]);
                }

                 System.out.println("----------------------------------------\nNúmero de jugadores del equipo:");

                 //Sacamos el número de jugadores del equipo
                 System.out.println(num_jugadores);
            }
            //Si la lista esta vacía es porque el equipo no existe, por lo que se informa al usuario
            else
            {
                 System.out.println("Este equipo no existe.");
            }
            
            //Cerramos la sesión y la SessionFactory
            session.close();
            sessionfactory.close();
       }
    }
}
