/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebatema6;

import MisBeans.Pedido;
import MisBeans.BaseDatos;
import MisBeans.Producto;
import MisBeans.Venta;
import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class PruebaTema6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // MySQL
        String urldb
                = "jdbc:mysql://localhost:3306/javabeans?useUnicode=true&serverTimezone=UTC";
        String nombreDriver = "com.mysql.cj.jdbc.Driver";
        String usuario = "root";
        String clave = "root";
// Creamos un objeto BaseDatos
        BaseDatos db = new BaseDatos(urldb, usuario, clave, nombreDriver);
        db.setCrearConexion();
        if (db.isCrearConexion()) {
            System.out.println("Conectado!");
            System.out.println("---------------------------");
            System.out.println("LISTA INICIAL DE PRODUCTOS");
            verProductos(db);
// creamos una venta
            System.out.println("---------------------------");
            System.out.println("CREAMOS VENTA EN ID 3 CON CANTIDAD 2");
            crearVenta(db, 3, 2);
            System.out.println("---------------------------");
            System.out.println("LISTA DE PRODUCTOS DESPUÉS DE CREAR LA VENTA");
            verProductos(db);
        }
        System.out.println("---------------------------");
        System.out.println("LISTA DE VENTAS");
        verVentas(db);
        System.out.println("---------------------------");
        System.out.println("LISTA DE PEDIDOS");
        verPedidos(db);
    }
    
    private static void verProductos(BaseDatos db) {
// Rellenar
    
    for(Producto p: db.consultaPro("SELECT * FROM PRODUCTOS"))
    {
        System.out.println(p.toString());
    }
}
// -----------
// Insertar una venta
// -----------
private static void crearVenta(BaseDatos db, int idproducto, int cantidad) {
// Rellenar
    Date fecha_hoy = new Date();
    Venta venta = new Venta(db.obtenerUltimoIdMasUno("Ventas"),idproducto,);
    db.insertarVenta(null);
}

// -----------
// Ver pedidos creados
// -----------
private static void verPedidos(BaseDatos db){
// Rellenar
}
private static void verVentas(BaseDatos db) {
// Rellenar
}
private static Date getCurrentDate() {
java.util.Date hoy = new java.util.Date();
return new java.sql.Date(hoy.getTime());
}
}
}
// -----------
// Visualizar los productos
// -----------

