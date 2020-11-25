/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temas_1_y_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class InsertarDatos {
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        
        //Url de la conexión a la BBDD
        String url = "jdbc:mysql://localhost:3306/jesus-validozafra" + "?useUnicode=true"+ "&serverTimezone=Europe/Madrid";
        
        String nombre_cargo;
        int sueldo_min;
        int sueldo_max;
        
        try{
        
        //Instanciamos una conexión a la que pasamos por parámetro la url del schema, el usuario y la contraseña
        Connection con = DriverManager.getConnection(url, "jesus", "examen1");
			
        //Creamos una sentencia a raíz de la conexión creada
        Statement sentencia = con.createStatement();
        
        Statement sentencia2 = con.createStatement();
        
        ResultSet idcargo_max_bbdd = sentencia.executeQuery("SELECT MAX(idcargo) FROM cargo");
        
        idcargo_max_bbdd.next();
        
        String variable_cargo = idcargo_max_bbdd.getString(1);
        
        int nuevo_idcargo=Integer.parseInt(variable_cargo.substring(1));
        
        if(nuevo_idcargo!=99)
        {
            nuevo_idcargo++;
        
            System.out.println("Introduzca los valores del nuevo cargo: \nNombre:");

            nombre_cargo=teclado.nextLine();

            System.out.println("Sueldo mínimo:");

            sueldo_min=teclado.nextInt();

            System.out.println("Sueldo máximo:");

            sueldo_max=teclado.nextInt();

            sentencia2.execute("INSERT INTO cargo(idcargo, nombre, sueldo_min, sueldo_max) VALUES ('C"+nuevo_idcargo+"', '"+nombre_cargo+"',"+sueldo_min+","+sueldo_max+")");
        }
        else
        {
            System.out.println("No se pueden introducir más cargos");
        }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    }
}
