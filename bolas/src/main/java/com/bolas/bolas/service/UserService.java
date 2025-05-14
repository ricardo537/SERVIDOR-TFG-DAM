package com.bolas.bolas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.DeleteDTO;
import com.bolas.bolas.dto.LoginDTO;
import com.bolas.bolas.dto.RegisterDTO;
import com.bolas.bolas.dto.SessionDTO;
import com.bolas.bolas.dto.UpdateDTO;
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
	
	
	public ResponseEntity<String> register(RegisterDTO registerData) {
	    
	    if (userRepository.existsByEmail(registerData.getEmail())) {
	        return new ResponseEntity<String>("El email ya existe", HttpStatus.CONFLICT);
	    }
	    User registro=new User();
	    registro.setEmail(registerData.getEmail());
	    registro.setPassword(registerData.getPassword());
	    registro.setName(registerData.getName());
	   
	    User guardado=userRepository.save(registro);
	    
	    if (guardado==null) {
	    	return new ResponseEntity<String>("No se ha podido registrar", HttpStatus.CONFLICT);
	    }
	    return new ResponseEntity<String>("Se ha registrado correctamente", HttpStatus.OK);
	}
	
	public ResponseEntity<String> delete(DeleteDTO deleteData) {
		
		Optional <User> delete= userRepository.findByEmailAndPassword(deleteData.getEmail(), deleteData.getPassword());
		if (delete.isEmpty()) {
			return new ResponseEntity<String>("Las credenciales no coinciden", HttpStatus.NOT_FOUND);
		
		}
		userRepository.delete(delete.get());
			return new ResponseEntity<String>("El usuario se ha borrado con éxito", HttpStatus.FOUND);
	}

	public ResponseEntity<SessionDTO> update(UpdateDTO updateData) {
		Optional<User> user = userRepository.findByEmailAndPassword(updateData.getSession().getEmail(), updateData.getSession().getPassword());
		if (user.isEmpty()) {
			return new ResponseEntity<SessionDTO>(updateData.getSession(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		Optional<User> userRepited = userRepository.findByEmail(updateData.getEmail());
		if (userRepited.isPresent()) {
			return new ResponseEntity<SessionDTO>(updateData.getSession(), HttpStatus.CONFLICT);
		}
		
		User userUpdated = updateData.updateUser(user.get());
		userRepository.save(userUpdated);
		
		return new ResponseEntity<SessionDTO>(updateData.getSession(), HttpStatus.ACCEPTED);
	}
}
