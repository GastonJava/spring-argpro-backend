package com.argpro.argentinaprograma.config.seguridad;


import com.argpro.argentinaprograma.models.DTOs.LoginuserdataDTO;

public class JWTAuthResonseDTO {

    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";
    private String role;

    private LoginuserdataDTO usuario;

    public JWTAuthResonseDTO(String tokenDeAcceso) {
        super();
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public JWTAuthResonseDTO(String tokenDeAcceso, String tipoDeToken) { //, String role
        super();
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }

    public JWTAuthResonseDTO(String tokenDeAcceso, String tipoDeToken, String role) { //, String role
        super();
        this.tokenDeAcceso = tokenDeAcceso;
        this.role = role;
    }


    public JWTAuthResonseDTO(String tokenDeAcceso, String tipoDeToken, String role, LoginuserdataDTO usuario) { //, String role
        super();
        this.tokenDeAcceso = tokenDeAcceso;
        this.role = role;
        this.usuario = usuario;
    }

    public String getTokenDeAcceso() {
        return tokenDeAcceso;
    }

    public void setTokenDeAcceso(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public LoginuserdataDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(LoginuserdataDTO usuario) {
        this.usuario = usuario;
    }

}
