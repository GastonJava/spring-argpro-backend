package com.argpro.argentinaprograma.models.SeccionModel.sobremi;

import com.argpro.argentinaprograma.models.config.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sobremi")
public class SobremiModel extends Base {

    /*
    @Column(name = "imagenportada")
    private String imagenportada;
     */

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "subtitulo")
    private String subtitulo;

    @Column(name = "portadatitulo")
    private String portadatitulo;

    @Lob
    @Column(name = "portada")
    private byte[] portada;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy="sobremiModel")
    //private List<ImagenModel> imagenes;
  // ---------------------- SETTERS GETTERS ---------------------------------

    public String getPortadatitulo() {
        return portadatitulo;
    }

    public void setPortadatitulo(String portadatitulo) {
        this.portadatitulo = portadatitulo;
    }

    public byte[] getPortada() {
        return portada;
    }

    public void setPortada(byte[] portada) {
        this.portada = portada;
    }

    /*
    public String getImagenportada() {
        return imagenportada;
    }
    public void setImagenportada(String imagenportada) {
        this.imagenportada = imagenportada;
    }
    */

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    //public List<ImagenModel> getImagenes() { return imagenes; }
    //public void setImagenes(List<ImagenModel> imagenes) { this.imagenes = imagenes;}

}
