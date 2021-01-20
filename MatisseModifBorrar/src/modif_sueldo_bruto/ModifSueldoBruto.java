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
            Empleado e2 = Empleado.lookupEmpleado_pk(db, "56789012B");
            
            if(e2!=null)
            {
                if(e2.getTiene_datos_prof()!=null)
                {
                    System.out.println(e2.subeSueldoBruto(25));
                }
                else
                {
                    System.out.println("Este empleado no tiene datos profesionales.");
                }
            }
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
