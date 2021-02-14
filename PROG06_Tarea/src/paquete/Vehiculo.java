
package paquete;

import java.time.LocalDate;

/**
 *Clase que gestiona Vehiculo
 * @author Verónica Aguilar
 * @version 1.0.0 Realizado: 5/02/2021
 */

public class Vehiculo {
   //Variable de entorno
    private String marca, matricula, descripcion, nombre_propietario, dni_propietario;//datos de tipo String
    private int n_kilometros; //dato de tipo int
    private float precio; //dato de tipo float
    private LocalDate fecha_matriculacion; //dato de tipo LocalDate
    //Constructor parametrizado
    public Vehiculo(String marca, String matricula, int annio, int mes, int dia, String descripcion, String nombre_propietario, String dni_propietario, int n_kilometros, float precio) {
        
        this.marca = marca;
        this.matricula = matricula;
        this.fecha_matriculacion = LocalDate.of(annio, mes,dia);
        this.descripcion = descripcion;
        this.nombre_propietario = nombre_propietario;
        this.dni_propietario = dni_propietario;
        this.n_kilometros = n_kilometros;
        this.precio = precio;
        
    }
    //Get y Set
    public String getMarca(){
        return this.marca;
        
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca=" + marca + ", matricula=" + matricula + ", descripcion=" + descripcion + ", nombre_propietario=" + nombre_propietario + ", dni_propietario=" + dni_propietario + ", n_kilometros=" + n_kilometros + ", precio=" + precio + ", fecha_matriculacion=" + fecha_matriculacion + '}';
    }
    
    public void setMarca(String marca){
        
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFecha_matriculacion() {
        return fecha_matriculacion;
    }

    public void setFecha_matriculacion(int annio, int mes, int dia) {
        this.fecha_matriculacion = LocalDate.of(annio, mes,dia);
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_propietario() {
        return nombre_propietario;
    }

    public void setNombre_propietario(String nombre_propietario) {
        this.nombre_propietario = nombre_propietario;
    }

    public String getDni_propietario() {
        return dni_propietario;
    }

    public void setDni_propietario(String dni_propietario) {
        this.dni_propietario = dni_propietario;
    }

    public int getN_kilometros() {
        return n_kilometros;
    }

    public void setN_kilometros(int n_kilometros) {
        this.n_kilometros = n_kilometros;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    // Calcula los años transcurridos desde la fecha de matriculación hasta la actual.
    public int get_Anios(){
    //Con este capturamos la fecha actual de sistema.
        LocalDate fecha_actual = LocalDate.now();
        //Almacenamos la diferencia de la fecha actual menos el año matriculación.
        int annios = fecha_actual.getYear() - fecha_matriculacion.getYear();
    //delovemos el resultado.
    return annios;
                
    
}
}
