package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.User;
import net.skhu.mapper.UserMapper;
@RestController
@RequestMapping("api")
public class HomeController {
	@Autowired UserMapper userMapper;
	
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}
	
	@RequestMapping("index")
	public String index() {
		return "Hello World!";
	}
	
	
	@RequestMapping("user")
	public User home(@PathVariable String name) {
		User user =userMapper.readUser(name);
		return user;
	}
	
	@RequestMapping("/{name}")
	public User home2(@PathVariable String name) {
		User user =userMapper.readUser(name);
		return user;
	}
}
