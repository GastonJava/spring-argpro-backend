package com.argpro.argentinaprograma.services.SeccionService;

import com.argpro.argentinaprograma.models.SeccionModel.navbar.NavbarModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.stereotype.Service;

@Service
public class NavbarServiceImple extends BaseServiceImple<NavbarModel, Long> implements NavbarService{
    public NavbarServiceImple(BaseRepository<NavbarModel, Long> baseRepository) { super(baseRepository); }
}
