package com.argpro.argentinaprograma.repositories.SeccionRepository;

import com.argpro.argentinaprograma.models.SeccionModel.sobremi.SobremiModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SobremiRepository extends BaseRepository<SobremiModel, Long> {

    Optional<SobremiModel> findByPortadatitulo(String imagennombre);
}
