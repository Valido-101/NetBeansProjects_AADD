/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica5;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author Usuario
 */
public class Ejercicio2 {
    
    public static void main(String args[])
    {
        Scanner teclado = new Scanner(System.in);
        
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
        XQSequence result = expression.executeQuery("count(doc('db/pruebas/catalogo.xml')/CATALOG/CD)");
        
        result.next();
        
        id = result.getInt()+1;
        
        System.out.println(id);
        
        //Si no está vacío, insertamos el nuevo cd
        if(id!=0)
        {

            expression.executeCommand("update insert <CD><ID>"+String.valueOf(id)+"</ID><TITLE>"+parameters[0]+"</TITLE><ARTIST>"+parameters[1]+"</ARTIST><COUNTRY>"+parameters[2]+"</COUNTRY><COMPANY>"+parameters[3]+"</COMPANY><PRICE>"+parameters[4]+"</PRICE><YEAR>"+parameters[5]+"</YEAR></CD> into doc('db/pruebas/catalogo.xml')/CATALOG");
            
            expression.close();
            conn.close();
        }
        else
        {
            System.out.println("No se ha ejecutado la consulta correctamente");
        }
    }
    
}
