package paquete;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;
        

/**
 *Clase que gestiona las acciones y el menú
 * @author Verónica Aguilar
 * @version 1.0.0 Realizado: 5/02/2021
 */


public class Principal {
    
//Clase principal
    public static void main (String[] args){
        Concesionario concesionario = new Concesionario();
        
        Scanner teclado = new Scanner (System.in);
        Scanner teclado_dos = new Scanner (System.in);
        boolean salida = false;
        int opcion;
        
        String marca, matricula, descripcion, nombre_propietario, dni_propietario;
        int n_kilometros,annio, mes, dia;
        float precio;

        
        while(salida == false){
            //Menú del programa
            System.out.println("1. Nuevo Vehículo\n2. Listar Vehículos\n3. Buscra Vehículo\n4. Modificar Kilometros Vehículo\n5. Salir");
            opcion = teclado.nextInt();
            //Declaración switch
            switch(opcion){
                //Nos va a devolver por pantalla los datos que queremos que el usuario teclee
                case 1:
                    System.out.println("Introduzca la marca");
                    marca = teclado_dos.nextLine();
                    System.out.println("Introduzca la matrícula");
                    matricula= teclado_dos.nextLine();
                    System.out.println("Introduzca el año de la matriculacion");
                    annio = teclado.nextInt();
                    System.out.println("Introduzca el mes de matriculación");
                    mes = teclado.nextInt();
                    System.out.println("Introduzca el día de la matriculacion");
                    dia = teclado.nextInt();
                    System.out.println("Introduzca la descripción");
                    descripcion = teclado_dos.nextLine();
                    System.out.println("Introduzca el nombre del propietario");
                    nombre_propietario = teclado_dos.nextLine();
                    System.out.println("Introduzca el DNI");
                    dni_propietario = teclado_dos.nextLine();
                    System.out.println("Introduzca el número de Kilometros");
                    n_kilometros = teclado.nextInt();
                    System.out.println("Introduzca el Precio");
                    precio = teclado.nextFloat();
                    
                    //Manejo de excepciones
                    try{
                        String regex1 = "\\d{8}[A-HJ-NP-TV-Z]";
                        if(!Pattern.matches(regex1,dni_propietario))
                        {   
                            throw new Exception ("El dni no tiene el formato esperado");
                        
                        }
                        String regex2 = "\\d{4}[A-Z]{3}";
                        
                        if (!Pattern.matches(regex2,matricula)){
                            throw new Exception ("La matricula no tiene el formato esperado");
                        }
                        
                        //Instanciar e inicializar una variable int
                        int cont_espacios = 0;
                        
                        /*for(char c: nombre_propietario.toCharArray())
                        {
                            if(c==' ')
                            {
                                cont_espacios++;
                            }
                        }*/
                        
                        //For que recorre las posiciones de la cadena
                        for(int x=0;x<nombre_propietario.length();x++)
                        {
                            //Primera iteración del bucle
                            
                            //nombre_propietario.substring(0, 1) = J
                            if(nombre_propietario.substring(x, x+1).equals(" "))
                            {
                                cont_espacios++;
                            }
                        }
                        
                        if(cont_espacios<2)
                        {
                            throw new Exception("El nombre del propietario debe estar compuesto por un nombre y dos apellidos");
                        }
                        
                        LocalDate fecha_actual = LocalDate.now();
                      
                        LocalDate fecha_matriculacion = LocalDate.of(annio, mes,dia);
                        
                        //Va a devolver un mensaje de error si la fecha de matriculación no es anterior de la fecha actual
                        if(!fecha_matriculacion.isBefore(fecha_actual)){
                            
                        throw new Exception("La fecha de matriculacion tiene que ser anterior a la fecha actual");                            
 
                        }
                        
                     
                        //Va a devolver un mensaje de error si los km son <= 0
                        if(n_kilometros<=0){
                        
                        throw new Exception("Los Kilometros tienen que ser mayor a 0");
                }
                                
                  int resultado=  concesionario.insertarVehiculo (marca, matricula, annio, mes, dia, descripcion, nombre_propietario, dni_propietario, n_kilometros, precio);
                  switch(resultado){
                      case 0:
                          System.out.println("Se ha insertad el vehiculo con exito");
                      break;
                      
                      case -1:
                          System.out.println("La lista del concesionario está llena");
                      break;
                      case -2:
                          System.out.println("La matricula instertada ya existe");
                  }     break;
                    }       
                    
                    
                    
                    //Va a lanzar el mensaje "El nº introducido no es válido"
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                    
       //Los siguientes comprueban que v1 no sea null, que se haya creado objeto vehículo antes de intentar llamar a los métodos
                case 2:
                    concesionario.listaVehiculo();
                    
                    break;
                   
                case 3:
                    String matricula_buscar;
                    System.out.println("Introduce una matricula\n");
                    matricula_buscar = teclado_dos.nextLine();
                   
                 
                    System.out.println(concesionario.buscaVehiculo(matricula_buscar));
                    
                    break;
                    
               case 4:
                      String matricula_buscar1;
                      int km;  
                       System.out.println("Introduce una matricula\n");
                       matricula_buscar1 = teclado_dos.nextLine();
                       System.out.println("Introduce el nuemero de kms\n");
                       km = teclado.nextInt();
                       if(concesionario.actualizaKms(matricula_buscar1, km)){
                           System.out.println("Se han actualizado con exito los kms");
                       }
                           else{
                                   System.out.println("No se han actualizado los kms");
                                   }
                       
                    break;
                    
                case 5:
                    
                   salida =true;
                    
                    break;
                    
                 
                       
                    
            }
            
        }
        
    }
    
    
    
}

