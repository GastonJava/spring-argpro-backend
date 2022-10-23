package com.argpro.argentinaprograma.repositories.SeccionRepository;

import com.argpro.argentinaprograma.models.SeccionModel.navbar.NavbarModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INavbarRepository extends BaseRepository<NavbarModel, Long> {

    //NavbarModel findByEmail(String email);

    //NavbarModel findByNombre(String nombre); // buscar por el nombre solo

    //Optional<NavbarModel> findByNombreOrEmail(String nombre, String email);

    //public Optional<UsuarioModel> findByUsername(String username);

    //public Boolean existsByNombre(String nombre);

    //public Boolean existsByEmail(String email);

    public Optional<NavbarModel> findByImagentitulo(String tituloimage);
}
