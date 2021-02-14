package paquete;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nikita
 */
public class Concesionario {
    
    //Atributo que permoite almacenar más de un objeto vehículo.
    private static Vehiculo[] vehiculos;
    //Atributo que nos permite conocer el tamaño del array.
    private static int tamanio_array;
    
    //constructor vacío, con inicializón de atributo
    public Concesionario (){
    vehiculos = new Vehiculo [50];
    tamanio_array=0;
    }
    
    //Constructor parametrizado
  
    public Concesionario (Vehiculo[] p_vehiculos){
    vehiculos=p_vehiculos;
    tamanio_array=p_vehiculos.length;
    
    }
   
//método que va a recorrer la lista de veiculo de la clase concesonario y devlvera el que tenga una matricula que coincida con la insertada por parametro o null si no encuentra ninguna
    
    public String buscaVehiculo (String matricula){
    // bucle que recorre la lista de vehiculos.
        for (int x=0;x<tamanio_array;x++){
        
            //obtenemos el vehiculo en la posicion x del array y guard
            Vehiculo v = (Vehiculo)vehiculos[x];
            //obtenemos la matricula del vehiculo y comprobamos si coincide con la del parametro
            if(v.getMatricula().equals(matricula))
            {
                return v.toString();
            }
        }
    
        return null;
        
    
    }
    
   ///Este método va a instanciar un objeto vehiculo y lo va a insertar en la lista de vehiculos de la clase.
    //Devolvera 0 si se ha insertao co exito, -1 si esta llena o -2 si la matricula ya existe
    public int insertarVehiculo (String marca, String matricula, int annio, int mes, int dia, String descripcion, String nombre_propietario, String dni_propietario, int n_kilometros, float precio){
        //instanciar objeto vehiculo con los parametrso del metodo
         
        Vehiculo v = new Vehiculo(marca,  matricula,  annio,  mes,  dia,  descripcion,  nombre_propietario,  dni_propietario, n_kilometros, precio);
    
        //Comprobamos que la lista no esté llena
        if (tamanio_array==50){
            return -1;
        }
        //comprobamos que la matricula no pertenezca a iotro vehiculo
        if (buscaVehiculo(matricula)!=null){
            return -2;
        }
        
        //Insertamos el vehiculo en la lista en la ultima posicion disponible.
        vehiculos [tamanio_array] = v;
        tamanio_array ++;
        
        return 0;
        
    }
    
    //metódo lista vehiculo para listar por pantalla todos los datos de los vehiculos que estan la lista
    
    public void listaVehiculo (){
        //Recorrer lista de vehículos
        for (int x=0;x<tamanio_array;x++){
            System.out.println(tamanio_array);
        //Obtemos el vehiculo
         Vehiculo v = (Vehiculo)vehiculos[x];   
        //Mostrar por pantalla todos los datos
        System.out.println(v.toString());
        }
    } 
   //metodo que actualiza los km de un vehiculo concreto y devuelve true si se ha realizdo con exito o false si no se ha encontrado
    
    public boolean actualizaKms(String matricula, int km){
        //recorerr lista de vehiculos
        for(int x=0;x<tamanio_array;x++){
        //almacenar el vehiculo
            Vehiculo v = (Vehiculo)vehiculos[x];
        //comprobamos si el vehiculo del bucle tiene la misma matricula que el parametro.
                
            if(v.getMatricula().equals(matricula)){
   
                //actualizamos km
                v.setN_kilometros(km);
                //devolvemos true
                return true;
            }
    }
       return false; 
    }
    
    
    
    }


  
    

