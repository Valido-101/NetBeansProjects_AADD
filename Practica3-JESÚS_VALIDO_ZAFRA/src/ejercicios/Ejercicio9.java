/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios;

import dam.*;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import singleton.HibernateUtil;

/**
 *
 * @author Jesús
 */
public class Ejercicio9 {
    public static void main(String[] args)
    {
        //Obtenemos SessionFactory
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

        //Obtenemos la Session
        Session session = sessionfactory.openSession();
        
        Query consulta_equipos = session.createQuery("SELECT e FROM Equipos e");
        List<Equipos> lista_equipos = consulta_equipos.list();
        
        Set<Jugadores> lista_jugadores;
        Set<Estadisticas> lista_estadisticas;
        
        Jugadores jugador_menos_puntos=null;
        Set<Estadisticas> estadisticas_jugador_menos_puntos = null;
        
        
        
        for(Equipos e: lista_equipos)
        {
            Transaction transaccion = session.beginTransaction();
            float menos_puntos=10000f;
            lista_jugadores = e.getJugadoreses();
            
            for(Jugadores j: lista_jugadores)
            {
                lista_estadisticas = j.getEstadisticases();
                
                
                for(Estadisticas es: lista_estadisticas)
                {
                    if(es.getPuntosPorPartido()<menos_puntos && es.getId().getTemporada().equals("07/08"))
                    {
                        menos_puntos=es.getPuntosPorPartido();
                        jugador_menos_puntos = j;
                    }
                }
            }
            System.out.println("Peor jugador del equipo "+jugador_menos_puntos.getEquipos().getNombre()+": "+jugador_menos_puntos.getNombre());
            
            estadisticas_jugador_menos_puntos = jugador_menos_puntos.getEstadisticases();
            
            for(Estadisticas es: estadisticas_jugador_menos_puntos)
            {
                session.delete(es);
            }
            
            session.delete(jugador_menos_puntos);
            
            transaccion.commit();
            
            System.out.println("Jugador borrado con éxito.");
            
        }
    }
}
