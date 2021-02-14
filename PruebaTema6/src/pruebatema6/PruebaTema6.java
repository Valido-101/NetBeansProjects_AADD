/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebatema6;

import MisBeans.Pedido;
import MisBeans.Producto;

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
        
        Producto producto = new Producto("desc",1,12,10,1.2f);
        Pedido pedido = new Pedido();
        
        producto.addPropertyChangeListener(pedido);
        
        producto.setStockActual(9);
    }
    
}
