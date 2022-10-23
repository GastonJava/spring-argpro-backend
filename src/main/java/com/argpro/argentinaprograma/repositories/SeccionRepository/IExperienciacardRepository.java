package com.argpro.argentinaprograma.repositories.SeccionRepository;

import com.argpro.argentinaprograma.models.SeccionModel.experiencia.Experienciacard;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExperienciacardRepository extends BaseRepository<Experienciacard, Long> {
    @Query("select imagencard from Experienciacard")
    public byte[] findByImagencard();


}
