package com.dev.ovni.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Planeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String info;
	private String foto;
	@OneToMany(mappedBy = "planeta")
	private Set<Alien> aliens = new HashSet<Alien>();
	
	public Planeta(String nombre, String info, String foto, Set<Alien> aliens) {
		super();
		this.nombre = nombre;
		this.info = info;
		this.foto = foto;
		this.aliens = aliens;
	}

	public Planeta() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Alien> getAliens() {
		return aliens;
	}

	public void setAliens(Set<Alien> aliens) {
		this.aliens = aliens;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
