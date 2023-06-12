/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

import java.io.File;
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
public class GestionDB_Art {
    ODB odb = null;
    String desktop = System.getProperty("user.home")+ File.separator + "Desktop";
    String DB_NAME = "almacenDB"; 
    public int insertarArticulo(Articulo articulo){
        int resp = 0;
        try{
            //se abre la base de datos
                              
            odb = ODBFactory.open(DB_NAME);
            //Se almacena el articulo                   
            odb.store(articulo);
            
            resp = 1;
        }
        catch (Exception ex){
            System.out.println("Se ha producido un error al insertar el articulo: " + ex);
        }
        finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            return  resp;
        }
    }
    
    public Articulo buscarArtPorId(String id){
        Articulo articulo = null;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Articulo.class, Where.equal("idArt", id));
            Object arti_rucuperado = odb.getObjects(query).getFirst();
            articulo = (Articulo)arti_rucuperado;
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            
        }
        return  articulo;
    }
    
    public int modificarArtPorId(String id, Articulo art){
        int resp = 0;
        try{
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Articulo.class, Where.equal("idArt", id));
            Articulo artDB = (Articulo) odb.getObjects(query).getFirst();
            artDB.setNombreArt(art.getNombreArt());
            artDB.setDescripcionArt(art.getDescripcionArt());
            artDB.setMinimo(art.getMinimo()); 
            odb.store(artDB);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el articulo: " + ex);
            
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return resp;
    }
    
    public int borrarArtPorId(String id){
        Articulo articulo = null;
        int resp = 0;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Articulo.class, Where.equal("idArt", id));
            Object arti_rucuperado = odb.getObjects(query).getFirst();
            articulo = (Articulo)arti_rucuperado;
            odb.delete(articulo);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al borrar el articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return  resp;
    }     
        
    public String listarArticulos(){
        String resp = "";
         try{
             //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Articulo.class);
            Objects<Articulo> lista = odb.getObjects(query);
            
            Articulo art = new Articulo();
            while (lista.hasNext()){
                art = lista.next();
                resp += art.toString() + "\n";
            }
          } 
         catch (Exception ex){
            System.out.println("Se ha producido un error al leer el articulo: " + ex);
          }   
         finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }   
          }
        return  resp;
    }
    
    public int modificarStock(String id, int cantidad){
        int resp = 0;
        try{
            Articulo artDB = buscarArtPorId(id);          
            artDB.setStock(artDB.getStock()+ cantidad);
            odb = ODBFactory.open(DB_NAME);
            odb.store(artDB);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al modificar el stock del articulo: " + ex);
            
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return resp;
    }
    
    public ArrayList<Articulo> crearArrayArticulos(){
        ArrayList<Articulo> array = new ArrayList<Articulo>();
         try{
             //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(Articulo.class);
            Objects<Articulo> lista = odb.getObjects(query);
            
            Articulo art = new Articulo();
            while (lista.hasNext()){
                art = lista.next();
                array.add(art);
            }
          } 
         catch (Exception ex){
            System.out.println("Se ha producido un error al leer los articulos: " + ex);
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
