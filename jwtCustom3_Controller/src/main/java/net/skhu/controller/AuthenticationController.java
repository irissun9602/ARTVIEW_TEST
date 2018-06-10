package net.skhu.controller;

import net.skhu.security.JwtTokenUtil;

import net.skhu.model.AuthToken;

import net.skhu.model.LoginUser;

import net.skhu.model.User;

import net.skhu.model.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

@RequestMapping("/token")

public class AuthenticationController {



    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Autowired
    private UserMapper userMapper;



    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)

    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {



        final Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        loginUser.getLogin_id(),

                        loginUser.getLogin_id()

                )

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User user = userMapper.findByLogin_id(loginUser.getLogin_id());

        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthToken(token));

    }



}