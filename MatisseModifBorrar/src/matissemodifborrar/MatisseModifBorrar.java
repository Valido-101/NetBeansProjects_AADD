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
        
        EmpleadoPlantilla jefe_proyecto = new EmpleadoPlantilla(db);
        //insert into empleadoplantilla values("12345678X","VALIDO","123456",null,null,null)
        jefe_proyecto.setDni("12345678X");
        jefe_proyecto.setNom_emp("VALIDO");
        jefe_proyecto.setNum_emp("123456");
        
        db.commit();
        
        db.startTransaction();
        
        Proyecto p1 = new Proyecto(db);
        p1.setNom_proy("AI EN 5G");
        p1.setF_inicio(new GregorianCalendar(2019,11,5));
        p1.setJefe_proyecto(jefe_proyecto);
        
        Empleado e1 = (Empleado)Empleado.lookupEmpleado_pk(db, "89012345E");
        
        if(e1!=null)
        {
            e1.setNom_emp("VERDES");

            Empleado[] succs = new Empleado[1];

            succs[0]=e1;

            p1.appendTiene_asignado(succs);
            
        }
        
        Empleado e2 = (Empleado)Empleado.lookupEmpleado_pk(db, "76543210S");
        
        if(e2!=null)
        {
                if(e2.getAsignado_a().length!=0)
                {
                    e2.removeAsignado_a(e2.getAsignado_a());
                }

            /*
                if(e2.getTiene_datos_prof()!=null)
                {
                    e2.getTiene_datos_prof().deepRemove();

                }
            */
            
            e2.deepRemove();

        }
        
        Empleado e3 = (Empleado)Empleado.lookupEmpleado_pk(db, "56789012B");
        
        if(e3!=null)
        {
            e3.clearAsignado_a();
            e3.clearTiene_datos_prof();
        }
        
        db.commit();
        
        db.startTransaction();
        
        Iterator proyectos = Proyecto.ownInstanceIterator(db);
        
        while(proyectos.hasNext())
        {
            Proyecto p = (Proyecto)proyectos.next();
            
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
    
