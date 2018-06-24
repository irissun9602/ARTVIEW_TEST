package net.skhu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.skhu.dto.User;
import net.skhu.email.model.EmailAndName;

@Mapper
public interface UserMapper {
	User findByEmailAndName(EmailAndName en);
	void updatePassword(@Param("password") String password, @Param("email")String email);
	
	
	
}
