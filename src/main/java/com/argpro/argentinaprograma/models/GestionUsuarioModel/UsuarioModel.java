package com.argpro.argentinaprograma.models.GestionUsuarioModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.argpro.argentinaprograma.models.ImagenModel;
import com.argpro.argentinaprograma.models.config.Base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuario")
public class UsuarioModel extends Base {// extends Serializable

    @Column(name = "nombre")
    private String nombre;

    /*
    @Column(name = "apellido")
    private String apellido;
    */

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private boolean tokenExpired;

    @Column(name = "esadmin")
    private Integer isadmin;

    @Column(name = "tituloimg")
    private String tituloimg;

    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] imagenbyte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_imagen")
    public ImagenModel imagen;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "rol_id", referencedColumnName = "id")
    )
    private List<RolModel> roles = new ArrayList<>();

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    public List<RolModel> roles = new ArrayList<RolModel>();
     */
}