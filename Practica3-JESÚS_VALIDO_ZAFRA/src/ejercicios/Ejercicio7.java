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
        
        Query q_num_equipos = session.createQuery("SELECT COUNT(e.nombre) FROM Equipos e");
        long num_equipos = (long)q_num_equipos.uniqueResult();
        
        if(num_equipos%2==0)
        {

            Query q_id_partidos = session.createQuery("SELECT MAX(p.id) FROM Partidos p");
            int id_partidos = (int)q_id_partidos.uniqueResult();
            
            Query q_equipos = session.createQuery("SELECT e FROM Equipos e");
            List<Equipos> equipos = q_equipos.list();
            
            for(int x=0;x<num_equipos/2;x++)
            {
                Transaction transaccion = session.beginTransaction();
                
                id_partidos++;
                
                Partidos nuevo_partido = new Partidos();
                
                Equipos equipo1  = equipos.get((int)(Math.random()*equipos.size()));
                equipos.remove(equipo1);
                Equipos equipo2 = equipos.get((int)(Math.random()*equipos.size()));
                equipos.remove(equipo2);
                
                nuevo_partido.setCodigo((int)id_partidos);
                nuevo_partido.setEquiposByEquipoVisitante(equipo1);
                nuevo_partido.setEquiposByEquipoLocal(equipo2);
                nuevo_partido.setTemporada("08/09");
                
                session.save(nuevo_partido);
                
                transaccion.commit();
            }
            
            System.out.println("Operación realizada con éxito.");
        } 
        session.close();
        sessionfactory.close();
    }
}
