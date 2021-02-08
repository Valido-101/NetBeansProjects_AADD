/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PROG05_Ejerc1;

import java.util.Scanner;
import PROG05_Ejerc1_util.DNI;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner teclado = new Scanner(System.in);
        Scanner teclado_subnormal = new Scanner(System.in);
        
        boolean salida = false;
        
        int opcion;
        
        Vehiculo v1 = null;
        
        String marca,matricula,descripcion,nombre_propietario,dni_propietario;
        int n_kilometros, annio, mes, dia;
        float precio;
        
        while(salida==false)
        {
            System.out.println("1. Nuevo Vehículo\n2. Ver Matrícula\n3. Ver Número de Kilómetros\n4. Actualizar Kilómetros\n5. Ver años de antigüedad\n6. Mostrar Propietario\n7. Mostrar Descripción\n8. Mostrar Precio\n9. Salir");
            
            opcion = teclado.nextInt();
            
            switch(opcion)
            {
                case 1:
                    System.out.println("Introduzca la marca:");
                    marca = teclado_subnormal.nextLine();
                    System.out.println("Introduzca la matrícula:");
                    matricula = teclado_subnormal.nextLine();
                    System.out.println("Introduzca año de matriculación:");
                    annio = teclado.nextInt();
                    System.out.println("Introduzca el mes de matriculación:");
                    mes = teclado.nextInt();
                    System.out.println("Introduzca el día de matriculación:");
                    dia = teclado.nextInt();
                    System.out.println("Introduzca la descripción:");
                    descripcion = teclado_subnormal.nextLine();
                    System.out.println("Introduzca el nombre del propietario:");
                    nombre_propietario = teclado_subnormal.nextLine();
                    System.out.println("Introduzca el dni del propietario:");
                    dni_propietario = teclado_subnormal.nextLine();
                    System.out.println("Introduzca el número de kilómetros:");
                    n_kilometros = teclado.nextInt();
                    System.out.println("Introduzca el precio:");
                    precio = teclado.nextFloat();
                                      
                    try
                    {
                        if(!DNI.validarNIF(dni_propietario))
                        {
                            throw new Exception("El dni introducido no es válido.");
                        }
                        
                        LocalDate fecha_actual = LocalDate.now();
                        
                        LocalDate fecha_matriculacion = LocalDate.of(annio, mes, dia);
                        
                        if(!fecha_matriculacion.isBefore(fecha_actual))
                        {
                            throw new Exception("La fecha de matriculación no puede ser anterior a la fecha de hoy.");
                        }
                        
                        if(n_kilometros<=0)
                        {
                            throw new Exception("El vehículo no puede tener 0 o menos kilómetros.");
                        }
                        
                        v1 = new Vehiculo(marca,matricula,annio,mes,dia,descripcion,nombre_propietario,dni_propietario,n_kilometros,precio);
                    
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                    
                case 2:
                    
                    if(v1!=null)
                    {
                        System.out.println(v1.getMatricula());
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 3:
                    
                    if(v1!=null)
                    {
                        System.out.println(v1.getN_kilometros());
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 4:
                    
                    if(v1!=null)
                    {
                        System.out.println("Introduzca un número de kilómetros:");
                        n_kilometros = teclado.nextInt();
                        
                        v1.setN_kilometros(n_kilometros);
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 5:
                    
                    if(v1!=null)
                    {
                        System.out.println(v1.get_Anios());
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 6:
                    
                    if(v1!=null)
                    {
                        System.out.println(v1.getNombre_propietario());
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 7:
                    
                    if(v1!=null)
                    {
                        System.out.println(v1.getDni_propietario());
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 8:
                    
                    if(v1!=null)
                    {
                        System.out.println(v1.getPrecio());
                    }
                    else
                    {
                        System.out.println("No ha creado ningún vehículo.");
                    }
                    
                    break;
                case 9:
                    
                    salida = true;
                    
                    break;
            }
        }
        
    }
    
}
