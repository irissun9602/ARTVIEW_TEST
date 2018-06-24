package net.skhu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.skhu.dto.Student;
import net.skhu.dto.User;

@Mapper
public interface UserMapper {
	User findByEmail(String email);
	void updatePassword(@Param("password") String password, @Param("email")String email);
	
	
	
}
