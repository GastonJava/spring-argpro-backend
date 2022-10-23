package com.argpro.argentinaprograma.models.GestionUsuarioModel;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;


import com.argpro.argentinaprograma.models.config.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rol")
public class RolModel extends Base { //implements Serializable, GenericEntity<RolModel>

    @Column(name = "nombrerol")
    private String rolnombre;

    //@Column(name = "nombre")
    //private String nombre;

    /*
    @Column(name = "nombrerol")
    private String nombrerol;

    @ManyToMany(mappedBy = "roles")
    private List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();


    @ManyToMany
    @JoinTable(
        name = "roles_privilegios", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilegio_id", referencedColumnName = "id")
    )
    private List<PrivilegioModel> privilegios = new ArrayList<PrivilegioModel>();


    //permisos y rol - es muchos a muchos
    /*
    @ManyToMany
    @JoinTable(
        name = "roles_permisos",
        joinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"
        ),

        inverseJoinColumns = @JoinColumn(
            name = "permiso_id", referencedColumnName = "id"
        )
    )
    private List<PermisoModel> permisos = new ArrayList<PermisoModel>();
    */

   
}