package com.argpro.argentinaprograma.services.AdmindtoService;


import com.argpro.argentinaprograma.models.AdminModel;
import com.argpro.argentinaprograma.services.GenericoService.BaseService;
import org.springframework.stereotype.Service;

@Service
public interface AdmindtoService extends BaseService<AdminModel, Long> {
    public Boolean Codigoadmin(int codigoadmin);

}
