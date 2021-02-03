/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica5;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

/**
 *
 * @author Usuario
 */
public class Ejercicio1 {
    
    public static void main(String[] args)
    {
     
        try {
            crearCopiaSeguridad("pruebas");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLDBException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Collection obtenColeccion(String nomCol) throws ClassNotFoundException, InstantiationException, XMLDBException, IllegalAccessException
    {
        String driver = "org.exist.xmldb.DatabaseImpl"; // driver para eXist
        Class c1; 
        
        c1 = Class.forName(driver); // Cargamos el driver
        Database database = (Database) c1.newInstance(); // Instancia de la BD
        DatabaseManager.registerDatabase(database); // Registro del driver
        String uri = "xmldb:exist://localhost:8090/exist/xmlrpc/db/"+nomCol; // Colec.
        String usu = "admin"; // usuario
        String pass = "admin"; // contraseña 
        
        Collection col = DatabaseManager.getCollection(uri,usu,pass);
        
        return col;
    }
    
    public static void crearCopiaSeguridad(String nomCol) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException
    {
        //Cogemos la fecha de hoy
        GregorianCalendar calendar = new GregorianCalendar();
        
        String fecha_hoy = ""+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        
        //Nos conectamos a la base de datos xml
        String driver = "org.exist.xmldb.DatabaseImpl"; // driver para eXist
        Class c1; 
        
        c1 = Class.forName(driver); // Cargamos el driver
        Database database = (Database) c1.newInstance(); // Instancia de la BD
        DatabaseManager.registerDatabase(database); // Registro del driver
        String uri = "xmldb:exist://localhost:8090/exist/xmlrpc/db/"+nomCol; // Colec.
        String usu = "admin"; // usuario
        String pass = "admin"; // contraseña 
        
        Collection col_pruebas = DatabaseManager.getCollection(uri,usu,pass);
        
        Collection col_parent = col_pruebas.getParentCollection();
        
        //Comprobamos que la colección "copia_seguridad" no exista
        String[] existing_cols = col_parent.listChildCollections();
        
        boolean exists = false;
        
        for(String s: existing_cols)
        {
            if(s.equals("copia_seguridad"))
            {
                exists = true;
            }
        }
        
        if(exists==false)
        {
            //Creamos la colección "copia_seguridad" en db
            CollectionManagementService mgtService = (CollectionManagementService)col_parent.getService("CollectionManagementService","1.0");
            mgtService.createCollection("copia_seguridad");
        }

        //Obtenemos la colección "copia_seguridad"
        Collection col_copia_seguridad = col_parent.getChildCollection("copia_seguridad");
        
        String regex = "^(.+)\\.xml";
        
        //Creamos el patrón para recuperar el trozo de texto deseado
        Pattern patron = Pattern.compile(regex);

        //Recorremos los nombres de los archivos xml
        for(String resource: col_pruebas.listResources())
        {
            //Obtenemos el archivo xml
            Resource archivo_xml = col_pruebas.getResource(resource);
            
            System.out.println(resource);
            
            //Creamos el matcher que nos permitirá encontrar y extraer dicho trozo del texto
            Matcher matcher = patron.matcher(resource);
            
            //Encontramos el patrón
            if(matcher.find())
            {
                System.out.println("Se han encontrado patrones");
            }
            else
            {
                System.out.println("No se han encontrado patrones");
            }
            
            System.out.println(matcher.group(1));
            
            //Creamos el archivo en el que vamos a copiar los datos
            Resource copia_archivo_xml = col_copia_seguridad.createResource("Copia_"+matcher.group(1)+"_"+fecha_hoy+".xml", XMLResource.RESOURCE_TYPE);
            
            //Copiamos el contenido de "archivo_xml" a la copia de seguridad
            copia_archivo_xml.setContent(archivo_xml.getContent());
            
            //Guardamos el archivo copiado en la colección de copias de seguridad
            col_copia_seguridad.storeResource(copia_archivo_xml);
        }
    }
    
}
