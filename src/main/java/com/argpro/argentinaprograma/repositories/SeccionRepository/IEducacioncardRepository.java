package com.argpro.argentinaprograma.repositories.SeccionRepository;

import com.argpro.argentinaprograma.models.SeccionModel.educacion.Educacioncard;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacioncardRepository extends BaseRepository<Educacioncard, Long> {

    //@Modifying
    //@Query("update educacioncard edu set edu.titulocard = :titulocard where id = :id")
    //public void UpdateTitulocardById (Integer id, String titulocard);
}
