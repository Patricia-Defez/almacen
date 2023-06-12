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
public class Proveedor {
    private String idProv;
    private String nombre;
    private String telefono;
    private String CIF;

    public Proveedor() {
    }

    public Proveedor(String idProv, String nombre, String telefono, String CIF) {
        this.idProv = idProv;
        this.nombre = nombre;
        this.telefono = telefono;
        this.CIF = CIF;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the CIF
     */
    public String getCIF() {
        return CIF;
    }

    /**
     * @param CIF the CIF to set
     */
    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    @Override
    public String toString() {
        return "idProv:" + idProv + "\tnombre: " + nombre + "\ttelefono: " + telefono + "\tCIF: " + CIF;
    }
    
    
}
