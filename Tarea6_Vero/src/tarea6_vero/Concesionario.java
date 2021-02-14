/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tarea6_vero;

/**
 *
 * @author Usuario
 */
public class Concesionario {
    
    //Atributo que permite almacenar más de un objeto vehículo
    private static Vehiculo[] vehiculos;
    //Atributo que nos permitirá conocer el tamaño del array
    private static int tamannio_array;
    
    //Constructor vacío
    public Concesionario()
    {
        vehiculos = new Vehiculo[50];
        tamannio_array=0;
    }
    
    //Constructor parametrizado
    public Concesionario(Vehiculo[] p_vehiculos)
    {
        vehiculos=p_vehiculos;
        tamannio_array=p_vehiculos.length;
    }
    
    //Va a recorrer la lista de vehiculos de la clase concesionario y devolverá el que tenga una matrícula
    //que coincida con la insertada por parámetro o null si no encuentra ninguno
    public String buscaVehiculo(String matricula)
    {
        //Bucle que recorre la lista de vehículos
        for(int x=0;x<tamannio_array;x++)
        {
            //Obtenemos el vehículo en la posición x del array y lo guardamos
            Vehiculo v = (Vehiculo)vehiculos[x];
            
            //Obtenemos la matrícula del vehículo y comprobamos si coincide con la del parámetro
            if(v.getMatricula().equals(matricula))
            {
                return v.toString();
            }
        }
        
        return null;
    }
    
    //Este método va a instanciar un objeto vehículo y lo va a insertar en la lista de vehículos de la clase
    //Devolverá 0 si se ha insertado con éxito, -1 si la lista está llena o -2 si la matrícula ya existe
    public int insertarVehiculo(String marca, String matricula, int annio, int mes, int dia, String descripcion, String nombre_propietario, String dni_propietario, int n_kilometros, float precio)
    {
        //Instanciamos el objeto vehículo con los parámetros del método
        Vehiculo v = new Vehiculo(marca,matricula,annio,mes,dia,descripcion,nombre_propietario,dni_propietario,n_kilometros,precio);
        
        //Comprobamos que la lista no esté llena
        if(tamannio_array==50)
        {
            return -1;
        }
        
        //Comprobamos que la matrícula no pertenezca a otro vehículo
        if(buscaVehiculo(matricula)!=null)
        {
            return -2;
        }
        
        //Insertamos el vehículo en la lista en la última posición disponible
        vehiculos[tamannio_array] = v;
        tamannio_array = vehiculos.length;
        
        return 0;
    }
    
    //Sacará por pantalla los datos de todos los vehículos que haya en la lista
    public void listVehiculos()
    {
        //Recorrer la lista de vehículos
        for(int x=0;x<tamannio_array;x++)
        {
            //Obtenemos el vehículo
            Vehiculo v = (Vehiculo)vehiculos[x];

            //Mostrar los datos por pantalla
            System.out.println(v.toString());
        }
    }
    
    //Método que actualizar los km de un vehículo concreto y devuelve true si se ha realizado con éxito
    //o false si no se ha encontrado
    public boolean actualizaKms(String matricula, int km)
    {
        //Recorrer la lista de vehículos
        for(int x=0;x<tamannio_array;x++)
        {
            //Almacenamos el vehículo
            Vehiculo v = (Vehiculo)vehiculos[x];
            
            //Comprobamos si el vehículo del bucle tiene la misma matrícula que el parámetro
            if (v.getMatricula().equals(matricula)) 
            {
                //Actualizamos km
                v.setN_kilometros(km);
                
                //Devolvemos true
                return true;
            }
            
        }
        
        return false;
        
    }
}
