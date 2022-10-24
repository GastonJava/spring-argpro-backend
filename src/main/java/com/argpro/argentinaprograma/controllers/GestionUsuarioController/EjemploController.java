package com.argpro.argentinaprograma.controllers.GestionUsuarioController;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/gestionusuario/ejemplo")
public class EjemploController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String TraerEjemplo (){
        return "Tienes Acceso a los ejemplos ADMIN";
    }

    @GetMapping("acceso")
    public String EjemploSinAuth (){
        return "Tienes Acceso a los ejemplos TODOS";
    }

}
