/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modif_sueldo_bruto;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import gest_proyectos.Empleado;

/**
 *
 * @author Usuario
 */

public class ModifSueldoBruto {
    
    private static MtDatabase db = null;
    
    public static void main(String[] args)
    {
        db = new MtDatabase("localhost", "AcDat_BDO");
        db.open();
        db.startTransaction();
        
        try
        {
            //Buscamos un empleado con un dni elegido al azar
            Empleado e2 = Empleado.lookupEmpleado_pk(db, "56789012B");
            
            //Si existe, operamos sobre él llamando al método subeSueldoBruto()
            if(e2!=null)
            {
                //Si tiene datos profesionales, se llama al método
                if(e2.getTiene_datos_prof()!=null)
                {
                    System.out.println(e2.subeSueldoBruto(25));
                }
                //Si no tiene datos profesionales, se informa al usuario
                else
                {
                    System.out.println("Este empleado no tiene datos profesionales.");
                }
            }
            //Si no se encuentra dicho empleado, se informa al usuario
            else
            {
                System.out.println("No se ha encontrado el empleado");
            }
            
            db.commit();
        }
        catch(MtException e)
        {
            e.printStackTrace();
        }
        
        
        
        if(db!=null)
        {
            db.close();
        }
    }
    
}
