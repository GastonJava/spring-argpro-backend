package com.argpro.argentinaprograma.services.SeccionService;

import com.argpro.argentinaprograma.models.SeccionModel.experiencia.ExperienciaModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExperienciaServiceImple extends BaseServiceImple<ExperienciaModel, Long> implements ExperienciaService {
    public ExperienciaServiceImple(BaseRepository<ExperienciaModel, Long> baseRepository) { super(baseRepository); }

    public String convertDateToString(Date dt) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dt);
    }

    public String convertDateToString(Date dt, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(dt);
    }
}
