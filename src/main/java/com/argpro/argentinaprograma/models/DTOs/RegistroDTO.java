package com.argpro.argentinaprograma.models.DTOs;

public class RegistroDTO {

    private String nombre;
    //private String apellido;
    private String email;
    private String password;
    public Integer isadmin;
    public byte[] imagenbyte;
    public String tituloimg;

    //setters and getter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsadmin() {
        return isadmin;
    }
    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public byte[] getImagenbyte() { return imagenbyte;}
    public void setImagenbyte(byte[] imagenbyte) {this.imagenbyte = imagenbyte;}

    public String getTituloimg() { return tituloimg; }
    public void setTituloimg(String tituloimg) { this.tituloimg = tituloimg; }

    public RegistroDTO() {
        super();
    }

    /*
    public String getApellido() {
        return apellido;
    }
    */

    /*
    public void setUsername(String apellido) {
        this.apellido = apellido;
    }
    */
}
