package com.dev.ovni.controllers;

import com.dev.ovni.entities.Alien;
import com.dev.ovni.entities.Planeta;
import com.dev.ovni.services.AlienService;
import com.dev.ovni.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("aliens")
public class AlienController {
    @Autowired
    private AlienService alienService;
    @Autowired
    private PlanetaService planetaService;

    @GetMapping
    public String aliens(Model model) {
        model.addAttribute("titulo", "Aliens Registrados");
        model.addAttribute("aliens", alienService.listarAliens());
        return "aliens";
    }

    @GetMapping("nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo Alien");
        model.addAttribute("alien", new Alien());
        model.addAttribute("planetas", planetaService.listarPlanetas());
        return "nuevoAlien";
    }

    @PostMapping("registrar")
    public String registrar(Alien alien, Model model) {
        Planeta planeta = alien.getPlaneta();
        planeta.getAliens().add(alien);
        planetaService.actualizarPlaneta(planeta);
        alienService.registrarAlien(alien);
        model.addAttribute("mensaje", "Alien Registrado Exitosamente!");
        return aliens(model);
    }

    @GetMapping("buscar")
    public String buscar(@RequestParam String planeta, Model model) {
        Optional<Planeta> oPlaneta = planetaService.buscarPlaneta(planeta);
        if (oPlaneta.isPresent()) {
            Planeta p = oPlaneta.get();
            List<Alien> aliens = alienService.findAliens(p);
            model.addAttribute("aliens", aliens);
        }else {
            model.addAttribute("mensaje","No se encontraron aliens de ese planeta");
        }
        model.addAttribute("titulo", "Aliens por Planeta");
        return "aliens";
    }
}
