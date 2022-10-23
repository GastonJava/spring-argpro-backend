
/*
package com.argpro.portfolio.models.GestionUsuarioModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.argpro.portfolio.models.config.Base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "privilegio")
public class PrivilegioModel extends Base{
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "privilegios")
    private List<RolModel> roles = new ArrayList<RolModel>();
}
*/