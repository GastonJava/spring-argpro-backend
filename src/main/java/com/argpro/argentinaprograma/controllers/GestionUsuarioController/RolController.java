package com.argpro.argentinaprograma.controllers.GestionUsuarioController;

import com.argpro.argentinaprograma.controllers.GenericoController.BaseControllerImplem;
import com.argpro.argentinaprograma.models.GestionUsuarioModel.RolModel;
import com.argpro.argentinaprograma.services.GestionUsuarioServices.RolServiceImple;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/gestionusuario/rol")
public class RolController extends BaseControllerImplem<RolModel, RolServiceImple> {

}
