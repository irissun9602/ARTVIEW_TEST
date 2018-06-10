package net.skhu.user;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
     user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
        userMapper.save(user);
    }
    
    @RequestMapping("/list")
    public List<User> list () {
    	return userMapper.findAll();
    }
}