package com.example.jorge.gymtrainer.Modelos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NIGGA on 3/11/2017.
 */

public class Usuario implements Serializable{
    private String ID;
    private String Cedula;
    private String Nombres;
    private String Apellidos;
    private String CorreoElectronico;
    private String Contraseña;
    private String RememberToken;
    private String TipoUsuario;
    private Date Created_at;
    private Date Updated_at;
    private Date Deleted_at;

    public Usuario(){

    }

    public Usuario(String ID, String cedula, String nombres, String apellidos, String correoElectronico, String contraseña, String rememberToken,String TipoUsuario ,Date created_at, Date updated_at, Date deleted_at) {
        this.ID = ID;
        Cedula = cedula;
        Nombres = nombres;
        Apellidos = apellidos;
        CorreoElectronico = correoElectronico;
        Contraseña = contraseña;
        RememberToken = rememberToken;
        this.TipoUsuario = TipoUsuario;
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

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getRememberToken() {
        return RememberToken;
    }

    public void setRememberToken(String rememberToken) {
        RememberToken = rememberToken;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        TipoUsuario = tipoUsuario;
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
