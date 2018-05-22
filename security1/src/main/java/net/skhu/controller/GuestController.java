package net.skhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.repository.UserRepository;
import net.skhu.domain.AuthenticationToken;
import net.skhu.domain.User;
import net.skhu.domain.AuthenticationRequest;
@RestController
public class GuestController {

	@Autowired AuthenticationManager authenticationManager;
	@Autowired UserRepository userRepository;
	
    @RequestMapping({"/", "guest/index"})
    public String index() {
        return "guest/index";
    }

    @RequestMapping(value="guest/login" )
    public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
        
    	String loginId = authenticationRequest.getLoginId();
    	String password = authenticationRequest.getPassword();
    	
    	
    	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginId,password);
    	Authentication authentication = authenticationManager.authenticate(token);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
    			SecurityContextHolder.getContext());
    	
    	
    	
    			
    	
    	User user = userRepository.findOneByLoginId(loginId);
 
    	return new AuthenticationToken(user.getLoginId(), user.getUserType(), session.getId()) ;
    }
}
