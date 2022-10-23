package com.argpro.argentinaprograma.services.SeccionService;


import com.argpro.argentinaprograma.models.SeccionModel.educacion.Educacioncard;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.stereotype.Service;

@Service
public class EducacioncardServiceImple extends BaseServiceImple<Educacioncard, Long> implements EducacioncardService {

    public EducacioncardServiceImple(BaseRepository<Educacioncard, Long> baseRepository) {
        super(baseRepository);
    }
}
