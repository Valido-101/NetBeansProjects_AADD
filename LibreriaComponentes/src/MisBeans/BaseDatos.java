/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MisBeans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class BaseDatos {
    
    private static Connection conexion; // para conectarse a la BBDD
    private String urldb; // url para acceder a la BBDD
    private String usuario; // usuario
    private String clave; // contraseña asociada al usuario
    private String driver; // driver para la conexión a la BBDD
    private boolean crearConexion; // atributo para saber si la conexión está o no creada
    
    public BaseDatos(String urldb, String usuario, String clave, String driver)
    {
        this.urldb = urldb;
        this.usuario=usuario;
        this.clave=clave;
        this.driver=driver;
        crearConexion=false;
    }
    
    public void setCrearConexion()
    {
        try
        {
            Class.forName(driver);
            conexion = DriverManager.getConnection(urldb, usuario, clave);
            crearConexion=true;
        }
        catch(ClassNotFoundException c)
        {
            c.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean isCrearConexion()
    {
        return crearConexion;
    }
    
    public Connection getConexion()
    {
        return conexion;
    }
    
    public void cerrarConexion()
    {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión. No hay ninguna conexión creada.");
        }
    }
    
    public ArrayList<Producto> consultaPro(String consulta)
    {
        
        ArrayList<Producto> lista = new ArrayList<>();
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            
            while(resultado.next())
            {
                lista.add(new Producto(resultado.getString(2),resultado.getInt(1),resultado.getInt(3),resultado.getInt(4),resultado.getFloat(5)));
            }
            
            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public int obtenerUltimoIdMasUno(String tabla)
    {
        int id = 1;
        String query = "SELECT MAX(ID) FROM "+tabla;
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            if(resultado.next())
            {
                id = resultado.getInt(1);
            }
            
            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public int insertarVenta(Venta venta)
    {
        int filas = 0;
        
        String insertar = "INSERT INTO VENTAS VALUES(?,?,?,?)";
        try {
            PreparedStatement sentencia_preparada = conexion.prepareStatement(insertar);
            
            sentencia_preparada.setInt(1, venta.getNumeroventa());
            sentencia_preparada.setInt(2,venta.getIdproducto());
            sentencia_preparada.setDate(3,venta.getFechaventa());
            sentencia_preparada.setInt(4, venta.getCantidad());
            
            filas = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filas;
    }
    
    public int actualizarStock(Producto producto, int cantidad, Date fechaActual)
    {
        Pedido pedido = new Pedido(this.obtenerUltimoIdMasUno("pedidos"),producto.getIdProducto(),fechaActual,cantidad);
        producto.addPropertyChangeListener(pedido);
        
        producto.setStockActual(producto.getStockActual()-cantidad);
        
        if(pedido.isPedir())
        {
            try {
                PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO PEDIDOS VALUES(?,?,?,?)");
                sentencia.setInt(1, this.obtenerUltimoIdMasUno("pedidos"));
                sentencia.setInt(2, producto.getIdProducto());
                sentencia.setDate(3,fechaActual);
                sentencia.setInt(4, cantidad);
                sentencia.execute();
                return -1;
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 1;
    }
    
    public Producto consultarProducto(int idProducto)
    {
        Producto producto = null;
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM PRODUCTOS WHERE ID="+idProducto);
            
            
            
            if(resultado.next())
            {
                producto = new Producto(resultado.getString(2),resultado.getInt(1),resultado.getInt(3),resultado.getInt(4),resultado.getFloat(5));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return producto;
    }
}
