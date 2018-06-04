package net.skhu.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationUserMapper {
	ApplicationUser findByUsername(String username);
	void save(ApplicationUser user);
}

