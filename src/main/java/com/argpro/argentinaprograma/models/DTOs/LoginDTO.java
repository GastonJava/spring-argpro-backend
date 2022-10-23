package com.argpro.argentinaprograma.models.DTOs;

public class LoginDTO {

    private String nombreOrEmail;
    private String password;

    public String getNombreOrEmail() {
        return nombreOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.nombreOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
