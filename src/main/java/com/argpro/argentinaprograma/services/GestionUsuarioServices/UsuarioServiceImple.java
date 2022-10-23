package com.argpro.argentinaprograma.services.GestionUsuarioServices;

import javax.transaction.Transactional;

import com.argpro.argentinaprograma.models.GestionUsuarioModel.RolModel;
import com.argpro.argentinaprograma.models.GestionUsuarioModel.UsuarioModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.repositories.GestionUsuarioRepository.IRolRepository;
import com.argpro.argentinaprograma.repositories.GestionUsuarioRepository.IUsuarioRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



import java.util.Optional;

//@RequiredArgsConstructor --> reemplazara al constructor con esto no es necesario el constructor
@Service
public class UsuarioServiceImple extends BaseServiceImple<UsuarioModel, Long> implements UsuarioService { //implements IGenericRepository<UsuarioModel, Long>

    //inyeccion de dependencia
    //@Autowired
    //BaseRepository baseRepository;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    IRolRepository rolRepository;

    /*
    @Value("${app.codadmin}")
    private int codadmin;
    */

    //logg 
    Logger log = LoggerFactory.getLogger(this.getClass());

    public UsuarioServiceImple(@Lazy BaseRepository<UsuarioModel, Long> baseRepository){
        super(baseRepository);
    }
    
    @Override
    @Transactional 
    public void roleAusuario(String usuarioMail, String rolNombre){

        log.info("agregar rol {} a usuario - roleAusuario", usuarioMail, rolNombre);
        
        UsuarioModel usuario = usuarioRepository.findByEmail(usuarioMail);
        RolModel rol = rolRepository.findByRolnombre(rolNombre);

        log.info("agregar rol a usuario - roleAusuario");
        //usuario.getRoles().add(rol)
        usuario.getRoles().add(rol);
    }

}