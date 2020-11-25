/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temas_1_y_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jesús
 */
public class LecturaFichero {
    public static void main(String[] args)
    {
        //Url de la conexión a la BBDD
        String url = "jdbc:mysql://localhost:3306/jesus-validozafra" + "?useUnicode=true"+ "&serverTimezone=Europe/Madrid";
        
        //Variable que guardará la consulta sql
	String sql = "";
        
        String linea="";
        
        try 
		{
			//Instanciamos el BufferedReader que leerá el documento
			BufferedReader lector = new BufferedReader(new FileReader("RecursosHumanos.sql"));
			
			//Instanciamos una conexión a la que pasamos por parámetro la url del schema, el usuario y la contraseña
			Connection con = DriverManager.getConnection(url, "jesus", "examen1");
			
			//Creamos una sentencia a raíz de la conexión creada
			Statement sentencia = con.createStatement();

			//Bucle while que se repite infinitamente
			while(true) 
			{
				//Leemos una línea del documento txt y la guardamos en la variable sql
				sql = lector.readLine();
				//Si la variable sql no es igual a null (indica que ha llegado al final del documento)
				if(sql!=null) 
				{
					if(!sql.contains("--"))
                                        {
                                            if(sql.contains(";"))
                                            {
                                                sentencia.execute(linea+sql);
                                                System.out.println("Sentencia '"+linea+sql+"' ejecutada con éxito.");
                                                sql="";
                                                linea="";
                                            }
                                            else
                                            {
                                                linea=sql;
                                            }
                                        }
				}
				//Si es igual a null, se rompe el bucle
				else 
				{
					break;
				}
			}
			
			//Cerramos los recursos
			sentencia.close();
			con.close();
			lector.close();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
