package com.example.jorge.gymtrainer.Modelos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NIGGA on 3/11/2017.
 */

public class Registro implements Serializable {

    private String ID;
    private String Rutina_ID;
    private String Alimentacion_ID;
    private String Usuario_ID;
    private String GrasaCorporal;
    private String RitmoCardiaco;
    private String Peso;
    private String Altura;
    private String Created_at;
    private String Updated_at;
    private String Deleted_at;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRutina_ID() {
        return Rutina_ID;
    }

    public void setRutina_ID(String rutina_ID) {
        Rutina_ID = rutina_ID;
    }

    public String getAlimentacion_ID() {
        return Alimentacion_ID;
    }

    public void setAlimentacion_ID(String alimentacion_ID) {
        Alimentacion_ID = alimentacion_ID;
    }

    public String getUsuario_ID() {
        return Usuario_ID;
    }

    public void setUsuario_ID(String usuario_ID) {
        Usuario_ID = usuario_ID;
    }

    public String getGrasaCorporal() {
        return GrasaCorporal;
    }

    public void setGrasaCorporal(String grasaCorporal) {
        GrasaCorporal = grasaCorporal;
    }

    public String getRitmoCardiaco() {
        return RitmoCardiaco;
    }

    public void setRitmoCardiaco(String ritmoCardiaco) {
        RitmoCardiaco = ritmoCardiaco;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(String created_at) {
        Created_at = created_at;
    }

    public String getUpdated_at() {
        return Updated_at;
    }

    public void setUpdated_at(String updated_at) {
        Updated_at = updated_at;
    }

    public String getDeleted_at() {
        return Deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        Deleted_at = deleted_at;
    }
}
