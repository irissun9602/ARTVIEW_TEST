package net.skhu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import net.skhu.service.MyAuthenticationProvider;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired MyAuthenticationProvider myAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/res/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        	.and()
        .authorizeRequests()
        	.antMatchers("/user/login").permitAll()
        	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        
            .antMatchers("/admin/**").access("ROLE_ADMIN")
            .antMatchers("/user/**").access("USER")
            .antMatchers("/guest/**").permitAll()
            .anyRequest().authenticated()
        	.and()
        	.logout();
            
        http.authenticationProvider(myAuthenticationProvider);
    }
    
    //SpringSecurity에서 사용되는 인증객체를 bean으로 등록할 때 사용합니다. @Bean을 붙여서 Bean으로 등록해줍니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
    
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
    	return new HeaderHttpSessionStrategy();
    }
    
    
}