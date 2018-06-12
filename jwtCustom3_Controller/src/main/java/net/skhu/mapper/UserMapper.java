package net.skhu.mapper;

import net.skhu.model.User;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

	User findByLogin_id(String login_id);

}