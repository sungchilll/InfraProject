package app.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmpAPIController {

	private final EmpRepository empRepository;
	
	@PostMapping("/emp")
	public Emp registerEmp(@RequestBody Emp newEmp) {
		Emp emp = empRepository.save(newEmp);
		return emp;
	}
	
	
	
}