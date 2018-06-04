package net.skhu.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	User findByUsername(String login_id);
	List<User> findAll();
	void save(User user);
}

