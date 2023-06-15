/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.controladores;

import almacen.modelos.Articulo;
import almacen.modelos.ArticuloProveedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import almacen.modelos.GestionDB_Art;
import almacen.modelos.GestionDB_ArtProv;
import almacen.modelos.GestionDB_Ped;
import almacen.modelos.GestionDB_Prov;
import almacen.modelos.Pedido;
import almacen.modelos.Proveedor;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author ptddd
 */
public class Panel_articController implements Initializable {

    @FXML
    private TextField txt_name_art;
    @FXML
    private TextField txt_descr_art;
    @FXML
    private TextField txt_stock_art;
    @FXML
    private TextField txt_minim_art;
    @FXML
    private Button bt_alta_art;
    @FXML
    private Button bt_update_art;
    @FXML
    private Button bt_delete_art;
    @FXML
    private Button bt_search_art;
    @FXML
    private TextField txt_id_art;
    @FXML
    private TextField txt_unid_drop_art;
    @FXML
    private Button bt_drop_art;
    @FXML
    private TextArea messages;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * Método para insertar en la base de datos objetos de la clase Articulo
     *
     * @param event Recoge el evento click vinculado botón bt_alta_art
     * 
     */
    @FXML
    private void alta_art(ActionEvent event) {
        GestionDB_Art gDbArt = new GestionDB_Art();
        Articulo art = new Articulo();
        Articulo artDB = gDbArt.buscarArtPorId(txt_id_art.getText());
        if(artDB != null){
            messages.setText("Ya existe un articulo con esa referencia,\n por vavor, utilice otra para dar de alta");
        }else{
            art.setIdArt(txt_id_art.getText());
            art.setNombreArt(txt_name_art.getText());
            art.setDescripcionArt(txt_descr_art.getText());
            art.setStock(Integer.parseInt(txt_stock_art.getText()));
            art.setMinimo(Integer.parseInt(txt_minim_art.getText()));
            int resp = gDbArt.insertarArticulo(art);

            if(resp == 1){
                messages.setText("");
                messages.setText("Articulo insertado corectamente");
                txt_id_art.setText("");
                txt_name_art.setText("");
                txt_descr_art.setText("");
                txt_stock_art.setText("");
                txt_minim_art.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido insertarse el articulo en la base de datos");
            }
        }
    }

    @FXML
    private void update_art(ActionEvent event) {
        GestionDB_Art gDbArt = new GestionDB_Art();
            messages.setText("");
            String id = txt_id_art.getText();
            Articulo art = new Articulo();
            art.setIdArt(id);
            art.setNombreArt(txt_name_art.getText());
            art.setDescripcionArt(txt_descr_art.getText()) ;
            art.setStock(Integer.parseInt(txt_stock_art.getText()));
            art.setMinimo(Integer.parseInt(txt_minim_art.getText()));
            int resp = gDbArt.modificarArtPorId(id, art);
            
            if(resp == 1){
                messages.setText("");
                messages.setText("Articulo modificado corectamente");
                txt_id_art.setText("");
                txt_name_art.setText("");
                txt_descr_art.setText("");
                txt_stock_art.setText("");
                txt_minim_art.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido modificarse el articulo");
            }
        
    }

    @FXML
    private void delete_art(ActionEvent event) {
        GestionDB_Art gDbArt = new GestionDB_Art();
        Articulo artDB = gDbArt.buscarArtPorId(txt_id_art.getText());
        if(artDB == null){
            messages.setText("No existe un articulo con esa referencia");
            txt_name_art.setText("");
            txt_descr_art.setText("");
            txt_stock_art.setText("");
            txt_minim_art.setText("");
            
        }else{
            messages.setText("");
            int resp = gDbArt.borrarArtPorId(txt_id_art.getText());
            GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
            gDbArtProv.borrarArtProvPorIdArt(txt_id_art.getText());
            if(resp == 1){
                messages.setText("");
                messages.setText("Articulo borrado corectamente");
                txt_id_art.setText("");
                txt_name_art.setText("");
                txt_descr_art.setText("");
                txt_stock_art.setText("");
                txt_minim_art.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido borrarse el articulo");
            }
        }
        
    }

    @FXML
    private void search_art(ActionEvent event) {
        GestionDB_Art gDbArt = new GestionDB_Art();
        Articulo art = gDbArt.buscarArtPorId(txt_id_art.getText());
        if(art == null){
            messages.setText("No existe un articulo con esa referencia");
            txt_name_art.setText("");
            txt_descr_art.setText("");
            txt_stock_art.setText("");
            txt_minim_art.setText("");
            
        }else{
            messages.setText("");
            txt_name_art.setText(art.getNombreArt());
            txt_descr_art.setText(art.getDescripcionArt());
            txt_stock_art.setText(Integer.toString(art.getStock()));
            txt_minim_art.setText(Integer.toString(art.getMinimo()));
        }
        
    }
    @FXML
    private void update_art_stock(ActionEvent event){
        GestionDB_Art gDbArt = new GestionDB_Art();
        String id = txt_id_art.getText();
        Articulo art = gDbArt.buscarArtPorId(id);
        if(art == null){
            messages.setText("No existe un articulo con esa referencia");
        }else{
            int cantidad = Integer.parseInt(txt_unid_drop_art.getText());
            int stock = art.getStock()+ cantidad;
            if (stock < 0){
                messages.setText("No existe stock suficiente del articulo");
            }else if(stock <= art.getMinimo()){
                GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
                ArticuloProveedor ArtProv = new ArticuloProveedor();
                ArtProv = gDbArtProv.compararPreciosPorIdArt(art.getIdArt());
                if (ArtProv == null){
                    messages.setText("No existe proveedor para ese articulo,\nes necesario dar de alta uno");
                }else{
                    int resp = gDbArt.modificarStock(id, cantidad);
                    if(resp == 1){
                        txt_unid_drop_art.setText("");
                        messages.setText("Se modifica el stock del articulo.\n");
                        alta_pedido(art, ArtProv);
                    }else{
                        messages.setText("No se ha podido modificar el stock del articulo");
                    }    
               }
            }else{
                int resp = gDbArt.modificarStock(id, cantidad);
                if(resp == 1){
                    txt_unid_drop_art.setText("");
                    messages.setText("Se modifica el stock del articulo");
                }else{
                    messages.setText("No se ha podido modificar el stock del articulo");
                } 
            }
        }
    }
    
    
    private void  alta_pedido(Articulo art, ArticuloProveedor ArtProv){
        Pedido ped = new Pedido();
        GestionDB_Prov gDbProv = new GestionDB_Prov();
        Proveedor prov = gDbProv.buscarProvPorId(ArtProv.getIdProv());
        ped.setIdArt(art.getIdArt());
        ped.setDescripcionArt(art.getDescripcionArt());
        ped.setIdProv(ArtProv.getIdProv());        
        ped.setNombreProv(prov.getNombre());
        ped.setCIFProv(prov.getCIF());
        ped.setPrecioUnidad(ArtProv.getPrecio());
        ped.setCantidad(art.getMinimo() *4);
        GestionDB_Ped gDbPed = new GestionDB_Ped();
        int resp = gDbPed.insertarPedido(ped);
        if(resp == 1){ 
            messages.appendText("El stock del articulo ha llegado al minimo preestablecido,"
                    + "\nse ordena el pedido correspondiente.");
        }else{
            messages.appendText("El stock del articulo ha llegado al minimo preestablecido,"
                    + "\npero no se ha podido ordenar el pedido correspondiente.");
        }
    }
}
