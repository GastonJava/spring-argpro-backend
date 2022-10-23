package com.argpro.argentinaprograma.models.SeccionModel.experiencia;

import com.argpro.argentinaprograma.models.config.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Embeddable
@Table(name = "experienciacard")
public class Experienciacard extends Base {

    @Column(name = "titulocard")
    private String titulocard;

    @Column(name = "subtitulocard")
    private String subtitulocard;

    @Lob
    @Column(name = "imagencard", columnDefinition = "LONGBLOB")
    private byte[] imagencard;

    /*
    @OneToMany(mappedBy = "experienciacard", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExperienciacardServiceImple> experienciacards;


     */


    /*

    public String getTitulocard() {
        return titulocard;
    }

    public void setTitulocard(String titulocard) {
        this.titulocard = titulocard;
    }

    public String getSubtitulocard() {
        return subtitulocard;
    }

    public void setSubtitulocard(String subtitulocard) {
        this.subtitulocard = subtitulocard;
    }

    public byte[] getImagencard() {
        return imagencard;
    }

    public void setImagencard(byte[] imagencard) {
        this.imagencard = imagencard;
    }

     */
}
