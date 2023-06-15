/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author ptddd
 */
public class GestionDB_ArtProv {
    ODB odb = null;
    String DB_NAME = "almacenDB"; 
    
     public int insertarArtProv(ArticuloProveedor ArtProv){
        int resp = 0;
        try{
            //se abre la base de datos                             
            odb = ODBFactory.open(DB_NAME);
            //Se almacena el articulo                   
            odb.store(ArtProv);      
            resp = 1;
        }
        catch (Exception ex){
            System.out.println("Se ha producido un error al insertar el precio articulo: " + ex);
        }
        finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            return  resp;
        }
    }
     
     
    public ArticuloProveedor buscarArtProvPorIds(String idProv,String idArt){
        ArticuloProveedor ArtProv = null;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            ICriterion criterioBusqueda = new And().add(Where.equal("idProv", idProv)).add(Where.equal("idArt", idArt));
            IQuery query = new CriteriaQuery(ArticuloProveedor.class, criterioBusqueda);
            Object ArtProv_rucuperado = odb.getObjects(query).getFirst();
            ArtProv = (ArticuloProveedor)ArtProv_rucuperado;
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el precio articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            
        }
        return  ArtProv;
    } 
    
    public int modificarArtProvPorIds(String idProv, String idArt, ArticuloProveedor ArtProv){
        int resp = 0;
        try{
            odb = ODBFactory.open(DB_NAME);
            ICriterion criterioBusqueda = new And().add(Where.equal("idProv", idProv)).add(Where.equal("idArt", idArt));
            IQuery query = new CriteriaQuery(ArticuloProveedor.class, criterioBusqueda);
            ArticuloProveedor ArtProvDB = (ArticuloProveedor) odb.getObjects(query).getFirst();
            ArtProvDB.setPrecio(ArtProv.getPrecio());
            odb.store(ArtProvDB);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el precio articulo: " + ex);
            
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return resp;
    }
    
    public int borrarArtProvPorIds(String idProv, String idArt){
        ArticuloProveedor ArtProv = null;
        int resp = 0;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            ICriterion criterioBusqueda = new And().add(Where.equal("idProv", idProv)).add(Where.equal("idArt", idArt));
            IQuery query = new CriteriaQuery(ArticuloProveedor.class, criterioBusqueda);
            Object ArtProv_rucuperado = odb.getObjects(query).getFirst();
            ArtProv = (ArticuloProveedor)ArtProv_rucuperado;
            odb.delete(ArtProv);
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al borrar el precio articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return  resp;
    }
    
     public int borrarArtProvPorIdProv(String idProv){
        int resp = 0;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(ArticuloProveedor.class, Where.equal("idProv", idProv));
            Objects<ArticuloProveedor> lista = odb.getObjects(query);
            ArticuloProveedor ArtProv = new ArticuloProveedor();
            while (lista.hasNext()){
                ArtProv = lista.next();
                odb.delete(ArtProv);
            }        
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al borrar el precio articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return  resp;
    }
    
         public int borrarArtProvPorIdArt (String idArt){
        int resp = 0;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(ArticuloProveedor.class, Where.equal("idArt", idArt));
            Objects<ArticuloProveedor> lista = odb.getObjects(query);
            ArticuloProveedor ArtProv = new ArticuloProveedor();
            while (lista.hasNext()){
                ArtProv = lista.next();
                odb.delete(ArtProv);
            }        
            resp = 1;
            
        } catch (Exception ex){
            System.out.println("Se ha producido un error al borrar el precio articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
        }
        return  resp;
    } 
     
    public ArticuloProveedor compararPreciosPorIdArt(String idArt){
        ArticuloProveedor ArtProv = null;
        try{
            //se abre la base de datos
            odb = ODBFactory.open(DB_NAME);
            IQuery query = new CriteriaQuery(ArticuloProveedor.class, Where.equal("idArt", idArt));
            query.orderByAsc("precio");
            Object ArtProv_rucuperado = odb.getObjects(query).getFirst();
            ArtProv = (ArticuloProveedor)ArtProv_rucuperado;
        } catch (Exception ex){
            System.out.println("Se ha producido un error al buscar el precio articulo: " + ex);
        }   finally{
            if (odb != null){
                // Se cierra la base de datos
                odb.close();
            }
            
        }
        return  ArtProv;
    }
}
