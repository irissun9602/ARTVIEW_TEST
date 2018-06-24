package net.skhu;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import net.skhu.dto.Department;
import net.skhu.dto.Info;
import net.skhu.dto.Student;
import net.skhu.dto.User;
import net.skhu.email.model.EmailAndName;
import net.skhu.email.model.TestEmail;
import net.skhu.email.service.EmailService;
import net.skhu.email.service.UserService;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.StudentMapper;

@RestController
@CrossOrigin
@RequestMapping("api")
public class APIController {

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	KakaoService kakaoService;

	@RequestMapping("students")
	public List<Student> students() {
		return studentMapper.findAll();
	}

	@RequestMapping("student/{id}")
	public Student student(@PathVariable("id") int id) {
		return studentMapper.findOne(id);
	}

	@RequestMapping(value = "student", method = RequestMethod.POST)
	public String studentSave(@RequestBody Student student) {
		studentMapper.insert(student);
		return "저장성공";
	}

	@RequestMapping(value = "student", method = RequestMethod.DELETE)
	public String studentSave(@PathVariable("id") int id) {

		studentMapper.delete(id);
		return "삭제성공";
	}

	@RequestMapping("departments")
	public List<Department> departments() {
		return departmentMapper.findAll();
	}

	// 카카오톡 로그인 토큰 발급 서비스
	@RequestMapping(value = "kakaologin", produces = "application/x-www-form-urlencoded", method = { RequestMethod.GET,
			RequestMethod.POST })
	public JsonNode kakaoLogin(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response) {
		// 로그인 후 code 얻음
		System.out.println("code: " + code);
		String token = kakaoService.getToken(code);
		return kakaoService.getKakaoUserInfo(token);

	}

	@RequestMapping(value = "kakaoinfo", method = RequestMethod.GET)
	public Info kakaoinfo(HttpServletRequest request, HttpServletResponse response) {

		Info info = new Info();

		return info;

	}

	@Autowired
	EmailService emailService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "sendMail/{email}", method = RequestMethod.POST)
	public void send(@PathVariable("email") String email) throws MessagingException {
		
		
		TestEmail testEmail = new TestEmail("iris3795@gmail.com", email, "제목입니다", "http://localhost:8080/RestServerBasic/enabled/"+email);
		emailService.sendMail(testEmail);
	}
	
	@RequestMapping(value = "enabled/{email}", method = RequestMethod.PUT)
	public String enabled(@PathVariable("email") String email) throws MessagingException {
		
		User user = userService.unabledUserByEmail(email);
		userService.setEnabled(user);
		
		
		return "계정정보가 활성화되었습니다.";
	}
	
	

	@RequestMapping(value = "newPassword", produces = "application/json; charset=utf8",
						method = RequestMethod.POST)
	public String newPassword(@RequestBody EmailAndName en) throws MessagingException {

		User user  = userService.findUserByEmailAndName(en);

		if (user != null) {
			String sendPassword = userService.setNewPassword(user);

			TestEmail testEmail = new TestEmail("iris3795@gmail.com", en.getEmail(), "제목입니다", sendPassword);
			emailService.sendMail(testEmail);

			return "발송완료";
		}
		
		return "회원정보가 올바르지 않아 발송에 실패했습니다.";
	}

}
