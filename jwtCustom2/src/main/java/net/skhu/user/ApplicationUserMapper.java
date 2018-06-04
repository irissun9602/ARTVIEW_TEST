package net.skhu.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationUserMapper {
	ApplicationUser findByUsername(String username);
	List<ApplicationUser> findAll();
	void save(ApplicationUser user);
}

