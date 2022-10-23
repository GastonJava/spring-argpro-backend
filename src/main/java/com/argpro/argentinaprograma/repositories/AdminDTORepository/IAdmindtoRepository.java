package com.argpro.argentinaprograma.repositories.AdminDTORepository;

import com.argpro.argentinaprograma.models.AdminModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdmindtoRepository extends BaseRepository<AdminModel, Long> {

    public Boolean Codigoadmin(Integer codigoadmin);
}
