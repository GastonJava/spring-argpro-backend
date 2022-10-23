package com.argpro.argentinaprograma.models.SeccionModel.educacion;

import com.argpro.argentinaprograma.models.config.Base;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "educacion")
public class EducacionModel extends Base {

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "titulofooter")
    private String titulofooter;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulofooter() {
        return titulofooter;
    }

    public void setTitulofooter(String titulofooter) {
        this.titulofooter = titulofooter;
    }

}
