package com.argpro.argentinaprograma.services.SeccionService;

import com.argpro.argentinaprograma.models.SeccionModel.educacion.EducacionModel;
import com.argpro.argentinaprograma.services.GenericoService.BaseService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface EducacionService extends BaseService<EducacionModel, Long> {

    public String convertDateToString(Date date);

    public String convertDateToString(Date date, String patter);
}
