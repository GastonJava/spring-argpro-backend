package com.argpro.argentinaprograma.models;

import java.sql.Date;

import javax.persistence.*;

import com.argpro.argentinaprograma.models.config.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagen")
public class ImagenModel extends Base {


 public ImagenModel() {
     super();
 }

 /*
    public ImagenModel(String nombre, String type, byte[] imgbyte) {
        this.nombre = nombre;
        //this.carpeta = carpeta;
        //this.date = date;
        this.type = type;
        this.imgbyte = imgbyte;
    }

  */

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "carpeta")
    public String carpeta;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha")
    public Date date;

    @Column(name = "type")
    private String type;

    @Column(name = "imgbyte", length = 1000)
    private byte[] imgbyte;

    /*
    @ManyToOne(fetch = FetchType.LAZY) // optional = false
    @JoinColumn(name = "sobremimodel_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SobremiModel sobremiModel;
    */

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCarpeta() { return carpeta; }

    public void setCarpeta(String carpeta) { this.carpeta = carpeta; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public byte[] getImgbyte() { return imgbyte; }

    public void setImgbyte(byte[] imgbyte) { this.imgbyte = imgbyte; }

    //public SobremiModel getSobremiModel() { return sobremiModel; }

    //public void setSobremiModel(SobremiModel sobremiModel) { this.sobremiModel = sobremiModel;}


}

