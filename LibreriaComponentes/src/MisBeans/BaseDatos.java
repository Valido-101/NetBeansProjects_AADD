/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MisBeans;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class BaseDatos {
    private static Connection conexion;
    private String urldb;
    private String usuario;
    private String clave;
    private String driver;
    private boolean crearConexion;//para saber si se ha creado la conexion
    
    public BaseDatos(String urldb,String usuario, String clave, String driver){
        this.urldb=urldb;
        this.usuario=usuario;
        this.clave=clave;
        this.driver=driver;
        crearConexion=false;
    }
    
    public void serCrearConexion(){
        
        try{
        Class.forName(driver);
        conexion = DriverManager.getConnection(urldb,usuario,clave);
        crearConexion = true;
        }catch(ClassNotFoundException ex){
            System.out.println("Driver no encontrado");
        }catch(SQLException ex){
            System.out.println("Problema de Conexion");
        }
    }
    
    //devuelve el valor de la conexion
    public boolean isCrearConexion(){
        return crearConexion;
    }
    
    //devuelve el objeto conexion
    public Connection getConexion(){
        return conexion;
    }
    
    public void cerrarConexion(){
        try{
            conexion.close();
        }catch(SQLException ex){
            System.out.println("Error al cerrar la conexion");
        }
    }
    
    public ArrayList<Producto> consultaPro(String consulta){
        
         ArrayList<Producto> lista = new ArrayList<>();
         try{
             
             Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta);
             
             while(result.next()){
                 
                 lista.add(new Producto(result.getString(2),result.getInt(1),result.getInt(3),result.getInt(4),result.getFloat(5)));
                System.out.println("ID ->"+result.getInt(1)+": "+result.getString(2)+ " Stock:"+result.getInt(3)+" PVP:"+result.getInt(5)+ " STK minimo:"+result.getInt(4));
             }
             result.close();
             statement.close();
             
         }catch(SQLException sqle){
            System.out.println("Error de conexion");
         }
       
         return lista;
    }
     public ArrayList<Pedido> consultaPed(String consulta){
        
         ArrayList<Pedido> lista = new ArrayList<>();
         try{
             
             Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta);
             
             while(result.next()){
                 
                 lista.add(new Pedido(result.getInt(1),result.getInt(2),result.getDate(3),result.getInt(4)));
                 
               
             }
             result.close();
             statement.close();
             
         }catch(SQLException ex){
             System.out.println("Error  conexion");
         }
       
         return lista;
    }
      public ArrayList<Ventas> consultaVen(String consulta){
        
         ArrayList<Ventas> lista = new ArrayList<>();
         try{
             
             Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(consulta);
             
             while(result.next()){
                 
                 lista.add(new Ventas(result.getInt(1),result.getInt(2),result.getDate(5),result.getInt(4)));
                 System.out.println("ID VENTA ->"+result.getInt(1)+": "+result.getString(3)+ " CANTIDAD:"+result.getInt(4)+" FECHA:"+result.getDate(5));
               
             }
             result.close();
             statement.close();
             
         }catch(SQLException ex){
             System.out.println("Error  conexion");
         }
       
         return lista;
    }
    
    public int obtenerUltimoID(String tabla){
        
        int id = 0;
        String query = "SELECT MAX(ID) FROM "+ tabla+";";
        try{
            
            Statement statement = conexion.createStatement();
             ResultSet result = statement.executeQuery(query);
             
             if(result.next()){
                 id=result.getInt(1);
             }
             result.close();
             statement.close();
             
        }catch(SQLException ex){
            System.out.println("Error conexion");
        }
        
        
        
        return id;
    }
    
    public int insertarVenta(Ventas ven){
        
        int filas = 0;
        String insertar = "INSERT INTO VENTAS VALUES(?,?,?,?)";
        try{
            
            PreparedStatement ps = conexion.prepareStatement(insertar);
            ps.setInt(1,ven.getNumeroventa());
            ps.setInt(2,ven.getIdproducto());
            ps.setDate(3,ven.getFechaventa());
            ps.setInt(4,ven.getCantidad());
            filas = ps.executeUpdate();
            ps.close();
            System.out.println("VENTA INSERTADA");
            
        }catch(SQLException ex){
            
            System.out.println("ERROR AL INSERTAR");
        }
        
        return filas;
    }
    
    public int actualizarStock(Producto producto, int cantidad, Date fechaActual){
        //se crea un pedido
        Pedido p1=new Pedido(this.obtenerUltimoID("pedidos")+1,producto.getIdProducto(),fechaActual,cantidad);
        //guardo en variables lo que me vaya a hacer falta(aunque no es necesario)
        int id=this.obtenerUltimoID("pedidos")+1;
        int idP=producto.getIdProducto();
        //llamamos al listener de producto para que compruebe si el stockactual es mayor al minimo para saber si se puede realizar el pedido o no
        producto.addPropertyChangeListener(p1);
        producto.setStockActual(producto.getStockActual()-cantidad);
        int stock =producto.getStockActual();
        String insertar = "INSERT INTO ventas VALUES(?,?,?,?)";
        String actualizar="UPDATE productos SET StockActual=? WHERE Id=?";
        try{
             PreparedStatement ps = conexion.prepareStatement(actualizar);
             ps.setInt(1,stock );
             ps.setInt(2,idP);
             ps.executeUpdate();
             ps.close();
             System.out.println("Stock actualizado");
             
        }catch(SQLException ex){
            System.out.println("ERROR AL ACTUALIZAR");
        }
        try{
            //si no hay suficiente stock, pedir es true por lo que haria falta hacer un pedido 
            if(p1.isPedir()==true){
                //hago un pedido ejecutando un insert sobre la tabla
                PreparedStatement ps = conexion.prepareStatement(insertar);
                ps.setInt(1,id);
                ps.setInt(2,idP);
                ps.setDate(3,fechaActual);
                ps.setInt(4,cantidad);
                ps.executeUpdate();
                ps.close();
                return -1;
            
        }
            
            
        }catch(SQLException ex){
            System.out.println("ERROR AL Insertar");
        }
        return 1;
    }
    
    public Producto consultaUnProducto(int idProducto){
        
        Producto p1=new Producto() ;
        String query="SELECT * FROM productos WHERE Id="+idProducto+";";
        try{
            
            Statement statement = conexion.createStatement();
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                p1=new Producto(result.getString(2),result.getInt(1),result.getInt(3),result.getInt(4),result.getFloat(5));
            }
            statement.close();
            
            
        }catch(SQLException ex){
            System.out.println("ERROR");
        }
        
        return p1;
        
    }
 
    
}
