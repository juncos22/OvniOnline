package com.dev.ovni.services;

import com.dev.ovni.encoding.Encoder;
import com.dev.ovni.entities.Usuario;
import com.dev.ovni.repos.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> oUsuario = usuarioRepo.findByNombre(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (oUsuario.isPresent()) {
            authorities.add(new SimpleGrantedAuthority("USER"));
            Usuario usuario = oUsuario.get();
            return new User(usuario.getNombre(), usuario.getContrasenia(), authorities);
        }

        return new User(username, username, authorities);
    }

    public void registrarUsuario(Usuario usuario) {
        Encoder encoder = Encoder.getInstance(usuario.getContrasenia());
        usuario.setContrasenia(encoder.encode());
        usuarioRepo.save(usuario);
    }
}
