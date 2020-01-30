package com.dev.ovni.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.ovni.entities.Alien;
import com.dev.ovni.entities.Planeta;

@Repository
public interface AlienRepo extends CrudRepository<Alien, Long> {
	Optional<Alien> findAlienByNombre(String nombre);
	List<Alien> findAliensByPlaneta(Planeta planeta);
}
