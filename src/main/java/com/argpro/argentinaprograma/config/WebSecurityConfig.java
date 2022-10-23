package com.argpro.argentinaprograma.config;

import com.argpro.argentinaprograma.config.seguridad.CustomUserDetailsService;
import com.argpro.argentinaprograma.config.seguridad.JwtAuthenticationEntryPoint;
import com.argpro.argentinaprograma.config.seguridad.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                    HttpMethod.DELETE,
                        "/api/seccion/*/**",
                        "/api/seccion/navbar/delete/**",
                        "/api/seccion/sobremi/delete/**",
                        "/api/seccion/experiencia/borrarcardporid/**"

                ).permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/api/**",
                        "/api/gestionusuario/usuario/**",
                        "/api/seccion/navbar/delete/**"
                        ,"/api/seccion/*/**",
                        "/**/*"

                ).permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "/api",
                        "/api/auth/**",
                        "/api/auth/codigoadmin",
                        "/api/image/**",
                        "/api/gestionusuario/usuario/**",
                        "/api/seccion/navbar/delete/**",
                        "/api/seccion/*/**",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css"

                ).permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

   // estaba comentado ...
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                //.allowedOrigins("http://localhost:4200", "https://localhost:4200", "http://localhost:8080", "https://localhost:8080")
                .allowedOrigins("https://argentina-programa-f05d3.firebaseapp.com", "https://argentina-programa-f05d3.firebaseapp.com",
                        "https://spring-argpro-backend.azurewebsites.net", "https://spring-argpro-backend.azurewebsites.net")
                .allowedHeaders("authorization", "contentType", "Origin", "Content-Type", "X-Auth-Token")
                .exposedHeaders("Authorization")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}