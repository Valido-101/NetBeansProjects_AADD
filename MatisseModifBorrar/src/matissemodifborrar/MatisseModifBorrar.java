/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matissemodifborrar;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import gest_proyectos.*;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class MatisseModifBorrar {

    /**
     * @param args the command line arguments
     */
    private static MtDatabase db = null;
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            
        // Instanciamos un objeto de tipo MtDatabase que da acceso a nuestra BDO
        db = new MtDatabase("localhost", "AcDat_BDO");
        db.open();
        // Comenzamos con la transacción
        db.startTransaction();
        
        //Buscamos un empleado que tenga el dni que le vamos a asignar al nuevo empleado
        EmpleadoPlantilla jefe_proyecto = EmpleadoPlantilla.lookupEmpleadoPlantilla_i_dni(db, "12345678X");
        
        //Si no encuentra ninguno es por que no existe, por lo que lo creamos
        if(jefe_proyecto==null)
        {
            //Creamos un nuevo EmpleadoPlantilla
            jefe_proyecto = new EmpleadoPlantilla(db);
            //Asignamos los datos del EmpladoPlantilla
            jefe_proyecto.setDni("12345678X");
            jefe_proyecto.setNom_emp("VALIDO");
            jefe_proyecto.setNum_emp("123456");            
        }
        
        //Hacemos el primer commit
        db.commit();
        
        //Comenzamos la nueva transacción
        db.startTransaction();
        
        //Obtenemos todos los proyectos de la base de datos
        Iterator proyectos = Proyecto.ownInstanceIterator(db);
        
        Proyecto p1 = null;
        
        //Los recorremos
        while(proyectos.hasNext())
        {
            p1 = (Proyecto) proyectos.next();
            
            //Si ya hay alguno con este nombre, lo guardamos y salimos del bucle
            if(p1.getNom_proy().equals("AI EN 5G"))
            {
                break;
            }
            //Mientras no coincida, asignaremos a p1 el valor de null
            else
            {
                p1 = null;
            }
        }
        
        //Si p1 es null, el proyecto no existe, por lo que lo creamos. De lo contrario nos quedamos con el que ha encontrado el bucle
        if(p1==null)
        {
            p1 = new Proyecto(db);
            p1.setNom_proy("AI EN 5G");
            p1.setF_inicio(new GregorianCalendar(2019,11,5));
        }
        
        //Si el proyecto no tiene ya asignado como jefe de proyecto al empleado que teníamos, se asigna
        if(p1.getJefe_proyecto()!=jefe_proyecto)
        {
            //Asignamos un jefe de proyecto al proyecto que acabamos de crear
            p1.setJefe_proyecto(jefe_proyecto);
        }
        
        //Buscamos al empleado con dni
        Empleado e1 = (Empleado)Empleado.lookupEmpleado_pk(db, "89012345E");
        
        //Si no lo encuentra es porque no existe, así que lo creamos
        if(e1!=null)
        {
            //Le cambiamos el nombre
            e1.setNom_emp("VERDES");
            
            //Asignamos este empleado al proyecto
            p1.appendTiene_asignado(e1);            
        }
        
        //Seguimos el mismo proceso con el empleado 2
        Empleado e2 = (Empleado)Empleado.lookupEmpleado_pk(db, "76543210S");
        
        //Si lo encuentra, hacemos las operaciones
        if(e2!=null)
        {
            
            //Obtenemos la lista de empleados asignados al proyecto
            if(e2.getAsignado_a().length!=0)
            {
                //Borramos los sucesores de la relación
                e2.removeAsignado_a(e2.getAsignado_a());
            }

            /*
            Condición necesaria antes de haber realizado el ejercicio 3

            if(e2.getTiene_datos_prof()!=null)
            {
                e2.getTiene_datos_prof().deepRemove();

            }
            */
            
            e2.deepRemove();

        }
        
        //Obtenemos al tercer empleado
        Empleado e3 = (Empleado)Empleado.lookupEmpleado_pk(db, "56789012B");
        
        //Si no es null, operamos sobre él
        if(e3!=null)
        {
            //Borramos las relaciones
            e3.clearAsignado_a();
            e3.clearTiene_datos_prof();
        }
        
        db.commit();
        
        db.startTransaction();
        
        //Obtenemos un iterador de todos los proyectos de la base de datos
        Iterator proyectos1 = Proyecto.ownInstanceIterator(db);
        
        //Lo recorremos
        while(proyectos1.hasNext())
        {
            Proyecto p = (Proyecto)proyectos.next();
            
            //Ejecutamos el método muestraProyecto sobre cada proyecto
            muestraProyecto(p);
            
            System.out.println("\n");
        }
        
        db.commit();
        
        }
        catch(MtException e)
                {
                    e.printStackTrace();
                }
        finally
        {
            if(db!=null)
            {
                db.close();
            }
        }
        }
        
    
    public static void muestraProyecto(Proyecto p){
            // Queremos imprimir lo siguiente...
            // Nombre del proyecto + OID (identificador único del objeto)
            // Jefe del proyecto: DNI + Nombre
            // Empleados del proyecto (DNI + Nombre)
        
            try{
                
                System.out.println("Proyecto: \n"+p.getNom_proy()+" "+p.getMtOid());
                
                EmpleadoPlantilla jefe_p = p.getJefe_proyecto();
                
                System.out.println("Jefe de Proyecto: \n"+jefe_p.getDni()+" "+jefe_p.getNom_emp());
                
                Empleado[] empleados = p.getTiene_asignado();
                
                for(Empleado e: empleados)
                {
                    System.out.println("Empleado asignado al proyecto: "+e.getDni()+" "+e.getNom_emp());
                }
            }
            catch(MtException e){
            System.out.println("MtException: " + e.getMessage());
            }
            }
        }
    
