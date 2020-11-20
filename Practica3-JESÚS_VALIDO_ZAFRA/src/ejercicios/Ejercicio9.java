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
        
        //Consulta que devuelve todos los equipos
        Query consulta_equipos = session.createQuery("SELECT e FROM Equipos e");
        List<Equipos> lista_equipos = consulta_equipos.list();
        
        //Creamos dos sets: de jugadores y de estadísticas
        Set<Jugadores> lista_jugadores;
        Set<Estadisticas> lista_estadisticas;
        
        //Instanciamos un jugador que será el que borraremos
        Jugadores jugador_menos_puntos=null;
        
        //Recorremos la lista de equipos
        for(Equipos e: lista_equipos)
        {
            //Iniciamos la transacción
            Transaction transaccion = session.beginTransaction();
            //Variable que almacenará los puntos mínimos
            float menos_puntos=10000f;
            //Jugadores que hay en el equipo
            lista_jugadores = e.getJugadoreses();
            
            //Recorremos los jugadores de ese equipo
            for(Jugadores j: lista_jugadores)
            {
                //Obtenemos las estadísticas de ese jugador
                lista_estadisticas = j.getEstadisticases();
                
                //Recorremos las estadísticas
                for(Estadisticas es: lista_estadisticas)
                {
                    //Si los puntos por partido son inferiores al mínimo y la temporada es la del 07/08 entra en la condición
                    if(es.getPuntosPorPartido()<menos_puntos && es.getId().getTemporada().equals("07/08"))
                    {
                        //Se actualiza el valor de la variable con el mínimo de puntos
                        menos_puntos=es.getPuntosPorPartido();
                        //Se almacena el jugador con menos puntos
                        jugador_menos_puntos = j;
                    }
                }
            }
            
            //Se muestra por pantalla el jugador que se va a borrar y el equipo al que pertenece
            System.out.println("Peor jugador del equipo "+jugador_menos_puntos.getEquipos().getNombre()+": "+jugador_menos_puntos.getNombre());
            
            //Se recorren las estadísticas del jugador a eliminar
            for(Estadisticas es: jugador_menos_puntos.getEstadisticases())
            {
                //Se van borrando las estadísticas
                session.delete(es);
            }
            
            //Una vez borradas las estadísticas se borra al jugador 
            session.delete(jugador_menos_puntos);
            
            //Se hace un commit
            transaccion.commit();
            
            //Se informa al usuario
            System.out.println("Jugador borrado con éxito.");
            
        }
        
        //Cerramos recursos
        session.close();
        sessionfactory.close();
    }
}
