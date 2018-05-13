package com.cusonar.example.user;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.SpringSecurity1Application;

import net.skhu.domain.User;
import net.skhu.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringSecurity1Application.class)
@WebAppConfiguration
public class UserMapperTest {
	@Autowired
	UserMapper userMapper;

	@Test
	public void readUserTest() {
		User user = userMapper.readUser("cusonar");
		assertThat("cusonar", is(user.getUsername()));
		assertThat("MIN", is(user.getName()));
		assertThat("1234", is(user.getPassword()));
	}

	@Test
	public void readAuthorityTest() {
		List<String> authorities = userMapper.readAuthority("cusonar");
		assertThat(authorities, hasItems("ADMIN", "USER"));
		authorities = userMapper.readAuthority("abc");
		assertThat(authorities, hasItem("USER"));
	}
}
