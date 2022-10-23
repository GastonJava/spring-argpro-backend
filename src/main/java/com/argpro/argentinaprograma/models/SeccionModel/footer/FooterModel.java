package com.argpro.argentinaprograma.models.SeccionModel.footer;

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
@Table(name = "footer")
public class FooterModel extends Base {
    
    @Column(name = "titulo")
    private String titulo;
}
