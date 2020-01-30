package com.dev.ovni.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ovni.entities.Alien;
import com.dev.ovni.entities.Planeta;
import com.dev.ovni.repos.AlienRepo;

@Service
public class AlienService {
	@Autowired
	private AlienRepo alienRepo;
	
	public void registrarAlien(Alien a) {
		alienRepo.save(a);
	}
	
	public void actualizarAlien(Alien a) {
		Optional<Alien> aOptional = alienRepo.findById(a.getId());
		if (aOptional.isPresent()) {
			Alien alien = aOptional.get();
			alien.setNombre(a.getNombre());
			alien.setNacimiento(a.getNacimiento());
			alien.setPlaneta(a.getPlaneta());
			alienRepo.save(alien);
		}
	}
	
	public void eliminarAlien(long id) {
		Optional<Alien> aOptional = alienRepo.findById(id);
		if (aOptional.isPresent()) {
			Alien alien = aOptional.get();
			alienRepo.delete(alien);
		}
	}
	
	public List<Alien> listarAliens() {
		List<Alien> aliens = new ArrayList<Alien>();
		for (Alien alien : alienRepo.findAll()) {
			aliens.add(alien);
		}
		return aliens;
	}
	
	public List<Alien> findAliens(Planeta planeta) {
		return alienRepo.findAliensByPlaneta(planeta);
	}
	
	public Optional<Alien> findAlien(String nombre) {
		return alienRepo.findAlienByNombre(nombre);
	}
}
