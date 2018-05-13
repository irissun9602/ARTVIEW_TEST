package net.skhu.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.domain.User;

@Mapper
public interface UserMapper {
	public User readUser(String username);
	public List<String> readAuthority(String username);
	//쿼리에서 String으로 리턴하기 때문에 List<String> 타입으로 받아야 합니다.
   
}
