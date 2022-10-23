package com.argpro.argentinaprograma.services.SeccionService;


import com.argpro.argentinaprograma.models.SeccionModel.experiencia.Experienciacard;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.stereotype.Service;

@Service
public class ExperienciacardServiceImple extends BaseServiceImple<Experienciacard, Long> implements ExperienciacardService {
    public ExperienciacardServiceImple(BaseRepository<Experienciacard, Long> baseRepository) { super(baseRepository); }
}
