package com.dev.ovni.controllers;

import com.dev.ovni.entities.Usuario;
import com.dev.ovni.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private IndexController indexController;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("titulo", "Iniciar Sesion");
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("titulo", "Registro de Usuario");
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registrarse")
    public String registrarse(Usuario usuario, Model model) {
        usuarioService.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado correctamente");
        return login(model);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model m) {
        try {
            request.logout();
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
        return indexController.index(m);
    }
}
