/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen.modelos;

/**
 *
 * @author Patricia Defez Díaz de Sarralde
 */

public class Articulo {
    private String idArt;
    private String nombreArt;
    private String descripcionArt;
    private int stock;
    private int minimo;

    public Articulo() {
    }

    public Articulo(String idArt, String nombreArt, String descripcionArt, int stock, int minimo) {
        this.idArt = idArt;
        this.nombreArt = nombreArt;
        this.descripcionArt = descripcionArt;
        this.stock = stock;
        this.minimo = minimo;
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
     * @return the nombreArt
     */
    public String getNombreArt() {
        return nombreArt;
    }

    /**
     * @param nombreArt the nombreArt to set
     */
    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
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
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the minimo
     */
    public int getMinimo() {
        return minimo;
    }

    /**
     * @param minimo the minimo to set
     */
    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }
    
     @Override
    public String toString() {
        return "ID: " + getIdArt() + "\tNombre: " + getNombreArt() + "\tDescripción:" + getDescripcionArt() + "\tStock: " + getStock() + "\tMínimo: " + getMinimo();
    }

 

}
