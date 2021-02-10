/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQSequence;
import net.xqj.exist.ExistXQDataSource;
import org.w3c.dom.Node;

/**
 *
 * @author Usuario
 */
public class Ejercicio2 {
    
    public static void main(String args[])
    {
        Scanner teclado = new Scanner(System.in);

        /*
        String[] parameters = new String[6];
        
        System.out.println("Introduzca los valores del nuevo CD:\n1:Título\n2:Artista\n3:País\n4:Compañía\n5:Precio\n6:Año\n");
        
        for(int x=0;x<parameters.length;x++)
        {   
            String valor=teclado.nextLine();
            
            if(valor.equals(""))
            {
                System.out.println("No puede tener valores vacíos");
                x--;
            }
            else
            {
                parameters[x]=valor;
            } 
        }
        
        try {
                nuevoCD(obtenConexion(),parameters);
            } catch (XQException ex) {
                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
            }
           */
        
        System.out.println("Introduzca un dni:");
        String dni = teclado.nextLine();
        try {
            numeroCompras(obtenConexion(),dni);
        } catch (XQException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        teclado.close();
    }
    
    public static XQConnection obtenConexion() throws XQException
    {
        //Configuramos servidor
        XQDataSource server = new ExistXQDataSource();
        server.setProperty("serverName","localhost");
        server.setProperty("port","8090");
        server.setProperty("user","admin");
        server.setProperty("password","admin");
        
        //Nos conectamos al servidor
        XQConnection conexion = server.getConnection("admin","admin");
        
        return conexion;
    }
    
    public static void nuevoCD(XQConnection conn, String[] parameters) throws XQException
    {
        int id;
        
        //Creamos la expresión
        XQExpression expression = conn.createExpression();
        
        //Ejecutamos la query y la almacenamos
        XQSequence result = expression.executeQuery("count(doc('/db/pruebas/catalogo.xml')/CATALOG/CD)");
        
        result.next();
        
        id = result.getInt()+1;
        
        //Si no está vacío, insertamos el nuevo cd
        if(id!=0)
        {

            expression.executeCommand("update insert <CD><ID>"+id+"</ID><TITLE>"+parameters[0]+"</TITLE><ARTIST>"+parameters[1]+"</ARTIST><COUNTRY>"+parameters[2]+"</COUNTRY><COMPANY>"+parameters[3]+"</COMPANY><PRICE>"+parameters[4]+"</PRICE><YEAR>"+parameters[5]+"</YEAR></CD> into doc('/db/pruebas/catalogo.xml')/CATALOG");
            
            expression.close();
            conn.close();
        }
        else
        {
            System.out.println("No se ha ejecutado la consulta correctamente");
        }
    }
    
    public static void modificarCP(XQConnection conn, String dni, int nuevo_cp) throws XQException, IOException
    {
        //Comprobamos si el cliente existe
        if(clienteExiste(dni,conn))
        {
            //Cogemos la fecha de hoy
            GregorianCalendar calendar = new GregorianCalendar();
        
            String fecha_hoy = ""+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            
            //Creamos la expresión para hacer la consulta
            XQExpression expression = conn.createExpression();
            
            //Recuperamos el anterior cp
            XQResultSequence resultado = expression.executeQuery("doc('/db/pruebas/clientes.xml')/clientes/cliente[@DNI='"+dni+"']/CP");
            
            resultado.next();
            
            int cp_anterior = resultado.getInt();
            
            //Ejecutamos la modificación
            expression.executeCommand("update value doc('/db/pruebas/clientes.xml')/clientes/cliente[@DNI='"+dni+"']/CP with "+nuevo_cp);
            
            //Creamos el objeto file que contendrá el paquete donde almacenaremos el registro
            File paquete_destino = new File("src/archivo_seguridad");
            
            //Comprobamos si existe o no
            
            //En el caso de que no exista
            if(!paquete_destino.exists())
            {
                paquete_destino.createNewFile();
            }
            
            File registro_mod = new File(paquete_destino,"registro_cambios.txt");
            
            if (!registro_mod.exists()) 
            {
                registro_mod.createNewFile();
            }
            
            //Creamos el bufferedwriter para escribir, en el 
            BufferedWriter escritor = new BufferedWriter(new FileWriter(registro_mod,true));
            
            expression.close();
        }
        //Si no existe, se informa al usuario
        else
        {
            System.out.println("El usuario con DNI = "+dni+" no se encuentra en la base de datos");
        }
        
        conn.close();
    }
    
    public static void numeroCompras(XQConnection conn, String dni) throws XQException
    {
        //Comprobamos si el cliente existe
        if(clienteExiste(dni,conn))
        {
            //Creamos la expresión para hacer la consulta
            XQExpression expression = conn.createExpression();
            
            //Ejecutamos la consulta y guardamos el resultado
            XQResultSequence resultado = expression.executeQuery("count(doc('/db/pruebas/compras.xml')/compras/producto[cliente/@DNI='"+dni+"'])");
            
            resultado.next();
            
            int n_compras = resultado.getInt();
            
            System.out.println("El usuario con DNI = "+dni+" ha realizado "+n_compras+" compra/s.");
            
            expression.close();
        }
        //Si no existe, se informa al usuario
        else
        {
            System.out.println("El usuario con DNI = "+dni+" no se encuentra en la base de datos");
        }
        
        conn.close();
    }
    
    public static boolean clienteExiste(String dni, XQConnection conn) throws XQException
    {        
        XQExpression expression = conn.createExpression();
        
        XQResultSequence resultado = expression.executeQuery("count(doc('/db/pruebas/clientes.xml')/clientes/cliente[@DNI='"+dni+"'])");
        
        resultado.next();
        
        int recuento = resultado.getInt();
        
        expression.close();
        
        if(recuento==0)
        {
            return false;
        }
        else
        {
            return true;
        }
        
        
    }
    
}
