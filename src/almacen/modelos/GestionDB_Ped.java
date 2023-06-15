/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

import java.util.ArrayList;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author ptddd
 */
public class GestionDB_Ped {
    ODB odb = null;
    String DB_NAME = "almacenDB";
    
    public int insertarPedido(Pedido pedido){
        int resp = 0;
        try{
            //se abre la base de datos                   
            odb = ODBFactory.open(DB_NAME);
            //Se almacena el pedido                   
            odb.store(pedido);            
            resp = 1;
        }
        catch (Exception ex){
            System.out.println("Se ha producido un error al insertar el pedido: " + ex);
        }
        finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            return  resp;
        }
    }
    
    public String listarPedidos(){
       String resp = "";
         try{
             //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Pedido.class);
            Objects<Pedido> lista = odb.getObjects(query);           
            Pedido ped = new Pedido();
            while (lista.hasNext()){
                ped = lista.next();
                resp += ped.toString() + "\n";
            }
          } 
         catch (Exception ex){
            System.out.println("Se ha producido un error al leer el pedido: " + ex);
          }   
         finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }   
          }
        return  resp; 
    }
    
    public ArrayList<Pedido> crearArrayPedidos(){
        ArrayList<Pedido> array = new ArrayList<Pedido>();
         try{
             //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Pedido.class);
            Objects<Pedido> lista = odb.getObjects(query);
            
            Pedido ped = new Pedido();
            while (lista.hasNext()){
                ped = lista.next();
                array.add(ped);
            }
          } 
         catch (Exception ex){
            System.out.println("Se ha producido un error al leer el proveedor: " + ex);
          }   
         finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }   
          }
        return  array;
    }
}
