package com.bolas.bolas.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Esta clase es la que define la estructura de la tabla de base de datos, para eso declaramos que es una entidad con la etiqueta @Entity.
 * La etiqueta tabla sirve para poner el nombre de la tabla dentro de la base de datos.
 * Para que se pueda conectar y funcionar bien con el repositorio (la conexión con la base de datos) es necesario como mínimo un constructor vacío
 * y los getter y setter de todos los atributos de la entidad.
 */
@Entity
@Table (name="users")
public class User {
	/* 
	 * Para describir los atributos de una clase es necesario hay que seguir la siguiente estructura: visibilidad TipoDeDato nombreDeLaVariable;
	 * La etiqueta @Id es para decir que atributo es la clave primaria, @GeneratedValue es para que genere atumaticamente el id de los usuarios que se registren por primera vez.
 	*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//UUID es una clave numérica generada aleatoriamente, solo es necesario que semas eso.
	private UUID id;
	//La etiqueta @Column sirve para para definir las propiedades del atributo, si puede o no estar vacío, si tiene que ser único, entre otros.
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	public User(UUID id, String email, String password, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User() {
		super();
	}
	
}
