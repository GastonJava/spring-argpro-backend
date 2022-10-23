package com.argpro.argentinaprograma.repositories.GestionUsuarioRepository;

import com.argpro.argentinaprograma.models.GestionUsuarioModel.UsuarioModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends BaseRepository<UsuarioModel, Long> {
    //public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);

    UsuarioModel findByEmail(String email);

    UsuarioModel findByNombre(String nombre); // buscar por el nombre solo

    Optional<UsuarioModel> findByNombreOrEmail(String nombre, String email);

    //public Optional<UsuarioModel> findByUsername(String username);

    public Boolean existsByNombre(String nombre);

    public Boolean existsByEmail(String email);

    public Optional<UsuarioModel> findByTituloimg(String tituloimage);
    
}