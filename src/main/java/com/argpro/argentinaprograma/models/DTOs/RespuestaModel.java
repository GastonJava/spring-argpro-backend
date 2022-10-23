package com.argpro.argentinaprograma.models.DTOs;

public class RespuestaModel {

    private String mensaje;
    private boolean borrado;

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public RespuestaModel(String mensaje, boolean borrado) {
        this.mensaje = mensaje;
        this.borrado = borrado;
    }

}
