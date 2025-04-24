package com.bolas.bolas.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolas.bolas.entity.User;

@RestController
@RequestMapping("/bolas/api/user")
public class UserController {
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user){
		return null;	
	}
}
