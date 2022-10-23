package com.argpro.argentinaprograma.repositories.GestionUsuarioRepository;

import com.argpro.argentinaprograma.models.GestionUsuarioModel.RolModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
//@NoRepositoryBean
public interface IRolRepository extends BaseRepository<RolModel, Long> { //extends GenericRepository<RolModel, Long>

    //public abstract Optional<T> findByNombre(String nombre);
    RolModel findByRolnombre(String nombre);
}
