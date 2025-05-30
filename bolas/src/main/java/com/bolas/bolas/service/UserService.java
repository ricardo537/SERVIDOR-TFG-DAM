package com.bolas.bolas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.DeleteDTO;
import com.bolas.bolas.dto.GetProfileDTO;
import com.bolas.bolas.dto.IdDTO;
import com.bolas.bolas.dto.LoginDTO;
import com.bolas.bolas.dto.ProfileDTO;
import com.bolas.bolas.dto.RegisterDTO;
import com.bolas.bolas.dto.SessionDTO;
import com.bolas.bolas.dto.UpdateDTO;
import com.bolas.bolas.dto.UserResumeDTO;
import com.bolas.bolas.entity.Follow;
import com.bolas.bolas.entity.Stats;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.EventRepository;
import com.bolas.bolas.repository.FollowRepository;
import com.bolas.bolas.repository.StatsRepository;
import com.bolas.bolas.repository.UserRepository;

import jakarta.transaction.Transactional;

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
	@Autowired
	private StatsRepository statsRepository;
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private EventRepository eventRepository;
	
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
	    registro.setGender(registerData.getGender());
	   
	    User guardado=userRepository.save(registro);
	    
	    if (guardado==null) {
	    	return new ResponseEntity<String>("No se ha podido registrar", HttpStatus.CONFLICT);
	    }
	    return new ResponseEntity<String>("Se ha registrado correctamente", HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<String> delete(DeleteDTO deleteData) {
		
		Optional <User> delete= userRepository.findByEmailAndPassword(deleteData.getEmail(), deleteData.getPassword());
		if (delete.isEmpty()) {
			return new ResponseEntity<String>("Las credenciales no coinciden", HttpStatus.NOT_FOUND);
		
		}
		
		User user = delete.get();
		
		followRepository.deleteByFollowerOrFollows(user, user);
		
		user.getEvents().stream().forEach((event) -> {
			event.setUser(null);
		});
		
		Optional<Stats> stats = statsRepository.findById(user.getId());
		
		if (stats.isPresent()) {
			statsRepository.delete(stats.get());
		}
		
		userRepository.delete(user);
		return new ResponseEntity<String>("El usuario se ha borrado con éxito", HttpStatus.OK);
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
		
		return new ResponseEntity<SessionDTO>(updateData.getSession(), HttpStatus.OK);
	}
	
	public boolean updateProfileImg(String email, String fileName) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			user.get().setImg(fileName);
			userRepository.save(user.get());
			return true;
		}
		return false;
	}
	
	public ResponseEntity<ProfileDTO> getProfile(GetProfileDTO profile) {
		Optional<User> user = userRepository.findById(profile.getId());
		Optional<User> asking = userRepository.findByEmailAndPassword(profile.getSession().getEmail(), profile.getSession().getPassword());
		
		if (user.isEmpty() || asking.isEmpty()) {
			return new ResponseEntity<ProfileDTO>(new ProfileDTO(), HttpStatus.NOT_FOUND);
		}
		
		Optional<Stats> stats = statsRepository.findById(user.get().getId());
		Stats sts;
		
		if (stats.isEmpty()) {
			sts = new Stats();
		} else {
			sts = stats.get();
		}
		
		List<Follow> followers_list = followRepository.findByFollows(user.get());
		List<Boolean> itFollows = followers_list.stream().map(f-> {
			return f.getFollower().equals(asking.get());
		}).filter(fo -> fo).collect(Collectors.toList());
		long followers = followRepository.countByFollows(user.get());
		long follows = followRepository.countByFollower(user.get());
		
		return new ResponseEntity<ProfileDTO>(ProfileDTO.toProfile(user.get(), sts, followers, follows, itFollows.size() != 0), HttpStatus.OK);
	}
	
	public ResponseEntity<IdDTO> getMyId(SessionDTO session) {
		Optional<User> user = userRepository.findByEmailAndPassword(session.getEmail(), session.getPassword());
		
		if (user.isEmpty()) {
			return new ResponseEntity<IdDTO>(new IdDTO(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<IdDTO>(new IdDTO(user.get().getId()), HttpStatus.OK);
	}
	
	public ResponseEntity<List<UserResumeDTO>> findByName(String name) {
		List<User> users = userRepository.findByNameStartingWith(name);
		List<UserResumeDTO> usersResume = users.stream().map(user -> {
			return new UserResumeDTO(user);
		}).collect(Collectors.toList());
		return new ResponseEntity<List<UserResumeDTO>>(usersResume, HttpStatus.OK);
	}
}
