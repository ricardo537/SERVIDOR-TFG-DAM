package com.bolas.bolas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.User;

/*
 * Para crear un repositorio es necesario que sea una interfaz y no una clase y pongamos la etiqueta @Repository.
 * Pero una interfaz sola no hace nada por eso lo conectaremos con JpaRepository que es lo que conecta con el código para conectarse e interactuar con la base de datos
 * ese código nosotros no lo conocemos, eso lo hizo un random y lo vamos a utilizar.
 * Cuando extiendes de JpaRepository vemos esto <User, UUID>, no está puesto al azar, sigue esta estructura <Entidad, TipoDeIdDeLaEntidad> 
 * la entidad es la tabla de la base de datos y el TipoDeIdDeLaEntidad se puede deducir jaja, en el caso de User el id es de tipo UUID.
 */
@Repository
public interface UserRepository extends JpaRepository <User, UUID>{
	//Dentro de aquí tienes que definir las funciones que quieres tener con la siguiente estructura (en inglés): visibilidad TipoDeDatoQueDevuelveLaFunción nombreDeLaFunción(TipoDeDatoQueSeManda nombreDeLaVariableDondeSeGuardaElDatoMandado);
	public User save(User user);
	
}
