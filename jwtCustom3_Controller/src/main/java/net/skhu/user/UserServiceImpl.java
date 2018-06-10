package net.skhu.user;
import net.skhu.user.UserMapper;

import net.skhu.model.User;

import net.skhu.model.UserDto;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;





@Service(value = "userService")

public class UserServiceImpl implements UserDetailsService, UserMapper {

	

	@Autowired

	private UserMapper userMapper;



	@Autowired

	private BCryptPasswordEncoder bcryptEncoder;



	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userMapper.findByUsername(username);

		if(user == null){

			throw new UsernameNotFoundException("Invalid username or password.");

		}

		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPwd(), getAuthority());

	}



	private List<SimpleGrantedAuthority> getAuthority() {

		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

	}








	@Override
	public User findByUsername(String name) {
		// TODO Auto-generated method stub
		
		User user = userMapper.findByUsername(name);
		return user;
	}



	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setPwd(bcryptEncoder.encode(user.getPwd()));
		userMapper.save(user);
	}



	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> users = userMapper.findAll();
		return users;
	}

}