package com.argpro.argentinaprograma.repositories.SeccionRepository;

import com.argpro.argentinaprograma.models.SeccionModel.educacion.EducacionModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository  extends BaseRepository<EducacionModel, Long> {
}
