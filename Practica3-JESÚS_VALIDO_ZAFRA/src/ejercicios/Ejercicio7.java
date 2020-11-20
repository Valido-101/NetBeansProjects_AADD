/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import singleton.HibernateUtil;
import dam.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author Jesús
 */
public class Ejercicio7 {
    public static void main(String[] args)
    {
        //Obtenemos SessionFactory
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

        //Obtenemos la Session
        Session session = sessionfactory.openSession();
        
        //Creamos la consulta que busque cuantos equipos hay para comprobar si el total es par
        Query q_num_equipos = session.createQuery("SELECT COUNT(e.nombre) FROM Equipos e");
        long num_equipos = (long)q_num_equipos.uniqueResult();
        
        //Comprobamos si es par
        if(num_equipos%2==0)
        {
            
            //Obtenemos el id mayor de todos los partidos
            Query q_id_partidos = session.createQuery("SELECT MAX(p.id) FROM Partidos p");
            int id_partidos = (int)q_id_partidos.uniqueResult();
            
            //Obtenemos todos los equipos
            Query q_equipos = session.createQuery("SELECT e FROM Equipos e");
            List<Equipos> equipos = q_equipos.list();
            
            //Repetimos un bucle la mitad de veces que equipos haya en total
            for(int x=0;x<num_equipos/2;x++)
            {
                //Comenzamos la transacción
                Transaction transaccion = session.beginTransaction();
                
                //Aumentamos el id de partidos para que se introduzca uno por encima del mayor
                id_partidos++;
                
                //Creamos un nuevo partido
                Partidos nuevo_partido = new Partidos();
                
                //Obtenemos dos equipos aleatorios de la lista y los eliminamos para que no haya oportunidad de que sea el mismo
                //equipo dos veces
                Equipos equipo1  = equipos.get((int)(Math.random()*equipos.size()));
                equipos.remove(equipo1);
                Equipos equipo2 = equipos.get((int)(Math.random()*equipos.size()));
                equipos.remove(equipo2);
                
                //Seteamos los atributos necesarios al nuevo partido
                nuevo_partido.setCodigo((int)id_partidos);
                nuevo_partido.setEquiposByEquipoVisitante(equipo1);
                nuevo_partido.setEquiposByEquipoLocal(equipo2);
                nuevo_partido.setTemporada("08/09");
                
                //Insertamos el nuevo partido en la base de datos
                session.save(nuevo_partido);
                
                //Hacemos el commit
                transaccion.commit();
            }
            
            //Informamos al usuario
            System.out.println("Operación realizada con éxito.");
        } 
        //Cerramos los recursos
        session.close();
        sessionfactory.close();
    }
}
