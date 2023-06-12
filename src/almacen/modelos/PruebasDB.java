/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

import java.util.ArrayList;

/**
 *
 * @author ptddd
 */
public class PruebasDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GestionDB_Art gDbArt = new GestionDB_Art();
        String articulos = gDbArt.listarArticulos();
        System.out.println(articulos.length());
        
        
        //Articulo art = new Articulo("1","clavos1","clavos pequeños",500,20);
        //gDbArt.insertarArticulo(art);
        Articulo artdb = gDbArt.buscarArtPorId("A11");
        System.out.println(artdb);
        //gDbArt.borrarArtPorId("1");
        
        System.out.println("Texto: " + articulos);
        
        ArrayList<Articulo> arrArt = new ArrayList<Articulo>();
        
       arrArt = gDbArt.crearArrayArticulos();
       for (Articulo art: arrArt){
           art.toString();
       }
    }
    
}
