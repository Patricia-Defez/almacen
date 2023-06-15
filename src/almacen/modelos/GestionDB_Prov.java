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
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author ptddd
 */
public class GestionDB_Prov {
    ODB odb = null;
    String DB_NAME = "almacenDB";
    
    public int insertarProveedor(Proveedor proveedor){
        int resp = 0;
        try{
            //se abre la base de datos                              
            odb = ODBFactory.open(DB_NAME);
            //Se almacena el proveedor                   
            odb.store(proveedor);           
            resp = 1;
        }
        catch (Exception ex){
            System.out.println("Se ha producido un error al insertar el proveedor: " + ex);
        }
        finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            return  resp;
        }       
    }
    
    public Proveedor buscarProvPorId(String id){
        Proveedor proveedor = null;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Proveedor.class, Where.equal("idProv", id));
            Object prov_rucuperado = odb.getObjects(query).getFirst();
            proveedor = (Proveedor)prov_rucuperado;
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el proveedor: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return  proveedor;
    }
    
    public int modificarProvPorId(String id, Proveedor prov){
        int resp = 0;
        try{
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Proveedor.class, Where.equal("idProv", id));
            Proveedor provDB = (Proveedor) odb.getObjects(query).getFirst();
            provDB.setNombre(prov.getNombre());
            provDB.setTelefono(prov.getTelefono());
            provDB.setCIF(prov.getCIF()); 
            odb.store(provDB);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el proveedor: " + ex);
            
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return resp;
    }
    
    public int borrarProvPorId (String id){
        Proveedor proveedor = null;
        int resp = 0;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Proveedor.class, Where.equal("idProv", id));
            Object prov_rucuperado = odb.getObjects(query).getFirst();
            proveedor = (Proveedor)prov_rucuperado;
            odb.delete(proveedor);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al borrar el proveedor: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return  resp;
    }
    
    public String listarProveedores(){
        String resp = "";
         try{
             //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Proveedor.class);
            Objects<Proveedor> lista = odb.getObjects(query);
            
            Proveedor prov = new Proveedor();
            while (lista.hasNext()){
                prov = lista.next();
                resp += prov.toString() + "\n";
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
        return  resp;
    }
    
    
}
