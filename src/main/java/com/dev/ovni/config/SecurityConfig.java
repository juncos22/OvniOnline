package com.dev.ovni.config;

import com.dev.ovni.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/aliens", "/planetas", "/aliens/buscar",
                        "/registro ", "/registrarse",
                        "/css/**", "/img/**").permitAll()
                .antMatchers("/aliens/nuevo", "/aliens/registrar",
                        "/planetas/nuevo", "/planetas/registrar").authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/index").failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/");
    }
}
