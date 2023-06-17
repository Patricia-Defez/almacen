/*
 * Modulo: PROYECTO DAM ABRIL
 * Proyecto: Almacen
 * Archivo: InterfazController.java
 * Objetivo: Realizar una aplicación para la gestión de Articulos y Proveedores de un almacén.
 */
package almacen;

import almacen.modelos.Articulo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import almacen.modelos.GestionDB_Art;
import almacen.modelos.GestionDB_Ped;
import almacen.modelos.GestionDB_Prov;
import almacen.modelos.Pedido;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.fxutils.viewer.JasperViewerFX;

/**
 * FXML Controller class
 * Controlador encargado de la gestión de la ventana principal
 * 
 * @author Patricia Defez Díaz de Sarralde
 * 
 * @version 1.0.0
 */
public class InterfazController implements Initializable {
    
    /**
     * Botón utilizado para cargar el panel de Gestión de Articulos
     */
    @FXML
    private Button bt_cargar_panel_art;
    
    /**
     * Botón utilizado para cargar el panel de Gestión de Proveedores
     */
    @FXML
    private Button bt_cargar_panel_prov;
    
    /**
     * Botón utilizado para la generación del informe de artículos
     */
    @FXML
    private Button bt_informe_art;
    
    /**
     * Botón utilizado para la generación del informe de proveedores
     */
    @FXML
    private Button bt_informe_ped;
    
    /**
     * Botón utilizado para cerrar la aplicación
     */
    @FXML
    private Button bt_salir;
    
    /**
     * Contenedor usado para cargar los paneles
     */
    @FXML
    private VBox contenido;
    
    /**
     * Botón utilizado para listar todos los artículos de la base de datos
     */
    @FXML
    private Button bt_listar_art;
    
    /**
     * Botón utilizado para listar todos los proveedores de la base de datos
     */
    @FXML
    private Button bt_listar_prov;
    
    /**
     * Botón utilizado para listar todos los pedidos de la base de datos
     */
    @FXML
    private Button bt_listar_ped;
    
    /**
     * Area de texto que mostrará los listados
     */
    @FXML
    private TextArea txt_Area;
    
     

    /**
     * Método de inicialización del controlador
     * 
     * @param url Ubicación utilizada para resolver rutas relativas para el objeto raíz,
     * o null si no se conoce la ubicación.
     * 
     * @param rb Recursos utillizados para localizar el objeto raíz, o null si el objeto 
     * raíz no se localizó.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //La aplicación se inicializa con el panel de gestión
        cargarPanel("panel_artic.fxml");

    }  
    
    /**
     * Método para cargar el panel de Gestión de Articulos
     * Arroja una excepción de tipo Exception si no se no se accedan al ficheros necesario para ello
     *
     * @param event Recoge el evento click vinculado botón bt_cargar_panel_art
     * 
     */
    @FXML
    private void cargar_panel_art(ActionEvent event) throws Exception{
        cargarPanel("panel_artic.fxml");
    }
    
    /**
     * Método para cargar el panel de Gestión de Proveedores
     * Arroja una excepción de tipo Exception si no se no se accedan al ficheros necesario para ello
     *
     * @param event Recoge el evento click vinculado botón bt_cargar_panel_prov
     * 
     */
    @FXML
    private void cargar_panel_prov(ActionEvent event) throws Exception{
        cargarPanel("panel_prove.fxml");
    }
    
    /**
     * Método para crear el informe de los articulos en almacén
     * Maneja la excepción de tipo Exception si no se crea correctamente el informe
     * 
     * @param event Recoge el evento click vinculado al botón bt_matric_DI
     * 
     */
    @FXML
    private void crearInformeArt(ActionEvent event){
        //Definimos la coleccion de datos sobre la que se creará el informe
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        GestionDB_Art gDbArt = new GestionDB_Art();
        articulos = gDbArt.crearArrayArticulos();
        if (articulos != null){
            try {
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(articulos);
                if (beanColDataSource == null){
                    txt_Area.setText("Los datos obtenidos son nulos");
                }
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                if (stage == null){
                    txt_Area.setText("No ha podido conseguirse la ventana");
                }
                //Declaramos nuestro visor de informes
                JasperViewerFX viewerfx;
                viewerfx = new JasperViewerFX(stage, "Stock de Articulos", "/informes/gbarrasArt.jasper",
                        new HashMap(), beanColDataSource);

                //Mostramos el informe en el visor
                viewerfx.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                txt_Area.setText("No ha podido abrirse el visor de informes");
            }   
            
        }else{
                txt_Area.setText("No han podido conseguirse datos de la base de datos");
        }

    }
            
    @FXML
    private void crearInformePed(ActionEvent event){
        //Definimos la coleccion de datos sobre la que se creará el informe
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        GestionDB_Ped gDbPed = new GestionDB_Ped();
        pedidos = gDbPed.crearArrayPedidos();
        System.out.println(pedidos.size()+ " pedidos creados correctamente");
        //Collections.sort(pedidos);
        if (pedidos != null){
            try {
                JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(pedidos);
                if (beanDataSource == null){
                    txt_Area.setText("Los datos obtenidos son nulos");
                }
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                if (stage == null){
                    txt_Area.setText("No ha podido conseguirse la ventana");
                }
                //Declaramos nuestro visor de informes
                JasperViewerFX viewerfx;
                viewerfx = new JasperViewerFX(stage, "Informe de Pedidos", "/informes/infPedidos.jasper",
                        new HashMap(), beanDataSource);

                //Mostramos el informe en el visor
                viewerfx.show();
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                txt_Area.setText("No ha podido abrirse el visor de informes");   
            }   
              
        }else{
                txt_Area.setText("No han podido conseguirse datos de la base de datos");
        }
    }
             

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void listar_art(ActionEvent event) {
        GestionDB_Art gDbArt = new GestionDB_Art();
        String articulos = gDbArt.listarArticulos();
        
        if (articulos.length()== 0){
            txt_Area.setText("");
            txt_Area.setText("No hay articulos en la base de datos");
        }else{
            txt_Area.setText("");
            txt_Area.setText(articulos);
        }
        
        
    }

    @FXML
    private void listar_prov(ActionEvent event) {
        GestionDB_Prov gDbProv = new GestionDB_Prov();
        String proveedores = gDbProv.listarProveedores();
        
        if (proveedores.length()== 0){
            txt_Area.setText("");
            txt_Area.setText("No hay proveedores en la base de datos");
        }else{
            txt_Area.setText("");
            txt_Area.setText(proveedores);
        }
        
    }

    @FXML
    private void listar_ped(ActionEvent event) {
        GestionDB_Ped gDBPed = new GestionDB_Ped();
        String pedidos = gDBPed.listarPedidos();
        
        if (pedidos.length()== 0){
            txt_Area.setText("");
            txt_Area.setText("No hay pedidos en la base de datos");
        }else{
            txt_Area.setText("");
            txt_Area.setText(pedidos);
        }
    }
    
    /**
     * Método para cargar un panel en el contenedor contenido
     * Maneja la excepción de tipo Exception si no se realiza correctamente la tarea
     * 
     * @param panel String correspondiente al nombre del fichero del panel cargado
     * 
     */
    private void cargarPanel(String panel){
        //Se elimina el panel anterior
        contenido.getChildren().clear();
        
        URL url = getClass().getResource("vistas/"+panel);
        
        try {
            Node nodo = FXMLLoader.load(url);
            //Se carga el nuevo panel
            contenido.getChildren().add(nodo);
            System.out.println("Cargando el panel"+panel);
        }
        catch(Exception ex){
            System.err.println("No se ha cargado el panel: "+ panel+"\n"+ex.toString());
        }
    
    }
}
