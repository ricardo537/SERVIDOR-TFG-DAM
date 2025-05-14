package com.bolas.bolas.controler;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bolas.bolas.service.StorageService;
import com.bolas.bolas.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bolas/api/media")
public class MediaController {
	
	@Autowired
	private StorageService storageService;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;

	@PostMapping("upload/profile/{email}")
	public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable String email) {
		String path = storageService.store(multipartFile, email);
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String url = ServletUriComponentsBuilder
				.fromHttpUrl(host)
				.path("/bolas/api/media/")
				.path(path)
				.toUriString();
		boolean success = this.userService.updateProfileImg(email, url);
		if (success) {
			return Map.of("url", url);
		}
		return Map.of("url", "");
	}
	
}
