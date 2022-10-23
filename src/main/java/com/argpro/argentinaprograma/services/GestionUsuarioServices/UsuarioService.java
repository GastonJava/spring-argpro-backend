package com.argpro.argentinaprograma.services.GestionUsuarioServices;

import com.argpro.argentinaprograma.models.GestionUsuarioModel.UsuarioModel;
import com.argpro.argentinaprograma.services.GenericoService.BaseService;
import org.springframework.stereotype.Service;


@Service
public interface UsuarioService extends BaseService<UsuarioModel, Long> {

    //agregar rol a usuarios
    public void roleAusuario(String usuarioemail, String rolNombre);


}