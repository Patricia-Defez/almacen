/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.controladores;

import almacen.modelos.Articulo;
import almacen.modelos.ArticuloProveedor;
import almacen.modelos.GestionDB_Art;
import almacen.modelos.GestionDB_ArtProv;
import almacen.modelos.GestionDB_Prov;
import almacen.modelos.Proveedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ptddd
 */
public class Panel_proveController implements Initializable {

    @FXML
    private AnchorPane txt_nombre_prov;
    @FXML
    private TextField txt_name_prov;
    @FXML
    private TextField txt_telef_prov;
    @FXML
    private TextField txt_CIF_prov;
    @FXML
    private Button bt_alta_prov;
    @FXML
    private Button bt_update_prov;
    @FXML
    private Button bt_delete_prov;
    @FXML
    private Button bt_search_prov;
    @FXML
    private TextField txt_id_Art;
    @FXML
    private TextField txt_price_art;
    @FXML
    private Button bt_alta_art_prov;
    @FXML
    private Button bt_update_art_prov;
    @FXML
    private Button bt_delete_art_prov;
    @FXML
    private Button bt_search_art_prov;
    @FXML
    private TextField txt_id_prov;
    @FXML
    private TextArea messages;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void alta_prov(ActionEvent event) {
        GestionDB_Prov gDbProv = new GestionDB_Prov();
        Proveedor prov = new Proveedor();
        Proveedor provDB = gDbProv.buscarProvPorId(txt_id_prov.getText());
        if(provDB != null){
            messages.setText("Ya existe un proveedor con esa referencia,\n por favor, utilice otra para dar de alta");
        }else{
            prov.setIdProv(txt_id_prov.getText());
            prov.setNombre(txt_name_prov.getText());
            prov.setTelefono(txt_telef_prov.getText());
            prov.setCIF(txt_CIF_prov.getText());            
            int resp = gDbProv.insertarProveedor(prov);

            if(resp == 1){
                messages.setText("");
                messages.setText("Proveedor insertado corectamente");
                txt_id_prov.setText("");
                txt_name_prov.setText("");
                txt_telef_prov.setText("");
                txt_CIF_prov.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido insertarse el proveedor en la base de datos");
            }
        }    
    }

    @FXML
    private void update_prov(ActionEvent event) {
        GestionDB_Prov gDbProv = new GestionDB_Prov();
            messages.setText("");
            String id = txt_id_prov.getText();
            Proveedor prov = new Proveedor();
            prov.setIdProv(id);
            prov.setNombre(txt_name_prov.getText());
            prov.setTelefono(txt_telef_prov.getText()) ;
            prov.setCIF(txt_CIF_prov.getText());
            int resp = gDbProv.modificarProvPorId(id, prov);
            
            if(resp == 1){
                messages.setText("");
                messages.setText("Proveedor modificado corectamente");
                txt_id_prov.setText("");
                txt_name_prov.setText("");
                txt_telef_prov.setText("");
                txt_CIF_prov.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido modificarse el proveedor");
            }
    }

    @FXML
    private void delete_prov(ActionEvent event) {
        GestionDB_Prov gDbProv = new GestionDB_Prov();
        Proveedor provDB = gDbProv.buscarProvPorId(txt_id_prov.getText());
        if(provDB == null){
            messages.setText("No existe un proveedor con esa referencia");
            txt_name_prov.setText("");
            txt_telef_prov.setText("");
            txt_CIF_prov.setText("");
            
        }else{
            messages.setText("");
            int resp = gDbProv.borrarProvPorId(txt_id_prov.getText());
            GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
            gDbArtProv.borrarArtProvPorIdProv(txt_id_prov.getText());
            if(resp == 1){
                messages.setText("");
                messages.setText("Proveedor borrado corectamente");
                txt_id_prov.setText("");
                txt_name_prov.setText("");
                txt_telef_prov.setText("");
                txt_CIF_prov.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido borrarse el proveedor");
            }
        }
    }

    @FXML
    private void search_prov(ActionEvent event) {
         GestionDB_Prov gDbProv = new GestionDB_Prov();
        Proveedor prov = gDbProv.buscarProvPorId(txt_id_prov.getText());
        if(prov == null){
            messages.setText("No existe un proveedor con esa referencia");
            txt_name_prov.setText("");
            txt_telef_prov.setText("");
            txt_CIF_prov.setText("");
            
        }else{
            messages.setText("");
            txt_name_prov.setText(prov.getNombre());
            txt_telef_prov.setText(prov.getTelefono());
            txt_CIF_prov.setText(prov.getCIF());
        }
    }

    @FXML
    private void alta_art_prov(ActionEvent event) {
        GestionDB_Prov gDbProv = new GestionDB_Prov();
        GestionDB_Art gDbArt = new GestionDB_Art();
        Articulo art = gDbArt.buscarArtPorId(txt_id_Art.getText());
        Proveedor prov = gDbProv.buscarProvPorId(txt_id_prov.getText());
        if(art == null || prov == null){
            messages.setText("Compruebe que las referencias son correctas");
        }else{
            GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
            ArticuloProveedor ArtProv = new ArticuloProveedor();
            ArticuloProveedor ArtProvDB = gDbArtProv.buscarArtProvPorIds(txt_id_prov.getText(),txt_id_Art.getText());
            if(ArtProvDB != null){
                messages.setText("Ya existe un precio articulo con esa referencia para el proveedor seleccionado");            
            }else{
                ArtProv.setIdArt(txt_id_Art.getText());
                ArtProv.setIdProv(txt_id_prov.getText());
                ArtProv.setPrecio(Float.valueOf(txt_price_art.getText()));
                int resp = gDbArtProv.insertarArtProv(ArtProv);

                if(resp == 1){
                    messages.setText("");
                    messages.setText("Precio articulo insertado corectamente");
                    txt_id_Art.setText("");
                    txt_price_art.setText("");
                }else{
                    messages.setText("");
                    messages.setText("No ha podido insertarse el precio articulo en la base de datos");
                }
            }
        }    
    }

    @FXML
    private void update_art_prov(ActionEvent event) {
        GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
             messages.setText("");
            String idArt = txt_id_Art.getText();
            String idProv = txt_id_prov.getText();
            ArticuloProveedor ArtProv = new ArticuloProveedor();
            ArtProv.setIdArt(idArt);
            ArtProv.setIdProv(idProv);
            ArtProv.setPrecio(Float.valueOf(txt_price_art.getText()));
            int resp = gDbArtProv.modificarArtProvPorIds(idProv, idArt, ArtProv);
            
            if(resp == 1){
                messages.setText("");
                messages.setText("Precio articulo modificado corectamente");
                txt_id_Art.setText("");
                txt_price_art.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido modificarse el precio articulo");
            }
    }

    @FXML
    private void delete_art_prov(ActionEvent event) {
        GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
        ArticuloProveedor ArtProvDB = gDbArtProv.buscarArtProvPorIds(txt_id_prov.getText(),txt_id_Art.getText());
        if(ArtProvDB == null){
            messages.setText("No existe un precio articulo con esa referencia \npara el proveedor seleccionado");
            txt_price_art.setText("");            
        }else{
            messages.setText("");
            int resp = gDbArtProv.borrarArtProvPorIds(txt_id_prov.getText(), txt_id_Art.getText());
            if(resp == 1){
                messages.setText("");
                messages.setText("Precio articulo borrado corectamente");
                txt_id_Art.setText("");
                txt_price_art.setText("");
            }else{
                messages.setText("");
                messages.setText("No ha podido borrarse el precio articulo");
            }
        }
    }

    @FXML
    private void search_art_prov(ActionEvent event) {
        GestionDB_ArtProv gDbArtProv = new GestionDB_ArtProv();
        ArticuloProveedor ArtProvDB = gDbArtProv.buscarArtProvPorIds(txt_id_prov.getText(),txt_id_Art.getText());
        if(ArtProvDB == null){
            messages.setText("No existe un precio articulo con esa referencia para el proveedor seleccionado");
            txt_price_art.setText("");             
        }else{
            messages.setText("");
            txt_id_Art.setText(ArtProvDB.getIdArt());
            txt_price_art.setText(Float.toString(ArtProvDB.getPrecio()));
        }
    }
    
}
