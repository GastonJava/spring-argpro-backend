package com.argpro.argentinaprograma.models.SeccionModel.proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.argpro.argentinaprograma.models.config.Base;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "proyecto")
public class ProyectoModel extends Base {
    
    @Column(name = "portada")
    private String imagen;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "subtitulo")
    private String subtitulo;

    //coleccion de elementos para los TAGS
    @ElementCollection
    @CollectionTable(name = "proyectotag",
        joinColumns = @JoinColumn(name = "proyecto_id"))
    @Column(name = "nombre")
    private List<String> proyectotags = new ArrayList<String>();

}
