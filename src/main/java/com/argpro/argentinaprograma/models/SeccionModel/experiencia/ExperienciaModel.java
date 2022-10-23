package com.argpro.argentinaprograma.models.SeccionModel.experiencia;

import com.argpro.argentinaprograma.models.config.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "experiencia")
public class ExperienciaModel extends Base {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private Long id;
     */

    @Column(name = "titulo")
    private String tituloexp;

    //@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY ,orphanRemoval = true)
    //@JoinColumn(name = "fk_experiencia_id", referencedColumnName = "id")

    //@CollectionTable(name = "experiencia_experienciacard", joinColumns = @JoinColumn(name = "experiencia_id"))
    //@ElementCollection

    //@ManyToOne()
    //private Set<ExperienciacardServiceImple> experienciacards;

    /*
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_experiencia_id")
    private ExperienciacardServiceImple experienciacard;
    */

    @OneToMany(targetEntity = Experienciacard.class, cascade= CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "experiencia_id_fk")
    private Set<Experienciacard> experienciacards;

}
