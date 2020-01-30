package com.dev.ovni.controllers;

import com.dev.ovni.entities.Planeta;
import com.dev.ovni.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("planetas")
public class PlanetaController {
    @Autowired
    private PlanetaService planetaService;
    @Autowired
    private IndexController indexController;

    @GetMapping
    public String planetas(Model model) {
        model.addAttribute("titulo", "Planetas Registrados");
        model.addAttribute("planetas", planetaService.listarPlanetas());
        return "planetas";
    }

    @GetMapping("nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Registrar un Planeta");
        model.addAttribute("planeta", new Planeta());
        return "nuevoPlaneta";
    }

    @PostMapping("registrar")
    public String registrar(Planeta planeta, Model model) {
        planetaService.registrarPlaneta(planeta);
        model.addAttribute("mensaje", "Planeta Registrado Exitosamente!");
        return planetas(model);
    }
}
