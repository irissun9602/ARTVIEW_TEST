package net.skhu.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.skhu.domain.User;
import net.skhu.mapper.UserMapper;


@Service
public class UserServiceImpl implements UserService {
	// UserService를 implements 하게 되면 loadUserByUsername 메소드를 반드시 구현해야합니다.
	// 이 메소드는 UserDetailsServcie에 정의된 메소드로 실제 Spring Security에서 User 정보를 읽을 때 사용됩니다.
	
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.readUser(username);	//유저 정보를 읽어옵니다.
		user.setAuthorities(getAuthorities(username));// getAuthorities 메소드를 이용하여 권한을 부여해준 뒤 리턴합니다.
		return user;
	}

	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<String> string_authorities = userMapper.readAuthority(username);	//Mybatis로 조회합니다.
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	//Mybatis를 통해 String을  가져왔으므로 GrantedAuthority 인터페이스에 맞게 
		for (String authority : string_authorities) {
			authorities.add(new SimpleGrantedAuthority(authority));				//SimpleGrantedAuthority로 변환해서 리스트를 만든 후 리턴합니다.
		}
		return authorities;
	}
}
