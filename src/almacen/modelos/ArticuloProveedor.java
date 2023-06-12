/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

/**
 *
 * @author ptddd
 */
public class ArticuloProveedor {
    private String idArt;
    private String idProv;
    private float precio;

    public ArticuloProveedor() {
    }

    public ArticuloProveedor(String idArt, String idProv, float precio) {
        this.idArt = idArt;
        this.idProv = idProv;
        this.precio = precio;
    }
    
    

    /**
     * @return the idArt
     */
    public String getIdArt() {
        return idArt;
    }

    /**
     * @param idArt the idArt to set
     */
    public void setIdArt(String idArt) {
        this.idArt = idArt;
    }

    /**
     * @return the idProv
     */
    public String getIdProv() {
        return idProv;
    }

    /**
     * @param idProv the idProv to set
     */
    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
