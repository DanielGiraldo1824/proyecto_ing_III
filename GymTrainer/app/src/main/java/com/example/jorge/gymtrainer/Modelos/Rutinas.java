package com.example.jorge.gymtrainer.Modelos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NIGGA on 3/11/2017.
 */

public class Rutinas implements Serializable{
    private String ID;
    private String Nombre;
    private String Descripcion;
    private String TipoRutina;
    private Date Created_at;
    private Date Updated_at;
    private Date Deleted_at;

    public Rutinas(){}

    public Rutinas(String ID, String nombre, String descripcion, String tipoRutina, Date created_at, Date updated_at, Date deleted_at) {
        this.ID = ID;
        Nombre = nombre;
        Descripcion = descripcion;
        TipoRutina = tipoRutina;
        Created_at = created_at;
        Updated_at = updated_at;
        Deleted_at = deleted_at;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTipoRutina() {
        return TipoRutina;
    }

    public void setTipoRutina(String tipoRutina) {
        TipoRutina = tipoRutina;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date created_at) {
        Created_at = created_at;
    }

    public Date getUpdated_at() {
        return Updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        Updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return Deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        Deleted_at = deleted_at;
    }
}
