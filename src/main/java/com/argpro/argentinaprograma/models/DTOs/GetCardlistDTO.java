package com.argpro.argentinaprograma.models.DTOs;

public class GetCardlistDTO {




    private int id;
    private String titulo;
    private String subtitulo;
    private String imagencardString;

    /*
    @Override
    public String toString() {
        return "GetCardlistDTO{" +
                "titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", imagencardString='" + imagencardString + '\'' +
                '}';
    }

     */

    public GetCardlistDTO(int id, String titulo, String subtitulo, String imagencardString) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.imagencardString = imagencardString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getImagencardString() {
        return imagencardString;
    }

    public void setImagencardString(String imagencardString) {
        this.imagencardString = imagencardString;
    }
}
