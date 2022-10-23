package com.argpro.argentinaprograma.services.AdmindtoService;


import com.argpro.argentinaprograma.models.AdminModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AdmindtoServiceImple extends BaseServiceImple<AdminModel, Long> implements AdmindtoService {

    //@Value("${app.codadmin}")
    private final Integer codigo = 555;

    //logg
    Logger log = LoggerFactory.getLogger(this.getClass());

    public AdmindtoServiceImple(@Lazy BaseRepository<AdminModel, Long> baseRepository){
        super(baseRepository);
    }

    //implementamos el metodo esAdmin el cual retorna true si el codigo es correcto
    //o false si es incorrecto.
    @Override
    public Boolean Codigoadmin(int codigoadmin){

        if(555 == codigoadmin){
            return true;
        }else {
            return false;
        }

        //return codigo == codigoadmin;
    }

}
