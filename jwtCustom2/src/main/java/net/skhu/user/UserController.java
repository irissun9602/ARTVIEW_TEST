package net.skhu.user;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserMapper applicationUserMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserMapper applicationUserMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserMapper = applicationUserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
     user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
        applicationUserMapper.save(user);
    }
    
    @RequestMapping("/list")
    public List<ApplicationUser> list () {
    	return applicationUserMapper.findAll();
    }
}