
import MisBeans.BaseDatos;
import MisBeans.Producto;
import MisBeans.Ventas;
import java.sql.Date;


//trabajo realizado por Fran Jesus y Santi
public class PruebaTema6 {

    public static void main(String[] args) {
// MySQL
        String urldb
                = "jdbc:mysql://localhost:3306/practicatema6?useUnicode=true&serverTimezone=Europe/Madrid";
        String nombreDriver = "com.mysql.cj.jdbc.Driver";
        String usuario = "root";
        String clave = "root";
// Creamos un objeto BaseDatos
        BaseDatos db = new BaseDatos(urldb, usuario, clave, nombreDriver);
        db.serCrearConexion();
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
            System.out.println("---------------------------");
            System.out.println("LISTA DE VENTAS");
            verVentas(db);
            System.out.println("---------------------------");
            System.out.println("LISTA DE PEDIDOS");
            verPedidos(db);
        }
    }
// -----------
// Visualizar los productos
// -----------

    private static void verProductos(BaseDatos db) {

        db.consultaPro("SELECT * FROM productos;");
    }


    private static void crearVenta(BaseDatos db, int idproducto, int cantidad) {
        Producto p1 = db.consultaUnProducto(3);
        Date date = getCurrentDate();
        
        Ventas v1 = new Ventas(1,3,date,2);
        db.insertarVenta(v1);
        db.actualizarStock(p1, cantidad, date);
        System.out.println("STOCK ACTUALIZADO");

    }


    private static void verPedidos(BaseDatos db) {
        db.consultaPro("SELECT * FROM pedidos;");
    }

    private static void verVentas(BaseDatos db) {
        db.consultaVen("SELECT ventas.Id,ventas.IdProducto,productos.Descripcion,ventas.Cantidad,ventas.FechaVenta FROM ventas,productos WHERE ventas.IdProducto=productos.Id;");
    }

    private static Date getCurrentDate() {
        java.util.Date hoy = new java.util.Date();
        return new java.sql.Date(hoy.getTime());
    }
}
