package com.argpro.argentinaprograma.repositories.GenericoRepository;

import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.argpro.argentinaprograma.models.config.Base;

@NoRepositoryBean
public interface BaseRepository<T extends Base, ID extends Serializable> extends JpaRepository<T, ID> {
    //public abstract ArrayList<T> findByPrioridad(Integer prioridad); 
    //public abstract ArrayList<T> rolInicialByName(String rolname);
    //abstract ArrayList<T> findByName(String rolname);
    //public abstract Optional<T> findByNombre(String nombre);
    //public byte[] compressBytes(byte[] data);
    //public byte[] decompressBytes(byte[] data);

}
