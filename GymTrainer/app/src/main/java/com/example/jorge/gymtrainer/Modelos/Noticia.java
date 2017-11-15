package com.example.jorge.gymtrainer.Modelos;

import java.util.Date;

/**
 * Created by NIGGA on 6/11/2017.
 */

public class Noticia {
    private int ID;
    private String Nombre;
    private String Descripcion;
    private Date Created_at;
    private Date Updated_at;
    private Date Deleted_at;

    public Noticia(int ID, String nombre, String descripcion, Date created_at, Date updated_at, Date deleted_at) {
        this.ID = ID;
        Nombre = nombre;
        Descripcion = descripcion;
        Created_at = created_at;
        Updated_at = updated_at;
        Deleted_at = deleted_at;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
