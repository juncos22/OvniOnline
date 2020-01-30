package com.dev.ovni.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.ovni.entities.Planeta;

@Repository
public interface PlanetaRepo extends CrudRepository<Planeta, Long> {
	Optional<Planeta> findPlanetaByNombre(String nombre);
}
