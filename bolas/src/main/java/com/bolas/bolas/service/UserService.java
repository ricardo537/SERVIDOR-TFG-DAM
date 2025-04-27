package com.bolas.bolas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.LoginDTO;
import com.bolas.bolas.dto.SessionDTO;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.UserRepository;

/*
 * Para que spring detecte que esto es un servicio hay que escribir la etiqueta @Service. El servicio contendrá toda la  lógica de la aplicación.
 * Aquí es donde se crearan las funciones que realizan todo el trabajo
 */
@Service
public class UserService {

	/*Necesitamos conectar el/los repositorios que vamos a utilizar, para eso lo que tendremos que hacer será poner el repositorio como un atributo de la clase,
	 * pero para que funcione necesitamos una etiqueta qeu se llama @Autowired	
	 */
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<SessionDTO> login(LoginDTO loginData) {
		Optional<User> userLogged = userRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());
		if (userLogged.isEmpty()) {
			return new ResponseEntity<SessionDTO>(new SessionDTO(), HttpStatus.NOT_FOUND);
		}
		SessionDTO session = new SessionDTO(userLogged.get().getEmail(), userLogged.get().getPassword());
		return new ResponseEntity<SessionDTO>(session, HttpStatus.ACCEPTED);
	}
}
