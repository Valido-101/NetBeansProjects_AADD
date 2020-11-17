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
import org.hibernate.Transaction;

public class Ejercicio6 {
    public static void main(String[] args)
    {
       Scanner teclado=new Scanner(System.in);
        String nombre_equipo;
        
        System.out.println("Introduzca un nombre de equipo:");
        nombre_equipo=teclado.nextLine();
        
       //Obtenemos SessionFactory
       SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
       
       //Obtenemos la Session
       Session session = sessionfactory.openSession();
       
       //Obtenemos el objeto de tipo Productos
       //Productos producto = (Productos)session.get(Productos.class, 1);
       //List<Jugadores> jugadores = (List<Jugadores>)
       
       String select1 = "SELECT j.nombre, j.posicion FROM Jugadores j WHERE j.equipos.nombre=:nombre_equipo";
       String select2 = "SELECT COUNT(j.nombre) FROM Jugadores j WHERE j.equipos.nombre=:nombre_equipo";
       
       Query consulta_jugadores = session.createQuery(select1);
       consulta_jugadores.setParameter("nombre_equipo", nombre_equipo);
       
       Query consulta_num_jugadores = session.createQuery(select2);
       consulta_num_jugadores.setParameter("nombre_equipo",nombre_equipo);
       
       List<Object[]> jugadores = (List<Object[]>)consulta_jugadores.list();
       
       long num_jugadores = (long)consulta_num_jugadores.uniqueResult();
       
       System.out.println("Jugadores del equipo "+nombre_equipo+":\n----------------------------------------");
       
       for(Object[] o: jugadores)
       {
           System.out.println("Nombre: "+o[0]+", Posición: "+o[1]);
       }
       
        System.out.println("----------------------------------------\nNúmero de jugadores del equipo:");
        
        System.out.println(num_jugadores);
    }
}
