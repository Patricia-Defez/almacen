/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ptddd
 */
public class Pedido implements Comparable<Pedido> {
    private String idArt;
    private String descripcionArt;
    private String idProv;
    private String nombreProv;
    private String CIFProv;
    private float precioUnidad;
    private int cantidad;
    private String fecha; 

    public Pedido() {
        Date myDate = new Date();
        this.fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
    }

    public Pedido(String idArt, String descripcionArt, String idProv, String nombreProv, String CIFProv, float precioUnidad, int cantidad ) {
        this.idArt = idArt;
        this.descripcionArt = descripcionArt;
        this.idProv = idProv;
        this.nombreProv = nombreProv;
        this.CIFProv = CIFProv;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        Date myDate = new Date();
        this.fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
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
     * @return the descripcionArt
     */
    public String getDescripcionArt() {
        return descripcionArt;
    }

    /**
     * @param descripcionArt the descripcionArt to set
     */
    public void setDescripcionArt(String descripcionArt) {
        this.descripcionArt = descripcionArt;
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
     * @return the nombreProv
     */
    public String getNombreProv() {
        return nombreProv;
    }

    /**
     * @param nombreProv the nombreProv to set
     */
    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    /**
     * @return the CIFProv
     */
    public String getCIFProv() {
        return CIFProv;
    }

    /**
     * @param CIFProv the CIFProv to set
     */
    public void setCIFProv(String CIFProv) {
        this.CIFProv = CIFProv;
    }

    /**
     * @return the precioUnidad
     */
    public float getPrecioUnidad() {
        return precioUnidad;
    }

    /**
     * @param cantidad the precioUnidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
     /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param precioUnidad the precioUnidad to set
     */
    public void setPrecioUnidad(float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return  "id Art: " + idArt + "\tdescripcion Art: " + descripcionArt + "\tid Prov: " + idProv + "\tnombre Prov: " + nombreProv + "\tCIF Prov: " + CIFProv + "\tprecio unidad: " + precioUnidad + "\tfecha: " + fecha;
    }

    @Override
    public int compareTo(Pedido ped) {
        return CIFProv.compareTo(ped.getCIFProv()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
