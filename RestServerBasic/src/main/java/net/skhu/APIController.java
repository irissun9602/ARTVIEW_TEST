package net.skhu;

import java.util.List;

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
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.StudentMapper;

@RestController
@CrossOrigin
@RequestMapping("api")
public class APIController {

	@Autowired StudentMapper studentMapper;
    @Autowired DepartmentMapper departmentMapper;
	@Autowired KakaoService kakaoService;
	
	@RequestMapping("students")
	public List<Student> students(){
		return studentMapper.findAll();
	}
	
	
	@RequestMapping("student/{id}")
	public Student student(@PathVariable("id") int id) {
		return studentMapper.findOne(id);
		}
	
	@RequestMapping(value="student", method=RequestMethod.POST)
	public String studentSave(@RequestBody Student student) {
		studentMapper.insert(student);
		return "저장성공";
	}
	
	@RequestMapping(value="student", method=RequestMethod.DELETE)
	public String studentSave(@PathVariable("id") int id) {
		
		studentMapper.delete(id);
		return "삭제성공";
	}
	
	@RequestMapping("departments")
	public List<Department> departments() {
		return departmentMapper.findAll();
	}
	
	
	//카카오톡  로그인 토큰 발급 서비스
	@RequestMapping(value="kakaologin", produces= "application/x-www-form-urlencoded", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public JsonNode kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {
		//로그인 후 code 얻음
		System.out.println("code: " +code);
		String token = kakaoService.getToken(code);
		return kakaoService.getKakaoUserInfo(token);
		
	}
	
	
	@RequestMapping(value="kakaoinfo", method= RequestMethod.GET)
	public Info kakaoinfo(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		
		Info info = new Info();
		
		return info;
		
	}
	
	

	
	
	
}
