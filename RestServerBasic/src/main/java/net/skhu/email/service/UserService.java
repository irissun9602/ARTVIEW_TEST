package net.skhu.email.service;

import org.springframework.stereotype.Service;

import net.skhu.dto.Student;
import net.skhu.dto.User;
import net.skhu.email.model.TestEmail;
import net.skhu.email.utils.Encryption;
import net.skhu.email.utils.RandomPassword;
import net.skhu.mapper.StudentMapper;
import net.skhu.mapper.UserMapper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class UserService {

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	UserMapper userMapper;
	
	public User findUserByEmail(String email){
		return userMapper.findByEmail(email);
		
	}
	
	
	public String setNewPassword(User user) throws MessagingException {
		String newPassword = RandomPassword.getRamdomPassword(4);
		String setPassword = Encryption.encrypt(newPassword, Encryption.MD5);
		userMapper.updatePassword(setPassword, user.getEmail());
				
	
		return newPassword;
	}
	
}
