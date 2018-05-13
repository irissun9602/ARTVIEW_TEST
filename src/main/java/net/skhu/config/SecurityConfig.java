package net.skhu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import net.skhu.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		//WebSecurityConfigurerAdapter는 기본적인 웹 인증에 대한 부분을 구현 해 놓은 클래스 입니다.
	@Autowired
	UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin();
	}	// http.csrf().disable() : CSRF 공격 검사를 하지 않겠다는 설정이다. 예제를 간단히 하기 위해 생략한다.
		//.authorizeRequests() : 요청에 대해서 권한을 처리하는데
		//.anyRequest().authenticated() : 어떠한 요청에라도 인증을 요구한다.
		//.and().formLogin(); : 그리고 Form을 이용한 로그인을 사용한다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);		//AuthenticationManagerBuilder에 해당 Bean을 주입해줍니다. (customizing 한 UserService 적옹)
	}
}
