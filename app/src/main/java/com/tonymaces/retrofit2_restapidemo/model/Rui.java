package com.tonymaces.retrofit2_restapidemo.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by tonym on 27/06/2016.
 */
public class Rui implements Serializable {

    @Expose
    private String IdRuiMaster;

    @Expose
    private String Nominativo;

    @Expose
    private String NumeroIscrizione;

    @Expose
    private String Sezione;

    @Expose
    private String Status;

    @Expose
    private String TipoPersona;

    public String getIdRuiMaster() {
        return IdRuiMaster;
    }

    public void setIdRuiMaster(String idRuiMaster) {
        IdRuiMaster = idRuiMaster;
    }

    public String getNominativo() {
        return Nominativo;
    }

    public void setNominativo(String nominativo) {
        Nominativo = nominativo;
    }

    public String getNumeroIscrizione() {
        return NumeroIscrizione;
    }

    public void setNumeroIscrizione(String numeroIscrizione) {
        NumeroIscrizione = numeroIscrizione;
    }

    public String getSezione() {
        return Sezione;
    }

    public void setSezione(String sezione) {
        Sezione = sezione;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTipoPersona() {
        return TipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        TipoPersona = tipoPersona;
    }

}
