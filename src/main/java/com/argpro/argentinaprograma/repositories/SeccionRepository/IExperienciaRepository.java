package com.argpro.argentinaprograma.repositories.SeccionRepository;

import com.argpro.argentinaprograma.models.SeccionModel.experiencia.ExperienciaModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends BaseRepository<ExperienciaModel, Long> {

}
