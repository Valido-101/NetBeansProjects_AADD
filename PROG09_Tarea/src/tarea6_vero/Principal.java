/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tarea6_vero;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
        Concesionario concesionario = new Concesionario();
        
        Scanner teclado = new Scanner(System.in);
        Scanner teclado_subnormal = new Scanner(System.in);
        
        boolean salida = false;
        
        int opcion;
        
        Vehiculo v1 = null;
        
        String marca,matricula,descripcion,nombre_propietario,dni_propietario,matricula_buscar,nombre_fichero;
        int n_kilometros, annio, mes, dia,km;
        float precio;
        
        //Nueva implementación tarea 9
        
        File fichero;
        
        //Preguntamos al usuario el nombre del fichero
        System.out.println("Introduzca el nombre del fichero en el que quiera guardar los datos:");
        
        nombre_fichero = teclado_subnormal.nextLine();
        
        //Creamos una expresión regular para comprobar si el usuario ha introducido la extensión del fichero
        String regexFile = "\\.out$";
        
        //Comprobamos y creamos el archivo
        if (Pattern.matches(regexFile, nombre_fichero)) 
        {
            fichero = new File("src"+File.separator+"tarea6_vero"+File.separator+nombre_fichero);
        }
        else
        {
            fichero = new File("src"+File.separator+"tarea6_vero"+File.separator+nombre_fichero+".out");
        }
        
        if (!fichero.exists())
        {
            try {
                fichero.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Si el fichero existe (y, por tanto, tiene vehículos cargados) lo leemos y guardamos los datos
        try 
        {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));

            //Creamos un bucle para leer el archivo. Sabremos que ha terminado cuando lance EOFException
            while(true)
            {
                Vehiculo v = (Vehiculo)ois.readObject();
                concesionario.insertarVehiculo(v.getMarca(), v.getMatricula(), v.getFecha_matriculacion().getYear(), v.getFecha_matriculacion().getMonthValue(), v.getFecha_matriculacion().getDayOfMonth(), v.getDescripcion(), v.getNombre_propietario(), v.getDni_propietario(), v.getN_kilometros(), v.getPrecio());
            }

        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(EOFException ex)
        {
            System.out.println("Vehículos cargados anteriormente:\n");
            
            //Cuando lance esta excepción, significa que ha llegado al final del archivo, así que mostramos lo obtenido
            concesionario.listVehiculos();
          
            System.out.println("");
            //Cerramos el flujo de lectura
            //ois.close();
            
        }
            
            catch (IOException ex) 
            {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        while(salida==false)
        {
            System.out.println("1. Nuevo Vehículo\n2. Listar Vehículos\n3. Buscar Vehículo\n4. Modificar Kms Vehículo\n5. Eliminar vehículo\n6. Salir");
            
            opcion = teclado.nextInt();
            
            switch(opcion)
            {
                case 1:
                    try
                    {
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
                                      
                    
                        /*String regex1 = "\\d{8}[A-HJ-NP-TV-Z]";
                        
                        if(!Pattern.matches(regex1, dni_propietario))
                        {
                            throw new Exception("El dni no tiene el formato esperado.");
                        }
                        */
                        
                        String regex2 = "\\d{4}[A-Z]{3}";
                        
                        if(!Pattern.matches(regex2,matricula))
                        {
                            throw new Exception("La matrícula no tiene el formato esperado");
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
                    
                        int resultado=concesionario.insertarVehiculo(marca, matricula, annio, mes, dia, descripcion, nombre_propietario, dni_propietario, n_kilometros, precio);
                        
                        switch(resultado)
                        {
                            case 0:
                                System.out.println("Se ha insertado el vehículo con éxito");
                                break;
                            case -1:
                                System.out.println("La lista del concesionario está llena");
                                break;
                            case -2:
                                System.out.println("La matrícula insertada ya existe");
                                break;
                        }
                    }
                    catch(java.util.InputMismatchException ex)
                    {
                        System.out.println("Uno de los datos no se ha introducido correctamente.");
                        
                        teclado.next();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    
                    
                    break;
                    
                case 2:
                    
                    concesionario.listVehiculos();
                    
                    break;
                case 3:
                                     
                    System.out.println("Introduce una matrícula:\n");
                    matricula_buscar=teclado_subnormal.nextLine();
                    
                    System.out.println(concesionario.buscaVehiculo(matricula_buscar));
                    
                    break;
                case 4:
                    
                    System.out.println("Introduce una matrícula:\n");
                    matricula_buscar=teclado_subnormal.nextLine();
                    System.out.println("Introduce el número de kilómetros:\n");
                    km = teclado.nextInt();
                    
                    if(concesionario.actualizaKms(matricula_buscar, km))
                    {
                        System.out.println("Se han actualizado con éxito los kilómetros");
                    }
                    else
                    {
                        System.out.println("No se han actualizado los kilómetros");
                    }
                    
                    break;
                //Nueva implementación
                case 5:
                    
                    System.out.println("Introduce una matrícula:\n");
                    matricula_buscar=teclado_subnormal.nextLine();
                    
                    if (concesionario.eliminarVehiculo(matricula_buscar))
                    {
                        System.out.println("Vehículo eliminado con éxito");
                    }
                    else
                    {
                        System.out.println("La matrícula insertada no existe");
                    }
                    
                    break;
                    
                case 6:
                    
                    System.out.println("Hasta la próxima");
                    
                    salida = true;
            try {
                //Guardamos los datos en el fichero
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero, false));
                
                //Recorremos la lista de Vehiculos del concesionario y los escribimos en el fichero
                for (Vehiculo v: concesionario.getVehiculos())
                {
                    oos.writeObject(v);
                }
                
                //Cerramos el flujo
                oos.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                    break;
            }
        }
        
    }
    
}
