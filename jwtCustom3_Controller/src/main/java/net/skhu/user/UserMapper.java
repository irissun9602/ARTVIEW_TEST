package net.skhu.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.model.User;

@Mapper
public interface UserMapper {
	User findByUsername(String login_id);
	List<User> findAll();
	void save(User user);
}

