/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Examen;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import BeansExamen.*;
import com.ibm.icu.util.Calendar;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import net.xqj.exist.ExistXQDataSource;
import org.xmldb.api.base.Resource;

/**
 *
 * @author Usuario
 */
public class EjercicioExamen {
    
    // También se podría haber hecho con un enum
    public static final String[] meses = {"enero","febrero","marzo","abril","mayo","junio"};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            // Ayuda sobre los métodos importantes de GregorianCalendar
            GregorianCalendar calendar = new GregorianCalendar();
            // Devuelve la fecha guardada en un GregorianCalendar con el formato de fechas utilizado en el XML
            System.out.println("Fecha actual: " + calendar.toZonedDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
            // Devuelve el mes del objeto GregorianCalendar empezando en 0
            System.out.println("Mes actual (siendo 0 = Enero): " + calendar.get(Calendar.MONTH));
            
            // Obtenemos colección y creamos el fichero en nuestra BBDD
            Collection col = obtenColeccion("/pruebas");
            crearFichero(col);
            
            // Creamos conexión con la BBDD para utilizar la API XQJ
            XQConnection conexion = obtenConexion();
            
            // Instanciamos objetos ComprasDelMes y ClienteDelMes con un DNI especifico
            // y configuramos el receptor de eventos
            String dni = "78901234X";
            ComprasDelMes comprasDelMes = new ComprasDelMes();
            comprasDelMes.setDni(dni);
            
            ClienteDelMes clienteDelMes = new ClienteDelMes(dni);
            
            comprasDelMes.addPropertyChangeListener(clienteDelMes);
            
            // Obtenemos las unidadesTotales del cliente 78901234X en el mes de enero
            int unidadesTotales = consultaPedidos(dni, "20210101", "20210131",conexion);
            comprasDelMes.setUnidades(unidadesTotales);
            
            // Comprobamos si cumple con la condición
            if(clienteDelMes.isEsClienteDelMes()){ 
                insertarClienteMes(clienteDelMes, conexion, 0);
            }
            
            else{
                System.out.println("No cumple con la condición, por lo que no es cliente del mes");    
            }
            
            System.out.println("Unidades totales: " + comprasDelMes.getUnidades());
            
        } catch (Exception ex) {
            Logger.getLogger(EjercicioExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Collection obtenColeccion(String nomCol) throws Exception {
        Database dbDriver;
        Collection col;
        // Cargamos driver y obtenemos una instancia de la BBDD
        dbDriver = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
        // Registramos el driver
        DatabaseManager.registerDatabase(dbDriver);
        // Obtenemos una colección determinada
        col = DatabaseManager.getCollection("xmldb:exist://localhost:8090/exist/xmlrpc/db" + nomCol,
                "admin", "admin");

        return col;
    }
          
    private static XQConnection obtenConexion() throws XQException {
        XQConnection con = null;
        XQDataSource db = new ExistXQDataSource();
        db.setProperty("serverName", "localhost");
        db.setProperty("port", "8090");
        db.setProperty("user", "admin");
        db.setProperty("password", "admin");

        con = db.getConnection();

        return con;
    }
    
    public static void crearFichero(Collection coleccion) throws XMLDBException{
        try {
            // Rellenar
            String nuevo_recurso = "clientes_del_mes.xml";
            
            //Obtenemos los recursos que hay en la colección pruebas para poder comprobar si existe el archivo que vamos a crear
            String[] child_collections = coleccion.listResources();
            
            boolean existe = false;
            
            //Recorremos los recursos de la colección pruebas
            for(String s: child_collections)
            {
                //Si el nombre de algún recurso coincide con el que vamos a crear, significa que existe
                if(s.equalsIgnoreCase(nuevo_recurso))
                {
                    existe = true;
                    break;
                }
            }
            
            //Si no existe, creamos el recurso
            if(!existe)
            {
                Resource clientesDelMes = coleccion.createResource(nuevo_recurso, XMLResource.RESOURCE_TYPE);
                
                //Asignamos contenido
                clientesDelMes.setContent("<anio_2021><enero/><febrero/><marzo/><abril/><mayo/><junio/></anio_2021>");
                
                //Guardamos el recurso
                coleccion.storeResource(clientesDelMes);
            }
            //Si ya existe, informamos al usuario
            else
            {
                System.out.println("El fichero ya existe en la BBDD");
            }
        } catch (Exception ex) {
            Logger.getLogger(EjercicioExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Formato fechaInicio y fechaFinal: YYYYMMDD (AñoMesDia)
    public static int consultaPedidos(String dni, String fechaInicio, String fechaFinal, XQConnection conexion) throws XQException{
    
        // Rellenar
        //Consulta que devuelve lo necesario
        /*for $producto in doc('/db/pruebas/compras.xml')/compras/producto
return if(compare(translate($producto/fecha,'-',''),'20210101')=1 and compare(translate($producto/fecha,'-',''),'20210131')=-1 and $producto/cliente/@DNI='78901234X')then xs:int($producto/unidades) else()
        */
        String query = "for $producto in doc('/db/pruebas/compras.xml')/compras/producto " +
"return if(compare(translate($producto/fecha,'-',''),'"+fechaInicio+"')=1 and compare(translate($producto/fecha,'-',''),'"+fechaFinal+"')=-1 and $producto/cliente[@DNI='"+dni+"']) then xs:int($producto/unidades) else()";
        int unidadesTotales = 0;
        
        XQExpression expresion = conexion.createExpression();
        
        XQResultSequence secuencia = expresion.executeQuery(query);
        
        while(secuencia.next())
        {
            unidadesTotales += secuencia.getInt();
        }
        
        return unidadesTotales;
    }

    // mesActualizado -> entero entre 0 y 11 que indica un mes del año (0 ->enero, 1->febrero...)
    public static void insertarClienteMes(ClienteDelMes cliente, XQConnection conexion, int mesActualizado) throws XQException{
    
        // Rellenar
        String fechaLogro = cliente.getFechaLogro().get(Calendar.YEAR)+"-"+(cliente.getFechaLogro().get(Calendar.MONTH)+1)+"-"+cliente.getFechaLogro().get(Calendar.DAY_OF_MONTH);
    
        XQExpression expresion = conexion.createExpression();
        
        XQResultSequence secuencia = expresion.executeQuery("count(doc('/db/pruebas/clientes_del_mes.xml')/anio_2021/"+meses[mesActualizado]+"/cliente[/DNI='"+cliente.getDni()+"'])");
        
        secuencia.next();
        
        if(secuencia.getInt()!=0)
        {
            expresion.executeCommand("update insert <cliente><DNI>'"+cliente.getDni()+"'</DNI><fecha_logro>'"+fechaLogro+"'</fecha_logro><unidades_totales>'"+cliente.getUnidadesTotales()+"'</unidades_totales></cliente> into doc('/db/pruebas/clientes_del_mes.xml')/anio_2021/"+meses[mesActualizado]);
        }
        else
        {
            //update value ------ with ------
            expresion.executeCommand("update value doc('/db/pruebas/clientes_del_mes.xml')/anio_2021/"+meses[mesActualizado]+"/cliente[/DNI='"+cliente.getDni()+"']/fecha_logro with '"+fechaLogro+"'");
            expresion.executeCommand("update value doc('/db/pruebas/clientes_del_mes.xml')/anio_2021/"+meses[mesActualizado]+"/cliente[/DNI='"+cliente.getDni()+"']/unidades_totales with '"+cliente.getUnidadesTotales()+"'");
        }
        
        expresion.close();
        
    }
}
