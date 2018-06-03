package net.skhu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping("/users")
	public String getUsers() {
		return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
		           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	}
}
