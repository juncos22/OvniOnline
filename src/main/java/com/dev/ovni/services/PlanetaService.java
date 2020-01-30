package com.dev.ovni.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ovni.entities.Planeta;
import com.dev.ovni.repos.PlanetaRepo;

@Service
public class PlanetaService {
	@Autowired
	private PlanetaRepo planetaRepo;
	
	public void registrarPlaneta(Planeta p) {
		planetaRepo.save(p);
	}
	
	public void actualizarPlaneta(Planeta p) {
		Optional<Planeta> pOptional = planetaRepo.findById(p.getId());
		if (pOptional.isPresent()) {
			Planeta planeta = pOptional.get();
			planeta.setNombre(p.getNombre());
			planeta.setInfo(p.getInfo());
			planeta.setFoto(p.getFoto());
			planeta.setAliens(p.getAliens());
			planetaRepo.save(planeta);
		}
	}

	public List<Planeta> listarPlanetas() {
		List<Planeta> planetas = new ArrayList<Planeta>();
		for (Planeta planeta : planetaRepo.findAll()) {
			planetas.add(planeta);
		}
		return planetas;
	}
	
	public Optional<Planeta> buscarPlaneta(String nombre) {
		return planetaRepo.findPlanetaByNombre(nombre);
	}
}
