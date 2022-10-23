package com.argpro.argentinaprograma.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "admin")
public class AdminModel extends Base {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "codigoadmin")
    public int codigoadmin;
}