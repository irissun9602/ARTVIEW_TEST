package net.skhu.email.service;

import org.springframework.stereotype.Service;

import net.skhu.dto.Student;
import net.skhu.email.model.TestEmail;
import net.skhu.email.utils.Encryption;
import net.skhu.email.utils.RandomPassword;
import net.skhu.mapper.StudentMapper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class UserService {

	@Autowired
	StudentMapper studentMapper;
	
	public Student findStudentByEmailAndName(String email, String name){
		return studentMapper.findByEmailAndName(email, name);
		
	}
	
	
	public String setNewPassword(Student student) throws MessagingException {
		String newPassword = RandomPassword.getRamdomPassword(4);
		String setPassword = Encryption.encrypt(newPassword, Encryption.MD5);
		studentMapper.updatePassword(setPassword, student.getEmail(), student.getName());
				
	
		return newPassword;
	}
	
}
