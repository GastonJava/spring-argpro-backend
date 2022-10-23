package com.argpro.argentinaprograma.config.seguridad;

import com.argpro.argentinaprograma.models.GestionUsuarioModel.RolModel;
import com.argpro.argentinaprograma.models.GestionUsuarioModel.UsuarioModel;
import com.argpro.argentinaprograma.repositories.GestionUsuarioRepository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UsuarioModel usuario = iUsuarioRepository.findByNombreOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email :" +
                        " " + usernameOrEmail));
        return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
    }

    private List<? extends GrantedAuthority> mapearRoles(List<RolModel> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getRolnombre())).collect(Collectors.toList());
    }

}
