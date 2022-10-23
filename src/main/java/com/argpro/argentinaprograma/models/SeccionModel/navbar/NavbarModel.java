package com.argpro.argentinaprograma.models.SeccionModel.navbar;

//import com.argpro.portfolio.models.SeccionModel.Persona.Persona;


import com.argpro.argentinaprograma.models.config.Base;

import javax.persistence.*;

@Entity
@Table(name = "navbar")
public class NavbarModel extends Base {

    @Column(name= "nombre")
    private String nombrepersonanavbar;

    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagenbyte;

    @Column(name = "imagen_titulo")
    private String imagentitulo;

/*
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona persona;




    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
*/
    //getters and setters

    public String getNombrepersonanavbar() {
        return nombrepersonanavbar;
    }

    public void setNombrepersonanavbar(String nombrepersonanavbar) {
        this.nombrepersonanavbar = nombrepersonanavbar;
    }

    public byte[] getImagenbyte() {
        return imagenbyte;
    }

    public void setImagenbyte(byte[] imagenbyte) {
        this.imagenbyte = imagenbyte;
    }

    public String getImagentitulo() {
        return imagentitulo;
    }

    public void setImagentitulo(String imagentitulo) {
        this.imagentitulo = imagentitulo;
    }




}
