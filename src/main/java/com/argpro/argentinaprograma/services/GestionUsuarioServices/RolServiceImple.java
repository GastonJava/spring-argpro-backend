package com.argpro.argentinaprograma.services.GestionUsuarioServices;

import com.argpro.argentinaprograma.models.GestionUsuarioModel.RolModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
public class RolServiceImple extends BaseServiceImple<RolModel, Long> implements RolService {

    /*
    @Autowired
    IRolRepository iRolRepository;
    */

    
    public RolServiceImple(@Lazy BaseRepository<RolModel, Long> rolRepository){
        super(rolRepository);
    }

	

    /* 
    @Override
    public List<RolModel> findAll() throws Exception {
        
        return null;
    }

    @Override
    public RolModel findById(Long id) throws Exception {
       
        return null;
    }

    @Override
    public RolModel save(RolModel entity) throws Exception {
        
        return null;
    }

    @Override
    public RolModel update(Long id, RolModel entity) throws Exception {
        
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
      
        return false;
    }
    */
    
}
