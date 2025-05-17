package com.bolas.bolas.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolas.bolas.dto.DeleteDTO;
import com.bolas.bolas.dto.LoginDTO;
import com.bolas.bolas.dto.ProfileDTO;
import com.bolas.bolas.dto.RegisterDTO;
import com.bolas.bolas.dto.SessionDTO;
import com.bolas.bolas.dto.UpdateDTO;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.service.UserService;

/*
 * Un controlador tiene que llevar dos etiquetas, @RestController que demuestra que tipo de controlador es y @RequestMapping es la dirección base
 * para acceder a todas las funciones que haya dentro del controlador
 */
@RestController
@RequestMapping("/bolas/api/user")
public class UserController {
	
	/*
	 * Para conectar el controlador con el servicio hay que definirlo como variable/atributo de la clase para poder utilizarlo
	 * y es necesario poner la etiqueta @Autowired
	 */
	@Autowired
	private UserService userService;
	
	/*
	 * @PostMapping sirve para describir una ruta más específica para acceder a la función específica, es decir, para acceder a esta función tienes que escribir:
	 * localhost:8080/bolas/api/user/register
	 * @RequestBody lo que hace es transformar los datos que se envían a traves del cuerpo de la petición en el objeto que tu quieras.
	 * ResponseEntity<TipoDeDatoQueQuieras> es un objeto que se utiliza para dar una respuesta y guardar un código de estado. Por ejemplo el error 404 not found de los navegadores
	 * se mandan a traves de los objetos ResponseEntity.
	 */
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerData){
		return userService.register(registerData);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<SessionDTO> login(@RequestBody LoginDTO loginData) {
		return userService.login(loginData);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody DeleteDTO deleteData) {
		return userService.delete(deleteData);
	}
	
	@PostMapping("/update")
	public ResponseEntity<SessionDTO> update(@RequestBody UpdateDTO updateData) {
		return userService.update(updateData);
	}
	
	@PostMapping("getMyProfile")
	public ResponseEntity<ProfileDTO> getMyProfile(@RequestBody SessionDTO session) {
		return userService.getMyProfile(session);
	}
	
}
